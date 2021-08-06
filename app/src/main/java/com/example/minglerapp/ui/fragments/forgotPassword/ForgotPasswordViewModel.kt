package com.example.minglerapp.ui.fragments.forgotPassword

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun forgotPassword(email: String): Task<Void> {
        return firebaseAuth.sendPasswordResetEmail(email)
    }
}
