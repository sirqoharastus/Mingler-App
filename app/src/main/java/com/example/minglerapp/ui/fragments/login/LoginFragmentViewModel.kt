package com.example.minglerapp.ui.fragments.login

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class LoginFragmentViewModel: ViewModel() {

    private lateinit var firbaseUser: FirebaseUser
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth

    fun login(email: String, password:String):Task<AuthResult> {
        firebaseAuth = FirebaseAuth.getInstance()
        return firebaseAuth.signInWithEmailAndPassword(email, password)
    }
}