package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var edtemail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnsignup: Button
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        edtemail=findViewById(R.id.edt_email);
        edtpassword=findViewById(R.id.edt_password)
        btnLogIn=findViewById(R.id.btnLogIn)
        btnsignup=findViewById(R.id.edt_signup)

        mAuth= FirebaseAuth.getInstance()
        btnsignup.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener {
            val email=edtemail.text.toString()
            val password=edtpassword.text.toString()
        login(email,password)
        }

    }
    private fun login(email:String,password:String)
    {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@LogIn,MainActivity2::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogIn,"Some error ocured", Toast.LENGTH_SHORT).show()
                }
            }
    }
}