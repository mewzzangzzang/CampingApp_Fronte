package com.camp.campingapp


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.databinding.ActivityAuthBinding
import com.camp.campingapp.test.MyApplication
import com.facebook.FacebookSdk
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MyApplication->checkAuth=>로그인이 확인
        if(MyApplication.checkAuth()){
            changeVisibility("login")
        }else {
            changeVisibility("logout")
        }

        //인텐트로 후처리하는 코드
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            //구글 로그인 결과 처리...........................
            //it.data구글로 인증된 정보가 들어있음
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                //인증객체,인증수단(등록된 이메일,구글인증)
                MyApplication.auth.signInWithCredential(credential)
                    //구글 인증으로 성공 후 실행할 로직,
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            //구글인증으로 된 이메일의 현재앱의 로그인된 email 재할당하는 부분
                            MyApplication.email = account.email
                            //changeVisibility각모드마다 보여주는 뷰가 다름
                            changeVisibility("login")
                        }else {
                            changeVisibility("logout")
                        }
                    }
            }catch (e: ApiException){
                changeVisibility("logout")
            }
        }

        binding.logoutBtn.setOnClickListener {
            //로그아웃...........
            MyApplication.auth.signOut()
            MyApplication.email = null
            //이메일 널로 할당
            changeVisibility("logout")
        }

        binding.goSignInBtn.setOnClickListener{
            changeVisibility("signin")
        }


        binding.googleLoginBtn.setOnClickListener {
            //구글 로그인....................
            //구글 로그인 관련 함수. 옵션부분을 설정
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestIdToken(getString(R.string.default_web_client_id))
//            default_web_client_id 첫 빌드 때 ,컴파일 오류가 일어남
                //인증 후,안보이게된다 ->인증된 아이디를 가져와서 사용했기때문
                //DEFAULT_SIGH_IN-> 파이어베이스 콘솔에서,지정함
                .requestEmail()
                //옵션객체에 담아두면
                .build()
            //gso
            val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
            requestLauncher.launch(signInIntent)
        }

        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입........................
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            //인증 방법 중에서 이메일,패스워드를 이용한 회원 가입 부분.
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                //파이어베이스 인증서비스에 이메일 등록->인증이메일 보냄->이메일 확인되면 등록
                .addOnCompleteListener(this){task ->
                    //이메일 등록후 수행되는 코드
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if(task.isSuccessful){
                        //인증된 이메일 존재할시 인증메일 보냄
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener{ sendTask ->
                                if(sendTask.isSuccessful){
                                    //인증메일확인시 가입완료
                                    Toast.makeText(baseContext, "회원가입에서 성공, 전송된 메일을 확인해 주세요",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                }else {
                                    Toast.makeText(baseContext, "메일 발송 실패", Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                }
                            }
                    }else {
                        Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        changeVisibility("logout")
                    }
                }

        }

        binding.loginBtn.setOnClickListener {
            //이메일, 비밀번호 로그인.......................
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
            //인증객체 함수중에서 로그인 처리하는 함수
            MyApplication.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    //유효성 체크가 성공하면 수행
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    if(task.isSuccessful){
                        //이메일과 비번이 보내지면 수행되는 함수
                        //인증된 이메일을 시스템에 등록
                        if(MyApplication.checkAuth()){
                            MyApplication.email = email
                            changeVisibility("login")
                        }else {
                            Toast.makeText(baseContext, "전송된 메일로 이메일 인증이 되지 않았습니다.", Toast.LENGTH_SHORT).show()

                        }
                    }else {
                        Toast.makeText(baseContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }


    //매개변수를 모드라는 변수명,문자열 타입.
    fun changeVisibility(mode: String){
        if(mode === "login"){
            binding.run {
                //인증된 이메일 부분
                authMainTextView.text = "${MyApplication.email} 님 반갑습니다."
                //뷰를 show & hide
                //로그아웃 버튼은 보이게
                logoutBtn.visibility= View.VISIBLE
                //인증버튼 안보이게
                goSignInBtn.visibility= View.GONE
                //구글로그인 안보이게
                googleLoginBtn.visibility= View.GONE
                //페북로그인 안보이게
                facebookLoginBtn.visibility=View.GONE
                //이메일 입력란 안보이게
                authEmailEditView.visibility= View.GONE
                //패스워드 입력안보이게
                authPasswordEditView.visibility= View.GONE

                signBtn.visibility= View.GONE
                loginBtn.visibility= View.GONE
            }

        }else if(mode === "logout"){
            binding.run {
                authMainTextView.text = "로그인 하거나 회원가입 해주세요."
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.VISIBLE
                googleLoginBtn.visibility = View.VISIBLE
                facebookLoginBtn.visibility=View.VISIBLE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE

                signBtn.visibility = View.GONE
                loginBtn.visibility = View.VISIBLE
            }
        }else if(mode === "signin"){
            binding.run {
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE
                facebookLoginBtn.visibility=View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                signBtn.visibility = View.VISIBLE
                loginBtn.visibility = View.GONE
            }
        }
    }
}