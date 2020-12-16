package com.example.viewModel.ui.registrer

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.viewModel.common.MyApp
import com.example.viewModel.ui.login.login
import com.example.viewmodel.retrofit.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class registrerViewModel:ViewModel() {

    private lateinit  var firestore:FirebaseFirestore
    private lateinit var mAut :FirebaseAuth

init {

    firestore= FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()
    firestore.firestoreSettings = settings


    //firestore.firestoreSettings =FirebaseFirestoreSettings.Builder()
      //  .setPersistenceEnabled(true)
        //.build()
}

    fun save(user: Users){

       val document= firestore.collection("users").document()

        val set=document.set(user)
            set.addOnSuccessListener {r->

                Log.d("Firebase","Document guardado")

            }
            set.addOnFailureListener {r->
                Log.d("Firebase","Document guardado erroneamente")
            }
    }


}