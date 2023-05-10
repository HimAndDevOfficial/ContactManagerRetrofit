package com.example.contactmanagerretrofit.model

data class UserResponse(
    val _id: String,
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String,
)