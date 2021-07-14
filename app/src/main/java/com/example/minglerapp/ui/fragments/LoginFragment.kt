package com.example.minglerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.minglerapp.R
import com.example.minglerapp.databinding.FragmentLoginBinding
import com.example.minglerapp.ui.activities.DashboardActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.startCodingButton.setOnClickListener {
            val name = binding.emailEdittext.text.toString()
            val password = binding.passwordEditText.text.toString()
            login(name, password)
        }

        binding.newUserRegisterTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.loginFragmentForgotPasswordTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }
    }

    private fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Snackbar.make(
                    requireContext(),
                    requireView(),
                    "Login Successful",
                    Snackbar.LENGTH_LONG
                ).show()
                lifecycleScope.launch {
                    delay(2000)
                }
                val intent = Intent(requireContext(), DashboardActivity::class.java)
                startActivity(intent)
                this.activity?.finish()

            } else {
                Snackbar.make(
                    requireContext(),
                    requireView(),
                    "Login Unsuccessful",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
