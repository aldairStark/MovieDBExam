package com.example.viewmodel.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.viewmodel.MainActivity
import com.example.viewmodel.R
import com.example.viewmodel.ui.registrer.registrer

class login : AppCompatActivity() {
    private lateinit var btnSend:Button
    private lateinit var btnRegister:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnSend = findViewById(R.id.Btn_Login)
        btnRegister=findViewById(R.id.btnRegistrer)
        btnSend.setOnClickListener{
            ActionSend()
        }
        btnRegister.setOnClickListener {
            ActionRegister()
        }

    }
    private fun ActionSend(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    private fun ActionRegister(){
        startActivity(Intent(this,registrer::class.java))
    }
}