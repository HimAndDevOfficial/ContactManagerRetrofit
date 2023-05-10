package com.example.contactmanagerretrofit.retrofit

import com.example.contactmanagerretrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

   @GET("signup")
   fun getAllUser() : Call<GetAllUserResponse>

   @POST("signup")
   fun registerUser(@Body userModel: UserRequest) : Call<UserResponse>

   @GET("{id}")
   fun getAllContact(@Path("id") id:String) :Call<AllContactResponse>

   @POST("{id}")
   fun addContact(@Path("id") id:String,@Body addContactRequest:AddContactRequest) : Call<AddContactResponse>

   @DELETE("{userid}/{contactid}")
   fun deleteContact(@Path("userid") userid:String, @Path("contactid") contactid:String) : Call<Any>

   @PUT("{userid}/{contactid}")
   fun updateContact(@Path("userid") userid:String, @Path("contactid") contactid:String,@Body userModel:UpdateContactRequestItem) : Call<Void>


}