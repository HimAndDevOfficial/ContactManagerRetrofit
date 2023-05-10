package com.example.contactmanagerretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.contactmanagerretrofit.databinding.FragmentAddContactBinding
import com.example.contactmanagerretrofit.model.AddContactRequest
import com.example.contactmanagerretrofit.ui.ContactViewModel


class AddContactFragment : Fragment() {

    private var notRegisteredAddContactObserver =true
    private lateinit var binding : FragmentAddContactBinding
    private val contactViewModel : ContactViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddContactBinding.inflate(layoutInflater, container, false)


        binding.addContact.setOnClickListener {

          if(notRegisteredAddContactObserver) {
              notRegisteredAddContactObserver=false
              var addContactRequest = AddContactRequest(email = binding.editTextTextEmailAddress.text.toString(), name = binding.editTextTextPersonName.text.toString(), number = binding.editTextTextPassword.text.toString())
              contactViewModel.addContact(addContactRequest)?.observe(viewLifecycleOwner, Observer {
                      response ->
                  if(response._id!=null) {

                      Toast.makeText(requireContext(),"ContacT added"+ response._id,Toast.LENGTH_SHORT).show()
                  }
                  else {
                      Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                  }
              })
          } else {
              var addContactRequest = AddContactRequest(email = binding.editTextTextEmailAddress.text.toString(), name = binding.editTextTextPersonName.text.toString(), number = binding.editTextTextPassword.text.toString())
              contactViewModel.addContact(addContactRequest)
          }
        }

        return binding.root
    }

}