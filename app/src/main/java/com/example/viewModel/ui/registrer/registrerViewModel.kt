package com.example.viewModel.ui.registrer

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.viewModel.common.MyApp
import com.example.viewModel.retrofit.models.User
import com.example.viewModel.ui.login.login
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class registrerViewModel:ViewModel() {

    private lateinit  var firestore:FirebaseFirestore

init {

    firestore= FirebaseFirestore.getInstance()
    firestore.firestoreSettings =FirebaseFirestoreSettings.Builder().build()
}

    fun save(user:User){
       val document= firestore.collection("users").document()
        user.id=document.id
        val set=document.set(user)
            set.addOnSuccessListener {r->

                Log.d("Firebase","Document guardado")

            }
            set.addOnFailureListener {r->
                Log.d("Firebase","Document guardado erroneamente")
            }
    }


}