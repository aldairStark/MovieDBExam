package com.example.viewModel.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewmodel.repository.UserReposiroty
import com.example.viewmodel.retrofit.models.Users

class DashboardViewModel : ViewModel() {
    private val repositoryUser =UserReposiroty()
fun fetchUserData():LiveData<MutableList<Users>>{
val mutableData = MutableLiveData<MutableList<Users>>()
repositoryUser.getUserData().observeForever{
        mutableData.value = it
   }
    return mutableData
}

}
