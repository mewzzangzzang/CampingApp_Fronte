package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.BoardDetail
import com.camp.campingapp.MyApplication
import com.camp.campingapp.databinding.ItemCommentBinding

class CommentAdapter(
    val context: Context,
    val itemList: MutableMap<String, BoardDetail.Comment>,
    val onEditClickListener: (position: Int) -> Unit,
    val onDeleteClickListener: (commentKey: String) -> Unit
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(ItemCommentBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val binding = holder.binding
        val commentKey = itemList.keys.elementAt(position)
        val commentItem = itemList[commentKey]

        binding.CommentDate.text = commentItem?.time
        binding.Comment.text = commentItem?.comment
        binding.CommentUsername.text = commentItem?.username

        // 댓글 작성자와 로그인한 사용자가 동일한 경우에만 수정 및 삭제 버튼을 표시
        if (commentItem?.username == MyApplication.userData?.username) {
            binding.editButton.visibility = View.VISIBLE
            binding.deleteButton.visibility = View.VISIBLE
        } else {
            binding.editButton.visibility = View.GONE
            binding.deleteButton.visibility = View.GONE
        }

        binding.editButton.setOnClickListener {
            onEditClickListener(position)
        }

        binding.deleteButton.setOnClickListener {
            onDeleteClickListener(commentKey)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.deleteButton.setOnClickListener {
                onDeleteClickListener(itemList.keys.elementAt(adapterPosition))
            }
        }
    }
}
