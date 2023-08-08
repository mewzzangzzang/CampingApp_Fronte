package com.camp.campingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.camp.campingapp.databinding.ActivityBoardBinding
import com.camp.campingapp.model.BoardData
import com.camp.campingapp.recycler.BoardAdapter
import com.camp.campingapp.util.myCheckPermission

class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myCheckPermission(this)
        makeRecyclerView()


        binding.add.setOnClickListener {
            startActivity(Intent(this, WriteActivity::class.java))
        }

    }
    private fun makeRecyclerView(){
        //파이어 스토어의 컬렉션 객체를 선택하는 함수.
        MyApplication.db.collection("Boards")
            .get()
            //성공후 처리하는 로직
            //news라는 컬렉션(테이블)에서 모든 문서(행과 비슷)를 가져옴
            .addOnSuccessListener {result ->
                //빈 리스트 만들고,임시로 저장할 공간이 필요
                //DTO(=VO),ItemData
                val itemList = mutableListOf<BoardData>()
                //반복문으로 받아온 문서를 하나씩 꺼내어서,작업.
                for(document in result){
                    //document.toObject,gson,jackson,해당 모델 클래스에 자동으로 매핑
                    //받아온 데이터를 지정한 클래스 형으로 자동 변환(매핑)
                    val item = document.toObject(BoardData::class.java)
                    //문서의 고유 아이디를 docId에 할당
                    item.docId=document.id
                    //각 ItemData형으로,리스트에 담기
                    itemList.add(item)
                }
                //리사이클러 뷰 출력을 리니어로 기본 세로 출력
                binding.boardRecyclerView.layoutManager = LinearLayoutManager(this)
                //리사이클러 뷰의 어댑터를 연결하는 부분
                //itemList->파이어 베이스에서 받아온 일반 데이터(문자열)
                binding.boardRecyclerView.adapter = BoardAdapter(this, itemList)
            }
            .addOnFailureListener{exception ->
                //파이어베이스 콘솔
                Log.d("kkang", "error.. getting document..", exception)
                Toast.makeText(this, "서버 데이터 획득 실패", Toast.LENGTH_SHORT).show()
            }
    }


}