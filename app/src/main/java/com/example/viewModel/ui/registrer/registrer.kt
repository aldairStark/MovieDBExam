package com.example.viewModel.ui.registrer

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.viewModel.R
import com.example.viewModel.retrofit.models.User
import com.example.viewModel.ui.login.login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registrer.*
import java.io.File

class registrer : AppCompatActivity() {

    private lateinit var txtUserName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPass:EditText
    private lateinit var edtextImage:TextView
    private lateinit var btnAddImage:Button
    private lateinit var btnSedUser:Button
    private lateinit var progressBar: ProgressBar
    private lateinit var labelLatitude:TextView
    private lateinit var labelLongitude:TextView
    private lateinit var viewModel:registrerViewModel
    private val File=1

    //variables location
    private val LOCATION_PERMISSION_REQUEST_CODE=2000
    private lateinit var locationViewModel: LocationViewModel
    private var lat:String ?=null
    private var log:String ?=null
    //variables firebase
    private val database: FirebaseDatabase=Firebase.database
    val myRef=database.getReference("user")

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrer)

        mAuth= FirebaseAuth.getInstance()



        txtUserName = findViewById(R.id.edtUserName)
        txtLastName = findViewById(R.id.edtUserLastname)
        txtEmail = findViewById(R.id.edtUserEMail)
        txtPass = findViewById(R.id.edtUserPass)
        edtextImage = findViewById(R.id.tvwImage)
        //butons
        btnAddImage = findViewById(R.id.btnSendImg)
        btnSedUser= findViewById(R.id.btnSendUser)
        progressBar=findViewById(R.id.progressBar)
        labelLatitude=findViewById(R.id.labelLatitude)
        labelLongitude=findViewById(R.id.labelLongitude)



        prepRequestLocationUpdates()
        btnSedUser.setOnClickListener {
            if (completedForm()){
                progressBar.visibility= View.VISIBLE
                saveUser()
            }

        }
        btnAddImage.setOnClickListener {
            fileUpload()
        }

        Toast.makeText(this,"$lat + $log",Toast.LENGTH_LONG).show()

        println("****************************************************************")
        println(lat)
        println(log)
        println("****************************************************************")

    }



    private fun prepRequestLocationUpdates() {
        if ( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
            requesLocationUpdates()
        }else{
            val permissionRequest = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissionRequest,LOCATION_PERMISSION_REQUEST_CODE)
        }

    }

    private fun requesLocationUpdates() {
       locationViewModel=ViewModelProvider(this).get(LocationViewModel::class.java)
        locationViewModel.getLocationLiveData().observe(this, Observer {
            lat= it.latitude
            log=it.Longitude
            labelLatitude.text=it.latitude.toString()
            labelLongitude.text=it.Longitude.toString()
            Toast.makeText(this,"$lat + $log",Toast.LENGTH_LONG).show()

        })
    }
    private fun ActionRegistrer(){
        startActivity(Intent(this,login::class.java))
    }
    private fun saveUser() {
        mAuth.createUserWithEmailAndPassword(txtEmail.text.toString(),txtPass.text.toString()).addOnCompleteListener(this) {
            task ->
            if (task.isComplete){
                val userAuth:FirebaseUser?=mAuth.currentUser
                var idUser=userAuth?.uid
                var user = User().apply {
                    id=idUser.toString()
                    latitude=labelLatitude.text.toString()
                    longitude=labelLongitude.text.toString()
                    name =  txtUserName.text.toString()
                    lastname= txtLastName.text.toString()
                    mail= txtEmail.text.toString()
                    password= txtPass.text.toString()
                    imagen=edtextImage.text.toString()
                    Log.i("IMAGEN",fileUpload())
                    // edtextImage = findViewById(R.id.tvwImage)
                    ActionRegistrer()
                }
                viewModel=ViewModelProvider(this).get(registrerViewModel::class.java)
                viewModel.save(user)
            }
        }

    }
    fun completedForm():Boolean{
        if (txtUserName.text.isNullOrBlank()) txtUserName.error = getString(R.string.fieldEmpty)
        if (txtLastName.text.isNullOrBlank()) txtLastName.error = getString(R.string.fieldEmpty)
        if (txtEmail.text.isNullOrBlank()) txtEmail.error = getString(R.string.fieldEmpty)
        if (txtPass.text.isNullOrBlank()) txtPass.error = getString(R.string.fieldEmpty)
        return !(txtUserName.text.isNullOrBlank() || txtLastName.text.isNullOrBlank() ||
                txtEmail.text.isNullOrBlank() || txtPass.text.isNullOrBlank())
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                requesLocationUpdates()

            }else{
                Toast.makeText(this, "consede permisos de Localizacion",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun fileUpload():String{
        val intent=Intent(Intent.ACTION_GET_CONTENT)
        intent.type="*/*"
        startActivityForResult(intent, File)
       return intent.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==File){
            if (resultCode== Activity.RESULT_OK){
                val FileUri=data!!.data

                val Folder:StorageReference=
                    FirebaseStorage.getInstance().getReference().child("User")
                val file_name:StorageReference=Folder.child("file" + FileUri!!.lastPathSegment)
                file_name.putFile(FileUri).addOnSuccessListener { taskSnapshot ->
                    file_name.downloadUrl.addOnSuccessListener {
                        uri ->
                        var hashMap= HashMap<String,String>()
                        hashMap["Link"]=java.lang.String.valueOf(uri)
                        edtextImage.text=uri.toString()
                        myRef.setValue(hashMap)
                        Log.i("Mensaje", "Se subio correctamente")
                    }
                }
            }
        }
    }
}
