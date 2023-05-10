package com.example.contactmanagerretrofit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.contactmanagerretrofit.R
import com.example.contactmanagerretrofit.model.AddContactResponse
import com.example.contactmanagerretrofit.model.AllContactResponse
import com.example.contactmanagerretrofit.model.AllContactResponseItem

class ShowContactsListAdapter : RecyclerView.Adapter<ShowContactsListAdapter.ViewHolder>() {

    private var contactList = AllContactResponse()
    var onItemClickListener : OnItemClickListener ?=null
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView = view.findViewById(R.id.indexOfContact)
        val name:TextView = view.findViewById(R.id.nameOfContact)
        val email:TextView = view.findViewById(R.id.emailOfContact)
        val number:TextView = view.findViewById(R.id.numberOfContact)
        val delete:ImageView = view.findViewById(R.id.deleteContact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.id.text = (position+1).toString()

        holder.name.text = contactList.get(position).name

        holder.email.text = contactList.get(position).email

        holder.number.text = contactList.get(position).number

        holder.itemView.findViewById<ImageView>(R.id.deleteContact).setOnClickListener{
            onItemClickListener?.onItemClick(contactList[position])
        }

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowitem).setOnClickListener{
            onItemClickListener?.onItemClickUpdateContact(contactList[position])
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setData(contactList : AllContactResponse) {
        Log.d("contactresponseDisplay", contactList.size.toString())
        this.contactList = contactList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(contact : AllContactResponseItem)
        fun onItemClickUpdateContact(contact:AllContactResponseItem)
    }

}