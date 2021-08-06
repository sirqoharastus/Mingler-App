package com.example.minglerapp.ui.fragments.chats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minglerapp.databinding.FragmentChatsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class ChatsFragment : Fragment() {
    lateinit var firebaseUser: FirebaseUser
    lateinit var firebaseDatabase: FirebaseDatabase
    private val chatsViewModel: ChatsViewModel by viewModels()
    private var _binding: FragmentChatsBinding ? = null
    private lateinit var contactsAdapter: ContactsAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsAdapter = ContactsAdapter()
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        chatsViewModel.getAllUsers()
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
            setHasFixedSize(true)
        }
        getAllUsers()
    }

    private fun getAllUsers() {
        chatsViewModel.listOfusers.observe(
            viewLifecycleOwner,
            {
                contactsAdapter?.getAdapterContactList(it)
                contactsAdapter.notifyDataSetChanged()
                Log.d("users", it[0].firstName.toString())
            }
        )
    }
}
