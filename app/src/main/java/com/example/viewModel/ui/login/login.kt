package com.example.viewModel.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
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
        progresBar=findViewById(R.id.progressBarLogin)
        auth= FirebaseAuth.getInstance()
        btnSend.setOnClickListener{
           //ActionSend()
            Loging()
        }
        btnRegister.setOnClickListener {
            ActionRegister()
        }

    }
    fun completeForm():Boolean{
        if (txtLogin.text.isNullOrBlank()) txtLogin.error = getString(R.string.fieldEmpty)
        if (txtPass.text.isNullOrBlank()) txtPass.error = getString(R.string.fieldEmpty)
        return !(txtLogin.text.isNullOrBlank() || txtPass.text.isNullOrBlank())
    }
    private fun Loging(){
        val userEmail:String=txtLogin.text.toString()
        val userPass:String=txtPass.text.toString()


        if (completeForm()){
            progresBar.visibility= View.VISIBLE
            println(userEmail)
            println(userPass)
            auth.signInWithEmailAndPassword(userEmail,userPass)
                .addOnCompleteListener(this){
                        task ->
                    if (task.isSuccessful){
                        ActionSend()
                        progresBar.visibility=View.INVISIBLE
                    }else{
                        Toast.makeText(this,"Error al ingresar",Toast.LENGTH_SHORT).show()
                        progresBar.visibility=View.INVISIBLE
                    }
                }

        }
    }

    private fun ActionSend(){
        startActivity(Intent(this,MainActivity::class.java))
    }
    private fun ActionRegister(){
        startActivity(Intent(this,registrer::class.java))
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser !=null){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

}