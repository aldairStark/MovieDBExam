package com.example.viewmodel.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.viewmodel.retrofit.models.Users
import com.google.firebase.firestore.FirebaseFirestore

class UserReposiroty {

    fun getUserData():LiveData<MutableList<Users>>{
        val  mutableData = MutableLiveData<MutableList<Users>>()
        FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener {result->
            val listData= mutableListOf<Users>()
            for (document in result){
                val id=document.getString("id")
                val imagen = document.getString("imagen")
                val lastname=document.getString("lastname")
                val name = document.getString("name")
                val mail = document.getString("mail")
                val latitude=document.getString("latitude")
                val password=document.getString("password")
                val longitude=document.getString("longitude")
                val users=Users(id!!,name!!,mail!!,lastname!!,password!!,latitude!!,longitude!!,imagen!!)
                listData.add(users)
            }
            mutableData.value=listData
        }
        return mutableData
    }
}