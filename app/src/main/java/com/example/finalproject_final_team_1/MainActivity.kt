package com.example.finalproject_final_team_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mainMenuLoginButton).setOnClickListener {
            if(login("johndoe@email.com", "123")) { //Testing login
                startActivity(Intent(this, AppOverview::class.java))
            }
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
}