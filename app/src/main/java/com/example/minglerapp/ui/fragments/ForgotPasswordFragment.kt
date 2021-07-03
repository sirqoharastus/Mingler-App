package com.example.minglerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minglerapp.R
import com.example.minglerapp.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordFragment : Fragment() {
    var _binding : FragmentForgotPasswordBinding? = null
    val binding get() = _binding!!
    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        binding.forgotPasswordResetPasswordButton.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        if (binding.forgotPasswordEmailEdittext.text.toString().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(binding.forgotPasswordEmailEdittext.text.toString()).matches()){
            val email = binding.forgotPasswordEmailEdittext.text.toString()
            mAuth?.sendPasswordResetEmail(binding.forgotPasswordEmailEdittext.text.toString().trim())
                ?.addOnCompleteListener {
                    if (it.isSuccessful){
                        Snackbar.make(requireView(), "Check your email to reset password", Snackbar.LENGTH_LONG).show()
                    }
                    else{
                        Snackbar.make(requireView(), "Something went wrong, try again", Snackbar.LENGTH_LONG).show()
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}