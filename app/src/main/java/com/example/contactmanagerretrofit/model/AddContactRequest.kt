package com.example.contactmanagerretrofit.model

data class AddContactRequest(
    val email: String,
    val name: String,
    val number: String
)