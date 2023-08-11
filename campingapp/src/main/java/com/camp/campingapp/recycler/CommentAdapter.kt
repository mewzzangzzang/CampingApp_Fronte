package com.camp.campingapp.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.BoardDetail
import com.camp.campingapp.databinding.ItemCommentBinding
import com.camp.campingapp.model.BoardData

class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

}

class CommentAdapter(val context: Context, val itemList: MutableList<BoardDetail.comment>): RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(ItemCommentBinding.inflate(layoutInflater))

    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val binding = (holder as CommentViewHolder).binding
        binding.CommentDate.text = itemList[position].time
        binding.Comment.text = itemList[position].comment

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
