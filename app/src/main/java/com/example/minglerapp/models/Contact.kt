package com.example.minglerapp.models

import com.google.firebase.database.Exclude

class Contact(
    @get:Exclude val id: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val phoneNumber: String? = null
)
