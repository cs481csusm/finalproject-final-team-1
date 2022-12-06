package com.example.finalproject_final_team_1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore


class DeleteTaskFragment : Fragment(R.layout.fragment_delete_task) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO
    }

    /** == DATABASE DELETION ==
     * @param email username email
     *
     * @Note Please lowercase all email entry before passed as a parameter
     */
    private fun deleteTask(email: String){
        val database = FirebaseFirestore.getInstance()
        val dbReference = database.collection("data").whereEqualTo("email", email).get().addOnSuccessListener {
            for(document in it ){
                database.collection("data").document(document.id).delete()
                Toast.makeText(this.context, "Entry successfully deteled.", Toast.LENGTH_LONG).show()
            }
        }
    }


}