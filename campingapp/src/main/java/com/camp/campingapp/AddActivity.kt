package com.camp.campingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.camp.campingapp.databinding.ActivityAddBinding
import com.camp.campingapp.util.dateToString

import java.io.File
import java.util.*

class AddActivity : AppCompatActivity() {

    //전역으로 기본 바인딩,뷰 객체 모음 모음
    lateinit var binding: ActivityAddBinding
    lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //바인딩 변환후 ,인플레이트를 이용해서,출력 객체 초기화
        binding= ActivityAddBinding.inflate(layoutInflater)
        //실제화면에 출력하는 ㅅ함수
        setContentView(binding.root)

    }
    //사진선택후 돌아왔을때 후처리하는 코드)
//인텐트를 이용해서,후처리를 하는 코드

    val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode === android.app.Activity.RESULT_OK){
            //가져온 이미지를 처리를 글라이드 이용
            Glide
                .with(getApplicationContext())
                //선택한 이미지 불러오기
                .load(it.data?.data)
                //출력사진의 크기
                .apply(RequestOptions().override(250, 200))
                //사진의 크기 조정
                .centerCrop()
                //불러온 이미지를 결과뷰에 출력
                .into(binding.addImageView)

//커서 부분은 해당,이미지의 Uri 결로로 위치를 파악하는 구문
            //이미지의 위치가 있는 uri주소
            //MediaStore.Images.Media.DATA:이미지의 정보


            val cursor = contentResolver.query(it.data?.data as Uri,
                arrayOf<String>(MediaStore.Images.Media.DATA), null, null, null);
            cursor?.moveToFirst().let {
//                filePath=cursor?.getString(0) as String->경로 주소
                filePath=cursor?.getString(0) as String
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //액션바의 메뉴의 이벤트 리스너

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_add_gallery){
            //Intent.ACTION_PINK->갤러리 사진 선택으로 이동
            val intent = Intent(Intent.ACTION_PICK)
            //인텐트 옵션에서,액션 문자열은
            //epdlxj xkdlq,MIME Type,모든 이미지
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            //인텐트의 후처리를 호출하는 함수이고, 위에 정의한 변수로 이동
            //
            requestLauncher.launch(intent)
        }else if(item.itemId === R.id.menu_add_save){
            if(binding.addImageView.drawable !== null && binding.addEditView.text.isNotEmpty()){
                //store 에 먼저 데이터를 저장후 document id 값으로 업로드 파일 이름 지정
                saveStore()
            }else {
                Toast.makeText(this, "데이터가 모두 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }
    //....................
    //파이어베이스 스토어에 저장하는 기능의 함수
    private fun saveStore(){
        //add............................
        //맵 객체에 키,값의 형태로 데이터를 data변수에 담음
        val data = mapOf(
            //인증된 유정의 이메일을 의미
            //앱이 시작시 인증을 체크하는 MyApplication의 checkAuth()확인함.
            "email" to MyApplication.email,
            //뷰에서 입력된 값
            "content" to binding.addEditView.text.toString(),

            "date" to dateToString(Date())
//            "type" to MyApplication.
        )


        //MyApplication->db->파이어 스토어를 사용하기 위한 객체
        //collection->컬렉션을 생성하는 함수 매개변수로 컬렉션 명,(임의로 지정가능.)
        MyApplication.db.collection("user")
            //add 부분에,임의로 만든 data를 추가
            .add(data)
            //파이어 스토어에 데이터를 저장을 잘 했을 시, 동작하는 함수.
            .addOnSuccessListener {
                //일반 데이터(문자열)파이어 스토어 저장이 잘 되었을 때 만
                //이미지를 스토리지에 저장하는 구조.
                uploadImage(it.id)
            }
            .addOnFailureListener{
                //데이터 추가 실패시 실행되는 로직
                Log.d("kkang", "data save error", it)
            }

    }
    //스토리지 기능중,업로드
    private fun uploadImage(docId: String){
        //add............................
        //매개변수 부분은,글 작성시 docId라고,문서번호(자동생성)
        //MyApplication->db->파이어 스토리지를 사용하기 위한 객체
        val storage = MyApplication.storage
        val storageRef = storage.reference
        //스토리지에서 레퍼런스를 이용해서 해당 객체를 바인딩
        val imgRef = storageRef.child("images/${docId}.jpg")
        //imgRef라는 객체로 업로드 및 다운로드 실행, 여기서는 업로드 부분
        //child->상위폴더,images 하위에 이미지 파일이 저장되는 구조.

        //후처리 코드에서,선택된 사진의 절대 경로를 file이라고 하는 참조형 변수에 해당
        val file = Uri.fromFile(File(filePath))
        //imgRef의 기능중,putFile경로의 파일을 업로드 하는 기능.
        imgRef.putFile(file)
            //이미지 업로드가 성공시 수행되는 로직->
            .addOnSuccessListener {
                //토스트 알림
                Toast.makeText(this, "save ok..", Toast.LENGTH_SHORT).show()
                //AddActivity를 수동으로 종료. 생명주기=onDestroy()
                finish()
            }
            //실패시 수행 로직
            .addOnFailureListener{
                Log.d("kkang", "file save error", it)
            }

    }
}