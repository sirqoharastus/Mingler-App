package com.example.minglerapp.ui.fragments.registration

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegistrationViewModel: ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerUser( email: String, password: String): Task<AuthResult>{
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }
}