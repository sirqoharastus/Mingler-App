package com.example.minglerapp.ui.fragments.registration

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.minglerapp.R
import com.example.minglerapp.databinding.FragmentRegistrationBinding
import com.example.minglerapp.models.UserRegistration
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment : Fragment() {
    var _binding: FragmentRegistrationBinding? = null
    val binding get() = _binding!!
    private var mAuth: FirebaseAuth? = null
    private val viewModel by viewModels<RegistrationViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val email = binding.emailEdittext.text.toString()
        val phoneNumber = binding.phoneNumberEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (binding.firstNameEditText.text.toString().isEmpty()) {
            binding.firstNameEditText.error = "Field cannot be empty"
            binding.firstNameEditText.requestFocus()
            return
        }
        if (binding.lastNameEditText.text.toString().isEmpty()) {
            binding.lastNameEditText.error = "Field cannot be empty"
            binding.lastNameEditText.requestFocus()
            return
        }
        if (binding.emailEdittext.text.toString().isEmpty()) {
            binding.emailEdittext.error = "Field cannot be empty"
            binding.emailEdittext.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailEdittext.text.toString()).matches()) {
            binding.emailEdittext.error = "Not a valid email"
            binding.emailEdittext.requestFocus()
            return
        }

        if (binding.phoneNumberEditText.text.toString().isEmpty()) {
            binding.phoneNumberEditText.error = "Field cannot be empty"
            binding.phoneNumberEditText.requestFocus()
            return
        }

        if (binding.phoneNumberEditText.text.toString().length < 10) {
            binding.phoneNumberEditText.error = "Not a valid phone number"
            binding.phoneNumberEditText.requestFocus()
            return
        }
        if (binding.passwordEditText.text.toString().length < 7) {
            binding.passwordEditText.error = "Password length must be 6 or greater"
            binding.passwordEditText.requestFocus()
        }
        if (binding.passwordEditText.text.toString() != binding.confirmPasswordEditText.text.toString()) {
            binding.confirmPasswordEditText.error = "Passwords must be the same"
            binding.confirmPasswordEditText.requestFocus()
        }

        viewModel.registerUser(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val id = mAuth?.currentUser?.uid.toString()
                val user = UserRegistration(id, firstName, lastName, email, phoneNumber)
                FirebaseAuth.getInstance().currentUser?.let { it1 ->
                    FirebaseDatabase.getInstance().getReference("Users").child(
                        it1.uid
                    )
                }?.setValue(user)?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        Snackbar.make(
                            requireContext(),
                            requireView(),
                            "User registered successfully",
                            Snackbar.LENGTH_LONG
                        ).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                    } else {
                        Snackbar.make(
                            requireContext(),
                            requireView(),
                            "Failed to register user",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Snackbar.make(
                    requireContext(),
                    requireView(),
                    "Failed to register user",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
