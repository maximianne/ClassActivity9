package com.example.kotlinexample2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    //define a mutable live data to store the information
    val userInformation= MutableLiveData<UserInformation>()
    //any--> any type

    //write a get ad set to get and update the mutable live data

    fun setInformation(info:UserInformation){
        userInformation.value=info;
    }

    fun getInformation(): MutableLiveData<UserInformation>{
        return userInformation;
    }



}