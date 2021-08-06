package com.example.minglerapp.ui.fragments.chats

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minglerapp.models.UserRegistration
import com.example.minglerapp.utils.USERS_REFERENCE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatsViewModel : ViewModel() {
    private var _listOfUsers = MutableLiveData<ArrayList<UserRegistration>>()
    val listOfUsers: LiveData<ArrayList<UserRegistration>> get() = _listOfUsers

    var firebaseDatabase = FirebaseDatabase.getInstance()
    var firebaseUser = FirebaseAuth.getInstance().currentUser

    fun getAllUsers() {
        var userList = ArrayList<UserRegistration>()
        val usersRef = firebaseDatabase.getReference(USERS_REFERENCE)
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (i in snapshot.children) {
                    val user = i.getValue(UserRegistration::class.java)
                    if (user != null) {
                        userList.add(user)
                        Log.d("user", user.toString())
                    }
                }
                _listOfUsers.value = userList
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
