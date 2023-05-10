package com.example.contactmanagerretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.contactmanagerretrofit.databinding.FragmentUpdateContactBinding
import com.example.contactmanagerretrofit.model.UpdateContactRequestItem
import com.example.contactmanagerretrofit.ui.ContactViewModel


class UpdateContactFragment : Fragment() {

    private val args by navArgs<UpdateContactFragmentArgs>()
    private val contactViewModel: ContactViewModel by viewModels()
    private lateinit var binding : FragmentUpdateContactBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUpdateContactBinding.inflate(layoutInflater,container,false)
        binding.editTextTextUpdatePersonName.setText(args.currentContactRecord.name)
        binding.editTextTextUpdateEmailAddress.setText(args.currentContactRecord.email)
        binding.editTextTextUpdateNumber.setText(args.currentContactRecord.number)

       binding.updateContact.setOnClickListener {
           var updateContactRequestItem = UpdateContactRequestItem(email= binding.editTextTextUpdateEmailAddress.text.toString(),name= binding.editTextTextUpdatePersonName.text.toString(),number=binding.editTextTextUpdateNumber.text.toString());
        contactViewModel.updateContact(args.currentContactRecord._id,updateContactRequestItem)?.observe(viewLifecycleOwner,
            Observer{
            response -> if(response ==true ) {
                Toast.makeText(requireContext(),"Contact Updated Successfully",Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
                else {
                Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()

            }
        })
       }

        return binding.root
    }

}