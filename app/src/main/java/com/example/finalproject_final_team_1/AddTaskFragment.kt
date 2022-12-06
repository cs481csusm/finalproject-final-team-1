package com.example.finalproject_final_team_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AddTaskFragment : Fragment(R.layout.fragment_add_task) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    /** == DATABASE ENTRY CREATION ==
     * @param email email used.
     * @param password account password
     *
     *
     * @Note Please lowercase all email entry before passed as a parameter
     * @Note Add entries as needed
     */
    fun addTask(email: String, password: String){
        val user : MutableMap<String, Any> = HashMap()
        val db = FirebaseFirestore.getInstance()

        //Add additional fields as needed
        user["email"] = email
        user["password"] = password

        db.collection("users").add(user).addOnFailureListener{
                e ->
            Toast.makeText(this.context, "Unable to add entry", Toast.LENGTH_LONG).show()
        }.addOnSuccessListener {
            Toast.makeText(this.context, "Entry has been added to the database", Toast.LENGTH_LONG).show()
        }
    }

}