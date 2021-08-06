package com.example.minglerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserRegistration(
    var id: String? = null,
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val phoneNumber: String? = ""
): Parcelable
