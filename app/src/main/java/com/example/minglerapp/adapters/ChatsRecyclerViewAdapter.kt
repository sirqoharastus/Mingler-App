package com.example.minglerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minglerapp.databinding.ChatsItemLayoutBinding
import com.example.minglerapp.models.Chats

class ChatsRecyclerViewAdapter(private val chatsList: ArrayList<Chats>) :
    RecyclerView.Adapter<ChatsRecyclerViewAdapter.ChatsRecyclerViewHolder>() {

    class ChatsRecyclerViewHolder(private val binding: ChatsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chats: Chats) {
            binding.apply {
                userNameTextview.text = chats.name
                lastMessageTimeTextView.text = chats.time
                lastMessageTimeTextView.text = chats.lastTextBody
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsRecyclerViewHolder {
        val binding: ChatsItemLayoutBinding = ChatsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatsRecyclerViewHolder, position: Int) {
        val currentItem = chatsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = chatsList.size
}
