package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var edtname: EditText
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        edtemail=findViewById(R.id.edttt_email);
        edtname=findViewById(R.id.name)
        edtpassword=findViewById(R.id.edttt_password)
        btnsignup=findViewById(R.id.btnn_signup)

        mAuth= FirebaseAuth.getInstance()
        btnsignup.setOnClickListener {
            val name=edtname.text.toString()
            val email=edtemail.text.toString()
            val password=edtpassword.text.toString()
            signup(name,email,password)
        }
    }
    private fun signup(name:String,email:String,password:String)
    {
        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addtodata(name,email,mAuth.currentUser?.uid!!)
                  val intent= Intent(this@SignUp,MainActivity2::class.java)
                    finish()
                    startActivity(intent)
                } else {
                  Toast.makeText(this@SignUp,"Some error ocured",Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun addtodata(name:String,email: String,uid:String){
    mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("Clients").child(uid).setValue(Clients(name,email,uid))
    }

}