package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.databinding.CommentItemBinding
import com.camp.campingapp.model.BoardData

class CommentViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

}

class CommentAdapter(val context: Context, val itemList: MutableList<BoardData>): RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(CommentItemBinding.inflate(layoutInflater))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val data = itemList.get(position)

        holder.binding.run {
            CommentContentView.text= data.comment.toString()
        }

    }
}