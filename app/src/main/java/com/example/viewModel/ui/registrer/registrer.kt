package com.example.viewModel.ui.registrer

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewModel.R
import com.example.viewModel.retrofit.models.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
    //variables location
    private val LOCATION_PERMISSION_REQUEST_CODE=2000
    private lateinit var locationViewModel: LocationViewModel
    private var lat:String ?=null
    private var log:String ?=null
    //variables firebase
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrer)

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
        saveUser()
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
    private fun saveUser() {
        var user=User().apply {

        }
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
}