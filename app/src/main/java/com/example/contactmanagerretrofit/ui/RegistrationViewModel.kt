package com.example.contactmanagerretrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactmanagerretrofit.model.ParticularUserResponseItem
import com.example.contactmanagerretrofit.model.UserRequest
import com.example.contactmanagerretrofit.model.UserResponse
import com.example.contactmanagerretrofit.repository.UserRepository

class RegistrationViewModel : ViewModel() {
    private var userExistLiveData : MutableLiveData<Boolean>? = null
    var registerUserLiveData : MutableLiveData<UserResponse>? = null
    private var particularUserResponseItem: MutableLiveData<ParticularUserResponseItem>? = null

    fun checkUserExist(email:String) : LiveData<Boolean>? {
        
        userExistLiveData = UserRepository.checkUserExist(email)
        return userExistLiveData
    }
    
    fun registerUser(userRequest:UserRequest): LiveData<UserResponse>? {
        registerUserLiveData = UserRepository.registerUser(userRequest)
        return registerUserLiveData
    }


    fun userLogin(email:String, password:String) : LiveData<ParticularUserResponseItem>? {

        particularUserResponseItem = UserRepository.getUser(email,password)
        return particularUserResponseItem
    }

}
