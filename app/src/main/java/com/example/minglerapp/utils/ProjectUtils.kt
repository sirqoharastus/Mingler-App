package com.example.minglerapp.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.SnackbarContentLayout


fun View.showSnackBar(message: String) {
    com.google.android.material.snackbar.Snackbar.make(this, message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()
}