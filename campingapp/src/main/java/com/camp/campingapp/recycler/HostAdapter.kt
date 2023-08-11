package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.HostListItemBinding
import com.camp.campingapp.databinding.ItemMainBinding
import com.camp.campingapp.model.HostData


class HostViewHolder(val binding: HostListItemBinding) : RecyclerView.ViewHolder(binding.root)

class HostAdapter(val context: Context, val hostList: MutableList<HostData>): RecyclerView.Adapter<HostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HostViewHolder(HostListItemBinding.inflate(layoutInflater))

    }


    override fun getItemCount(): Int {
        return hostList.size
    }
    //뷰<->데이터 연결부분

    override fun onBindViewHolder(holder: HostViewHolder, position: Int) {
        //받은 데이터의 리스트의 인덱스
        val data = hostList.get(position)

        //목록에 보여주는 요소
        holder.binding.run {
            HostCampNameView.text=data.campname

        }

        //스토리지 이미지 다운로드........................
        //MyApplication-매니페스트에 등록이 되어있음
        //imgRef 이 객체를 싸용해서,업로드,다운로드 구현
        //다운로드가 잘되면 ,콜백으로 돌아와서 로직 실행
        //스토리지에서 이미지의 url 주소만 가져온다
        val imgRef = MyApplication.storage.reference.child("images/${data.hid}.jpg")
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
                    .into(holder.binding.HostCampImageView)
            }
        }
    }
}