package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.test.MyApplication
import com.camp.campingapp.databinding.ItemMainBinding
import com.camp.campingapp.model.ItemData


//리사이클러 뷰->목록 형식으로 출력되는 뷰
//뷰홀더->뷰 객체들의 모음집. 해당 뷰 홀더의 주 생성자의 매개변수에,바인딩 기법으로 객체가 선언됨
//그래서,해당 매개변수호,전체 뷰 객체에 접근 가능(뷰바인딩 객체는,목록의 아이템의 요소 사용중)
class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val itemList: MutableList<ItemData>): RecyclerView.Adapter<MyViewHolder>() {
    //어댑터 클래스를 만들고,재정의한 함수들임
    //인플레이터가 나옴,해당 뷰를 출력하기 위한 객체를 초기화하는 작업\
    //포기화는(참조형 변수에,해당 메모리 위치 주솟값을 할당하는것에 말함)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(ItemMainBinding.inflate(layoutInflater))
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
    //뷰<->데이터 연결부분

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //받은 데이터의 리스트의 인덱스
        val data = itemList.get(position)

        holder.binding.run {
            itemEmailView.text=data.email
            itemTypeView.text=data.type
        }

        //스토리지 이미지 다운로드........................
        //MyApplication-매니페스트에 등록이 되어있음
        //imgRef 이 객체를 싸용해서,업로드,다운로드 구현
        //다운로드가 잘되면 ,콜백으로 돌아와서 로직 실행
        //스토리지에서 이미지의 url 주소만 가져온다
        val imgRef = MyApplication.storage.reference.child("images/${data.uid}.jpg")
        imgRef.downloadUrl.addOnCompleteListener{ task ->

            if(task.isSuccessful){
                //후처리,스토리지의 이미지의 url주소를 잘가지고오면
                //그라이드를 이용해서 이미지로드 불러오고
                //into를 통해 해당 결과 이미지 뷰에 출력하는 고드
                //원래는,안드로이드에서 이미지의 객체 타입을 bitmap구조로 변경하는 코드 필요
                //implemaentation 'com.firebaseui:firebase-ui-storage:8.0.2
                Glide.with(context)
                    //이미지를 불러오는 역활
                    .load(task.result)
                    //불러온 이미지를,결과뷰에 출력
                    .into(holder.binding.itemImageView)
            }
        }
    }
}