package com.camp.campingapp.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.BoardDetailActivity
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.BoardItemBinding
import com.camp.campingapp.model.BoardData

class BoardViewHolder(val binding: BoardItemBinding) : RecyclerView.ViewHolder(binding.root) {

}

class BoardAdapter(val context: Context, val itemList: MutableList<BoardData>): RecyclerView.Adapter<BoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BoardViewHolder(BoardItemBinding.inflate(layoutInflater))

    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val data = itemList.get(position)

        holder.binding.run {
            itemTitleView.text=data.title
            itemContentView.text=data.content
            itemDateView.text=data.date
        }

        // 게시글 클릭 이벤트
//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, BoardDetailActivity::class.java)
//            intent.putExtra("title", data.title)
//            intent.putExtra("title", data.content)
//            intent.putExtra("title", data.date)
//            Toast.makeText(context, "Clicked: ${data.title}", Toast.LENGTH_SHORT).show()
//            context.startActivity(intent)
//        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BoardDetailActivity::class.java)
            intent.putExtra("BoardTitle", data.title)
            intent.putExtra("BoardContent", data.content)
            intent.putExtra("BoardDate", data.date)
            context.startActivity(intent)
        }

        val imgRef = MyApplication.storage.reference.child("images/${data.docId}.jpg")
        imgRef.downloadUrl.addOnCompleteListener{ task ->

            if(task.isSuccessful){
                Glide.with(context)
                    .load(task.result)
            }
        }

    }
}