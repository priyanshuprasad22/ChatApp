package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var bttnLogIn: Button
    private lateinit var bttnsignup: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bttnLogIn=findViewById(R.id.bttnLogIn)
        bttnsignup=findViewById(R.id.edtt_signup)
        bttnsignup.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        bttnLogIn.setOnClickListener {
            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)
        }
    }
}