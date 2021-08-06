package com.example.minglerapp.ui.fragments.contacts

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minglerapp.databinding.ChatsItemLayoutBinding
import com.example.minglerapp.models.Contact

class ContactsRecyclerView : RecyclerView.Adapter<ContactsRecyclerView.ContactsViewHolder>() {
    private var contactsList = arrayListOf<Contact>()
    inner class ContactsViewHolder(private val binding: ChatsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.apply {
                userNameTextview.text = contact.firstName + " ${contact.lastName}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }
}
