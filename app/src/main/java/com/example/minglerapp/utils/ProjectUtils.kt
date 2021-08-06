package com.example.minglerapp.utils

import android.view.View

fun View.showSnackBar(message: String) {
    com.google.android.material.snackbar.Snackbar.make(this, message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG).show()
}
