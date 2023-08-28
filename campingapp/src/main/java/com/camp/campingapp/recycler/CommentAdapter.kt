package com.camp.campingapp.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camp.campingapp.BoardDetail
import com.camp.campingapp.databinding.ItemCommentBinding

class CommentAdapter(
    val context: Context,
    val itemList: MutableMap<String, BoardDetail.Comment>,
    val onEditClickListener: (position: Int) -> Unit,
    val onDeleteClickListener: (commentKey: String) -> Unit // Updated parameter name
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

        binding.editButton.setOnClickListener {
            onEditClickListener(position)
        }

        binding.deleteButton.setOnClickListener {
            onDeleteClickListener(commentKey) // Call the delete click listener with the commentKey
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        // ...

        init {
            binding.deleteButton.setOnClickListener {
                onDeleteClickListener(itemList.keys.elementAt(adapterPosition)) // Call the delete click listener with the commentKey
                deleteComment(itemList.keys.elementAt(adapterPosition)) // Delete the comment and update RecyclerView
            }
        }
    }

    private fun deleteComment(commentKey: String) {
        itemList.remove(commentKey) // Remove the comment from the map
        notifyDataSetChanged() // Notify the adapter that data has changed
    }
}
