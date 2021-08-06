package com.example.minglerapp.ui.fragments.chats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.minglerapp.R
import com.example.minglerapp.databinding.ContactsLayoutBinding
import com.example.minglerapp.models.UserRegistration

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {
    private var controller: NavController? = null
    private var contactsList = ArrayList<UserRegistration>()
    inner class ContactsViewHolder(val binding: ContactsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: UserRegistration) {
            binding.contactNameTextView.text = contact.firstName
        }
    }
    fun getAdapterContactList(contactList: ArrayList<UserRegistration>) {
        this.contactsList = contactList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = ContactsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        with(holder) {
            with(contactsList[position]) {
                binding.contactNameTextView.text = firstName
            }
        }
        holder.itemView.setOnClickListener {
            controller = Navigation.findNavController(holder.itemView)
            val action = ChatsFragmentDirections.actionChatsFragmentToChatScreenFragment(contactsList[position])
            controller!!.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }
}
