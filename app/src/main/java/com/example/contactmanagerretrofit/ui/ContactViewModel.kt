package com.example.contactmanagerretrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactmanagerretrofit.model.*
import com.example.contactmanagerretrofit.repository.ContactRepository
import com.example.contactmanagerretrofit.repository.UserRepository

class ContactViewModel : ViewModel() {
  private var getAllContact : MutableLiveData<AllContactResponse>?=null
  var addContactLiveData:MutableLiveData<AddContactResponse>? = null
    var contactDeletedLiveData:MutableLiveData<Boolean>? = null
    var contactUpdatedLiveData:MutableLiveData<Boolean>? = null

    fun getAllContact(id:String) : LiveData<AllContactResponse>?{
        getAllContact = ContactRepository.getAllContact(id)
        return getAllContact
    }

    fun addContact(addContactRequest: AddContactRequest) : LiveData<AddContactResponse>? {
        addContactLiveData = ContactRepository.addContact(addContactRequest)
        return addContactLiveData
    }

    fun deleteContact(idOfContact: String) : LiveData<Boolean>? {
        contactDeletedLiveData = ContactRepository.deleteContact(idOfContact)
        return contactDeletedLiveData
    }

    fun updateContact(idOfContact: String,updateContactRequestItem: UpdateContactRequestItem) : LiveData<Boolean>? {
        contactUpdatedLiveData= ContactRepository.updateContact(idOfContact,updateContactRequestItem)
        return contactUpdatedLiveData
    }
}
