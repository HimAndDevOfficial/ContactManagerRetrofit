package com.example.contactmanagerretrofit.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.contactmanagerretrofit.model.*
import com.example.contactmanagerretrofit.ui.RegistrationFragment.Companion.IDOFUSER
import com.example.quotesapp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContactRepository {

 var getAllContactResponse = MutableLiveData<AllContactResponse>()
    var addContactResponse = MutableLiveData<AddContactResponse> ()
    var deleteContact = MutableLiveData<Boolean> ()
    var updateContact = MutableLiveData<Boolean> ()
    fun getAllContact(id:String) :MutableLiveData<AllContactResponse> {
             val call = RetrofitClient.apiInterface.getAllContact(id)

             call.enqueue(object :Callback<AllContactResponse>{
                 override fun onResponse(
                     call: Call<AllContactResponse>,
                     response: Response<AllContactResponse>
                 ) {
                    getAllContactResponse.value =response.body()
                     Log.d("contactresponse", getAllContactResponse.toString())
                 }

                 override fun onFailure(call: Call<AllContactResponse>, t: Throwable) {

                     Log.d("messagecontact",t.message.toString())
                 }

             })
             return getAllContactResponse
         }

    fun addContact(addContactRequest: AddContactRequest): MutableLiveData<AddContactResponse> {

        val call = RetrofitClient.apiInterface.addContact(IDOFUSER,addContactRequest)

        call.enqueue(object : Callback<AddContactResponse?> {
            override fun onResponse(call: Call<AddContactResponse?>, response: Response<AddContactResponse?>) {

                Log.d("DEBUG resp :",response.body().toString())

                addContactResponse.value = response.body()
            }

            override fun onFailure(call: Call<AddContactResponse?>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return addContactResponse
    }

    fun deleteContact(idOfContact: String): MutableLiveData<Boolean> {

        val call = RetrofitClient.apiInterface.deleteContact(IDOFUSER,idOfContact)


        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any?>) {

                Log.d("DEBUG resp :",response.body().toString())

                if(response.code()==200)
                {
                    deleteContact.value= true
                } else {
                    deleteContact.value= false
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return deleteContact
    }


    fun updateContact(idOfContact: String,updateContactRequestItem: UpdateContactRequestItem): MutableLiveData<Boolean> {

        val call = RetrofitClient.apiInterface.updateContact(IDOFUSER,idOfContact,updateContactRequestItem)


        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void?>) {

                Log.d("DEBUG upt cntct resp :",response.body().toString())

                if(response.code()==200)
                {
                    updateContact.value= true
                } else {
                    updateContact.value= false
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("DEBUG error :",t.message.toString())
            }
        })
        return updateContact
    }
}