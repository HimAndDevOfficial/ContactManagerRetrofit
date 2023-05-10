package com.example.contactmanagerretrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateContactRequestItem(
    val email: String,
    val name: String,
    val number: String
) : Parcelable