package com.example.contactmanagerretrofit.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.contactmanagerretrofit.model.GetAllUserResponse
import com.example.contactmanagerretrofit.model.ParticularUserResponseItem
import com.example.contactmanagerretrofit.model.UserRequest
import com.example.contactmanagerretrofit.model.UserResponse
import com.example.quotesapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserRepository {

    var userResponse = MutableLiveData<UserResponse>()

    var isEmailExists = MutableLiveData<Boolean>()

    var getAllUserResponse = MutableLiveData<GetAllUserResponse>()

    var getUserResponse = MutableLiveData<ParticularUserResponseItem>()

    fun registerUser(userRequest: UserRequest): MutableLiveData<UserResponse> {

        val call = RetrofitClient.apiInterface.registerUser(userRequest)

        call.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {

                Log.d("DEBUG resp :",response.body().toString())

                userResponse.value = response.body()
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return userResponse
    }


    fun checkUserExist(email:String): MutableLiveData<Boolean> {

        val call = RetrofitClient.apiInterface.getAllUser()

        call.enqueue(object : Callback<GetAllUserResponse?> {
            override fun onResponse(call: Call<GetAllUserResponse?>, response: Response<GetAllUserResponse?>) {

                Log.d("DEBUG resp :",response.body().toString())

                getAllUserResponse.value = response.body()
                isEmailExists.value = isUserEmailExists(getAllUserResponse.value!!,email)
            }

            override fun onFailure(call: Call<GetAllUserResponse?>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return isEmailExists
    }

    fun getUser(email:String,password:String): MutableLiveData<ParticularUserResponseItem>? {

        val call = RetrofitClient.apiInterface.getAllUser()

        call.enqueue(object : Callback<GetAllUserResponse?> {
            override fun onResponse(call: Call<GetAllUserResponse?>, response: Response<GetAllUserResponse?>) {

                Log.d("DEBUG resp :",response.body().toString())

                getAllUserResponse.value = response.body()

                getUserResponse.value = getAllUserResponse.value?.let {
                    verifyUser(it,email,password) ?: ParticularUserResponseItem()
                }
            }

            override fun onFailure(call: Call<GetAllUserResponse?>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return getUserResponse
    }

    fun isUserEmailExists(users : GetAllUserResponse, email:String ): Boolean {
        return users.any {
            user -> user.email == email
        }
    }

    fun verifyUser(users : GetAllUserResponse, email:String,password: String ): ParticularUserResponseItem? {
        return users.find { it.email == email && it.password == password }
    }
}