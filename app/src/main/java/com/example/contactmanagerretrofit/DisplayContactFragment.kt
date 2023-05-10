package com.example.contactmanagerretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactmanagerretrofit.adapter.ShowContactsListAdapter
import com.example.contactmanagerretrofit.databinding.FragmentDisplayContactBinding
import com.example.contactmanagerretrofit.model.AddContactResponse
import com.example.contactmanagerretrofit.model.AllContactResponse
import com.example.contactmanagerretrofit.model.AllContactResponseItem
import com.example.contactmanagerretrofit.repository.ContactRepository
import com.example.contactmanagerretrofit.ui.ContactViewModel
import com.example.contactmanagerretrofit.ui.RegistrationFragment.Companion.IDOFUSER


class DisplayContactFragment : Fragment(), ShowContactsListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentDisplayContactBinding
    private val contactViewModel : ContactViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDisplayContactBinding.inflate(layoutInflater,container,false)
        val adapter = ShowContactsListAdapter()
        adapter.onItemClickListener = this
        val recyclerView = binding.displayContactRecyclerView

        recyclerView.adapter = adapter


        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val listObserver = Observer<AllContactResponse> {

            newList ->
            adapter.setData(newList)
            Log.d("contactresponseDisplay", newList.size.toString())
            adapter.notifyDataSetChanged()
        }

        contactViewModel.getAllContact(IDOFUSER)?.observe(viewLifecycleOwner,listObserver)



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        contactViewModel.getAllContact(IDOFUSER)
    }

    override fun onItemClick(contact: AllContactResponseItem) {
       contactViewModel.deleteContact(contact._id)
       contactViewModel.getAllContact(IDOFUSER)
    }

    override fun onItemClickUpdateContact(contact: AllContactResponseItem) {
        val action = DisplayContactFragmentDirections.actionDisplayContactFragmentToUpdateContactFragment(contact)
        findNavController().navigate(action)
    }

}