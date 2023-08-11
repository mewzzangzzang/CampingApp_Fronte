package com.camp.campingapp


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.camp.campingapp.MyApplication.Companion.rdb
import com.camp.campingapp.databinding.ActivityAuthBinding
import com.camp.campingapp.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

import com.google.firebase.database.DatabaseReference

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityAuthBinding

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth= Firebase.auth


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
        binding.goHostSignBtn.setOnClickListener{
            changeVisibility("h_signin")
        }
        binding.goGoogleSignInBtn.setOnClickListener{
            changeVisibility("G_signin")
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

        binding.googleSignBtn.setOnClickListener {
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
            saveUser()
            binding.authEmailEditView.text.clear()
            binding.authPasswordEditView.text.clear()
            binding.authUsernameEditView.text.clear()
            binding.authAddressEditView.text.clear()
            binding.authTelEditView.text.clear()

            val signInIntent = GoogleSignIn.getClient(this, gso).signInIntent
            requestLauncher.launch(signInIntent)


        }

        binding.signBtn.setOnClickListener {
            //이메일,비밀번호 회원가입........................
            val username = binding.authUsernameEditView.text.toString()
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()

            // val type=binding.authTypeEditView.text.toString()
            //인증 방법 중에서 이메일,패스워드를 이용한 회원 가입 부분.
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                //파이어베이스 인증서비스에 이메일 등록->인증이메일 보냄->이메일 확인되면 등록
                .addOnCompleteListener(this){task ->
                    //이메일 등록후 수행되는 코드

                    saveUser()
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    binding.authUsernameEditView.text.clear()
                    binding.authAddressEditView.text.clear()
                    binding.authTelEditView.text.clear()
                    if(task.isSuccessful){
                        //인증된 이메일 존재할시 인증메일 보냄
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener{ sendTask ->
                                if(sendTask.isSuccessful){
                                    //인증메일확인시 가입완료

                                    Toast.makeText(baseContext, "회원가입에서 성공, 전송된 메일을 확인해 주세요",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                    addUserToDatabase(username, email, auth.currentUser?.uid!!)
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

        binding.hostSignBtn.setOnClickListener {
            //호스트이메일,비밀번호 회원가입........................
            val username = binding.authHostUsernameEditView.text.toString()
            val email = binding.authEmailEditView.text.toString()
            val password = binding.authPasswordEditView.text.toString()
//            val type=binding.authTypeEditView.text.toString()
            //인증 방법 중에서 이메일,패스워드를 이용한 회원 가입 부분.
//            HostApplication.auth.createUserWithEmailAndPasswordAndType()
            MyApplication.auth.createUserWithEmailAndPassword(email, password)
                //파이어베이스 인증서비스에 이메일 등록->인증이메일 보냄->이메일 확인되면 등록
                .addOnCompleteListener(this){task ->
                    //이메일 등록후 수행되는 코드

                    saveHost()
                    binding.authEmailEditView.text.clear()
                    binding.authPasswordEditView.text.clear()
                    binding.authHostUsernameEditView.text.clear()
                    binding.authHostTelEditView.text.clear()
                    if(task.isSuccessful){
                        //인증된 이메일 존재할시 인증메일 보냄
                        MyApplication.auth.currentUser?.sendEmailVerification()
                            ?.addOnCompleteListener{ sendTask ->
                                if(sendTask.isSuccessful){
                                    //인증메일확인시 가입완료
                                    Toast.makeText(baseContext, "host회원가입에서 성공, 전송된 메일을 확인해 주세요",
                                        Toast.LENGTH_SHORT).show()
                                    changeVisibility("logout")
                                    addUserToDatabase(username, email, auth.currentUser?.uid!!)
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





    private fun saveUser(){
        //add............................
        //맵 객체에 키,값의 형태로 데이터를 data변수에 담음
        val data = mapOf(
            //인증된 유정의 이메일을 의미
            //앱이 시작시 인증을 체크하는 MyApplication의 checkAuth()확인함.
            "email" to binding.authEmailEditView.text.toString(),
            //뷰에서 입력된 값
            "password" to binding.authPasswordEditView.text.toString(),
            "username" to binding.authUsernameEditView.text.toString(),
            "address" to binding.authAddressEditView.text.toString(),
            "tel" to binding.authTelEditView.text.toString()
        )

        //MyApplication->db->파이어 스토어를 사용하기 위한 객체
        //collection->컬렉션을 생성하는 함수 매개변수로 컬렉션 명,(임의로 지정가능.)
        MyApplication.db.collection("user")
            //add 부분에,임의로 만든 data를 추가
            .add(data)
            //파이어 스토어에 데이터를 저장을 잘 했을 시, 동작하는 함수.
            .addOnFailureListener{
                //데이터 추가 실패시 실행되는 로직
                Log.d("kkang", "data save error", it)
            }
    }
    private fun saveHost(){
        //add............................
        //맵 객체에 키,값의 형태로 데이터를 data변수에 담음
        val data = mapOf(
            //인증된 유정의 이메일을 의미
            //앱이 시작시 인증을 체크하는 MyApplication의 checkAuth()확인함.
            "email" to binding.authEmailEditView.text.toString(),
            //뷰에서 입력된 값
            "password" to binding.authPasswordEditView.text.toString(),
            "username" to binding.authHostUsernameEditView.text.toString(),
            "tel" to binding.authHostTelEditView.text.toString()
        )

        //MyApplication->db->파이어 스토어를 사용하기 위한 객체
        //collection->컬렉션을 생성하는 함수 매개변수로 컬렉션 명,(임의로 지정가능.)
        MyApplication.db.collection("host")
            //add 부분에,임의로 만든 data를 추가
            .add(data)
            //파이어 스토어에 데이터를 저장을 잘 했을 시, 동작하는 함수.
            .addOnFailureListener{
                //데이터 추가 실패시 실행되는 로직
                Log.d("kkang", "data save error", it)
            }
    }


    private fun addUserToDatabase(name: String, email: String, uId: String){
        rdb.child("user").child(uId).setValue(User(name, email, uId))

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

                //이름 입력란 안보이게
                authUsernameEditView.visibility=View.GONE

                //페북로그인 안보이게
                facebookLoginBtn.visibility=View.GONE

                //이메일 입력란 안보이게
                authEmailEditView.visibility= View.GONE
                //패스워드 입력안보이게
                authPasswordEditView.visibility= View.GONE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE
                authHostUsernameEditView.visibility=View.GONE
                authTelEditView.visibility=View.GONE
                authHostTelEditView.visibility=View.GONE
                //타입입력 안보이게
//                authTypeEditView.visibility = View.GONE
                //호스트 가입 안보이게
                goHostSignBtn.visibility=View.GONE


                signBtn.visibility= View.GONE
                hostSignBtn.visibility=View.GONE
                loginBtn.visibility= View.GONE
                goGoogleSignInBtn.visibility=View.GONE
                googleSignBtn.visibility=View.GONE
            }

        }else if(mode === "logout"){
            binding.run {
                authMainTextView.text = "로그인 하거나 회원가입 해주세요."
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.VISIBLE
                googleLoginBtn.visibility = View.VISIBLE

                authUsernameEditView.visibility = View.GONE

                facebookLoginBtn.visibility=View.VISIBLE
                goHostSignBtn.visibility=View.VISIBLE

                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE
                authHostUsernameEditView.visibility=View.GONE
                authTelEditView.visibility=View.GONE
                authHostTelEditView.visibility=View.GONE
//                authTypeEditView.visibility = View.GONE

                signBtn.visibility = View.GONE
                goGoogleSignInBtn.visibility=View.VISIBLE
                hostSignBtn.visibility=View.GONE
                loginBtn.visibility = View.VISIBLE
                googleSignBtn.visibility=View.GONE
            }
        }else if(mode === "signin"){
            binding.run {
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE

                authUsernameEditView.visibility = View.VISIBLE

                facebookLoginBtn.visibility=View.GONE
                goHostSignBtn.visibility=View.GONE

                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.VISIBLE
                authUsernameEditView.visibility=View.VISIBLE
                authHostUsernameEditView.visibility=View.GONE
                authTelEditView.visibility=View.VISIBLE
                authHostTelEditView.visibility=View.GONE
//                authTypeEditView.visibility = View.VISIBLE
                signBtn.visibility = View.VISIBLE
                goGoogleSignInBtn.visibility=View.GONE
                hostSignBtn.visibility=View.GONE
                loginBtn.visibility = View.GONE
                googleSignBtn.visibility=View.GONE
            }
        }
        else if(mode==="h_signin"){
            binding.run{
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE
                facebookLoginBtn.visibility=View.GONE
                goHostSignBtn.visibility=View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.GONE
                authUsernameEditView.visibility=View.GONE
                authHostUsernameEditView.visibility=View.VISIBLE
                authTelEditView.visibility=View.GONE
                authHostTelEditView.visibility=View.VISIBLE
//                authTypeEditView.visibility = View.VISIBLE
                signBtn.visibility = View.GONE
                goGoogleSignInBtn.visibility=View.GONE
                hostSignBtn.visibility = View.VISIBLE
                loginBtn.visibility = View.GONE
                googleSignBtn.visibility=View.GONE
            }
        }
        else if(mode==="G_signin"){
            binding.run{
                logoutBtn.visibility = View.GONE
                goSignInBtn.visibility = View.GONE
                googleLoginBtn.visibility = View.GONE
                authUsernameEditView.visibility = View.VISIBLE
                facebookLoginBtn.visibility=View.GONE
                goHostSignBtn.visibility=View.GONE
                authEmailEditView.visibility = View.VISIBLE
                authPasswordEditView.visibility = View.VISIBLE
                authAddressEditView.visibility=View.VISIBLE
                authUsernameEditView.visibility=View.VISIBLE
                authHostUsernameEditView.visibility=View.GONE
                authTelEditView.visibility=View.VISIBLE
                authHostTelEditView.visibility=View.GONE
                signBtn.visibility = View.GONE
                goGoogleSignInBtn.visibility=View.GONE
                hostSignBtn.visibility=View.GONE
                loginBtn.visibility = View.GONE
                googleSignBtn.visibility=View.VISIBLE
            }
        }

    }
}