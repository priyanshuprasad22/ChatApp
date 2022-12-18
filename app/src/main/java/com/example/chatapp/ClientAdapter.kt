package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class ClientAdapter(val context: Context,val clientlist:ArrayList<Clients>):
    RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
     val view: View=LayoutInflater.from(context).inflate(R.layout.client_new,parent,false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
       val currentUser=clientlist[position]
        holder.textname.text=currentUser.name
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatAcitivity::class.java)
            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)
    }
    }

    override fun getItemCount(): Int {
      return clientlist.size
    }
    class ClientViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val textname=itemView.findViewById<TextView>(R.id.txt_name)
    }
}