package com.example.minglerapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minglerapp.R

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MinglerApp)
        setContentView(R.layout.activity_authentication)
    }
}