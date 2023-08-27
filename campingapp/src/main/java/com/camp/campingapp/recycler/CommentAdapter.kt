package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.BoardDetail
import com.camp.campingapp.databinding.ItemCommentBinding

class CommentAdapter(
    val context: Context,
    val itemList: MutableList<BoardDetail.Comment>, // Change to MutableList
    val onEditClickListener: (position: Int) -> Unit,
    val onDeleteClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(ItemCommentBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val binding = holder.binding
        val commentItem = itemList[position]

        binding.CommentDate.text = commentItem.time
        binding.Comment.text = commentItem.comment
        binding.CommentUsername.text = commentItem.username // Set the username TextView

        binding.editButton.setOnClickListener {
            onEditClickListener(position) // Call the edit click listener
        }

        binding.deleteButton.setOnClickListener {
            onDeleteClickListener(position) // Call the delete click listener
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun deleteComment(position: Int) {
        itemList.removeAt(position) // Remove the comment from the list
        notifyItemRemoved(position) // Notify the adapter that an item has been removed
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        // ...

        init {
            binding.deleteButton.setOnClickListener {
                onDeleteClickListener(adapterPosition) // Call the delete click listener
                deleteComment(adapterPosition) // Delete the comment and update RecyclerView
            }
        }
    }
}
