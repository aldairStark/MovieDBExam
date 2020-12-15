package com.example.viewModel.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.example.viewModel.MainActivity
import com.example.viewModel.R
import com.example.viewModel.ui.registrer.registrer
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var txtLogin:EditText
    private lateinit var txtPass:EditText
    private lateinit var progresBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    private lateinit var btnSend:Button
    private lateinit var btnRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtLogin=findViewById(R.id.edtUserMail)
        txtPass=findViewById(R.id.edtPass)

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