package com.example.minglerapp.ui.fragments.chatScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.minglerapp.R
import com.example.minglerapp.databinding.FragmentChatScreenBinding

class ChatScreenFragment : Fragment() {
    private val navArgs by navArgs<ChatScreenFragmentArgs>()
    var _binding : FragmentChatScreenBinding? =null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = navArgs.receiverData.firstName
        binding.sampleTextView.text = name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
