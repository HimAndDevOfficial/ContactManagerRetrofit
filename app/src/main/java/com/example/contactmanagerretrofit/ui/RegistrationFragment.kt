package com.example.contactmanagerretrofit.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.contactmanagerretrofit.DashboardActivity
import com.example.contactmanagerretrofit.databinding.FragmentRegistrationBinding
import com.example.contactmanagerretrofit.model.UserRequest


class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val registerViewModel :RegistrationViewModel by viewModels()
    private var notRegister = true
    private var notRegisterLoginObserver = true

    companion object {
        var IDOFUSER =""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(layoutInflater,container,false)

        binding.register.setOnClickListener {

            if(notRegister) {
                registerViewModel.checkUserExist(binding.editTextTextEmailAddress.text.toString())?.observe(viewLifecycleOwner,
                    Observer {  response ->


                        if(response){

                            Toast.makeText(requireContext(),"User Already Exists", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            var userRequest = UserRequest(binding.editTextTextPersonName.text.toString(),binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString(),binding.editTextTextConfirmPassword.text.toString())
                            registerViewModel.registerUser(userRequest)?.observe(viewLifecycleOwner, Observer {
                                    userResponse -> Log.d("email",userResponse.email)
                                Toast.makeText(requireContext(),"User Created :"+userResponse.name +" Successfully",Toast.LENGTH_SHORT).show()
                                //registeration fragment
                            })
                        }
                    })
                notRegister = false
            }
            else {
                registerViewModel.checkUserExist(binding.editTextTextEmailAddress.text.toString())
            }
        }


        binding.login.setOnClickListener {
         if(notRegisterLoginObserver) {
             registerViewModel.userLogin(binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString())?.observe(viewLifecycleOwner,
                 Observer { response ->
                     response._id
                     if(response.email.equals("User Not Found"))
                     Toast.makeText(requireContext(),"Incorrect Password or Email / Or Sign Up",Toast.LENGTH_SHORT).show()
                     else
                     {
                         IDOFUSER = response._id.toString()
                         val intent = Intent(requireActivity(),DashboardActivity::class.java)
                         startActivity(intent)
                     }
                 })
             notRegisterLoginObserver= false
         }
            else{
             registerViewModel.userLogin(binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString())
         }
        }

        return binding.root
    }

}

//https://crudcrud.com/api/4de731209927496aaab4ae776e8bf832