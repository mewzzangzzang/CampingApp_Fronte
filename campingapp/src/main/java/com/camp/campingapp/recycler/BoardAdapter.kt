package com.camp.campingapp.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camp.campingapp.BoardDetail
import com.camp.campingapp.databinding.BoardItemBinding
import com.camp.campingapp.model.BoardData

class BoardViewHolder(val binding: BoardItemBinding) : RecyclerView.ViewHolder(binding.root)

class BoardAdapter(private val context: Context, private var itemList: List<BoardData>) :
    RecyclerView.Adapter<BoardViewHolder>() {

    private var filteredItemList: List<BoardData> = itemList // 필터링된 아이템 목록

    // 필터링된 목록을 업데이트하는 메서드 추가
    fun filter(query: String) {
        filteredItemList = if (query.isEmpty()) {
            itemList // 검색어가 비어있을 때는 전체 목록 보여줌
        } else {
            itemList.filter { data ->
                data.title?.contains(query, ignoreCase = true) == true ||
                        data.content?.contains(query, ignoreCase = true) == true
            }
        }
        notifyDataSetChanged()
    }

    // updateData 메서드 추가
    fun updateData(newItemList: List<BoardData>) {
        itemList = newItemList
        filter("") // 초기화 할 때 필터링 없이 모든 아이템을 보여줌
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BoardItemBinding.inflate(layoutInflater, parent, false)
        return BoardViewHolder(binding)
    }

    override fun getItemCount(): Int = filteredItemList.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val data = filteredItemList[position]

        with(holder.binding) {
            itemTitleView.text = data.title
            itemContentView.text = data.content
            itemDateView.text = data.date
            itemUsernameView.text = data.username // 게시글 작성자의 이름 표시

            val imageUrl = data.imageUrl
            if (imageUrl != null) {
                Glide.with(context)
                    .load(imageUrl)
                    .into(itemImageView)
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, BoardDetail::class.java).apply {
                putExtra("DocId", data.docId)
                putExtra("BoardTitle", data.title)
                putExtra("BoardContent", data.content)
                putExtra("BoardDate", data.date)
                putExtra("AuthorUid", data.uid) // 게시글 작성자의 UID 전달
                putExtra("Username", data.username)
            }
            context.startActivity(intent)
        }
    }
}
