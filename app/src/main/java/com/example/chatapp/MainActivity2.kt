package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var clientRecyclerView: RecyclerView
    private lateinit var clientlist:ArrayList<Clients>
    private lateinit var adapter: ClientAdapter
    private lateinit var mAuth:FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        mAuth= FirebaseAuth.getInstance()
        mDbRef=FirebaseDatabase.getInstance().getReference()
        clientlist= ArrayList()
        adapter= ClientAdapter(this,clientlist)
clientRecyclerView=findViewById(R.id.clientRecyclerview)
        clientRecyclerView.layoutManager=LinearLayoutManager(this)
        clientRecyclerView.adapter=adapter
    mDbRef.child("Clients").addValueEventListener(object:ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            clientlist.clear()
            for(postsnapshot in snapshot.children){
               val currentuser= postsnapshot.getValue(Clients::class.java)
           if(mAuth.currentUser?.uid!=currentuser?.uid){
               clientlist.add(currentuser!!)
           }
            }
         adapter.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            mAuth.signOut()
            finish()
        return true
    }
    return true
    }
}