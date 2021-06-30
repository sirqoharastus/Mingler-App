package com.example.minglerapp.ui.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.minglerapp.R
import com.example.minglerapp.databinding.FragmentRegistrationBinding
import com.example.minglerapp.models.UserRegistration
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegistrationFragment : Fragment() {
    var _binding : FragmentRegistrationBinding? = null
    val binding get() = _binding!!
    private var mAuth: FirebaseAuth? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance();

        binding.signUpButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val firstName = binding.firstNameEdittext.text.toString()
        val lastName = binding.lastNameEdittext.text.toString()
        val email = binding.emailEdittext.text.toString()
        val phoneNumber = binding.phoneNumberEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        if (binding.firstNameEdittext.text.toString().isEmpty()){
            binding.firstNameEdittext.error = "Field cannot be empty"
            binding.firstNameEdittext.requestFocus()
            return
        }
        if (binding.lastNameEdittext.text.toString().isEmpty()){
            binding.lastNameEdittext.error = "Field cannot be empty"
            binding.lastNameEdittext.requestFocus()
            return
        }
        if (binding.emailEdittext.text.toString().isEmpty()){
            binding.emailEdittext.error = "Field cannot be empty"
            binding.emailEdittext.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEdittext.text.toString()).matches()){
            binding.emailEdittext.error = "Not a valid email"
            binding.emailEdittext.requestFocus()
            return
        }

        if (binding.phoneNumberEdittext.text.toString().isEmpty()){
            binding.phoneNumberEdittext.error = "Field cannot be empty"
            binding.phoneNumberEdittext.requestFocus()
            return
        }

        if (binding.phoneNumberEdittext.text.toString().length < 10){
            binding.phoneNumberEdittext.error = "Not a valid phone number"
            binding.phoneNumberEdittext.requestFocus()
            return
        }
        if (binding.passwordEdittext.text.toString().length < 7){
            binding.passwordEdittext.error = "Password length must be 6 or greater"
            binding.passwordEdittext.requestFocus()
        }
        if (binding.passwordEdittext.text.toString() != binding.confirmPasswordEdittext.text.toString()){
            binding.confirmPasswordEdittext.error = "Passwords must be the same"
            binding.confirmPasswordEdittext.requestFocus()
        }

        mAuth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { it ->
                if (it.isSuccessful){
                    val user = UserRegistration(firstName, lastName, email, phoneNumber, password)
                    FirebaseAuth.getInstance().currentUser?.let { it1 ->
                        FirebaseDatabase.getInstance().getReference("Users").child(
                            it1.uid)
                    }?.setValue(user)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            Snackbar.make(requireContext(), requireView(), "User registered successfully", Snackbar.LENGTH_LONG).show()

                        }
                        else{
                            Snackbar.make(requireContext(),requireView(), "Failed to register user", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
                else{
                    Snackbar.make(requireContext(),requireView(), "Failed to register user", Snackbar.LENGTH_LONG).show()

                }
            }
    }

}