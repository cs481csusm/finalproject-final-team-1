package com.example.finalproject_final_team_1

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.firestore.FirebaseFirestore
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mainMenuLoginButton).setOnClickListener {
            if(login("johndoe@email.com", "123")) { //Testing login
                startActivity(Intent(this, AppOverview::class.java))
            }
        }
        findViewById<Button>(R.id.notification).setOnClickListener {
           // permissionRequest()
            //sendNotification()
         }

    }

    /** == DATABASE AUTHENTICATION ==
     * @param email email used.
     * @param password account password
     *
     * @return true if authentication is successful
     *
     * @Note Please lowercase all email entry before passed as a parameter
     */
    private fun login(email: String, password: String) : Boolean{
        var returnValue = false
        val database = FirebaseFirestore.getInstance()

        //Check the database and match all email
        database.collection("data").whereEqualTo("email", email).get().addOnSuccessListener {
            database.collection("data").whereEqualTo("password", password).get().addOnSuccessListener {
                returnValue = true
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
            }
        }

        //default return
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
        return returnValue
    }

    private fun permissionRequest(){
      /*  var permissionList = mutableListOf<String>()
        if (!(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_GRANTED)){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED)){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionList.isNotEmpty()){
            //Log.d("XYZ", "${permissionList.toTypedArray()}")
            ActivityCompat.requestPermissions(this, permissionList.toTypedArray(),100)
        }*/
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                for (index in grantResults.indices){
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED){
                        Log.d("XYZ", "Your ${permissions[index]} successfully granted")
                    }
                }

            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           // val channel:NotificationChannel = NotificationChannel("C10","Title", NotificationManager.IMPORTANCE_DEFAULT).apply())
        }
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //notificationManager.createNotificationChannel(channel)
    }
    private fun sendNotification(){
        showAlert("Flow Reminder", "Check Tasks for the Week")
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this,"C10")
        with(NotificationManagerCompat.from(this)) {
            notify(10, builder.build())
        }

    }
}

private fun showAlert(title: String, message: String) {
   /* AlertDialog.Builder(this)
        .setTitle("You will see this")
        .setMessage("Title: " +title+ "\nMessage: " + message)
        .setPositiveButton("Okay"){_,_ ->}
        .show()*/

}

