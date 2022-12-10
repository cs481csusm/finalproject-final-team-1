package com.example.finalproject_final_team_1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        view.findViewById<Button>(R.id.deleteButton).setOnClickListener{
            deleteTask(
                view.findViewById<EditText>(R.id.taskDelete).toString(), //Read in the task name
                view.findViewById<EditText>(R.id.dayDelete).toString().toInt(), //Read what day
                view.findViewById<EditText>(R.id.weekDelete).toString().toInt() //Read in the hours
            )
        }

    }

    /** == DATABASE DELETION ==
     * @param email username email
     *
     * @Note Please lowercase all email entry before passed as a parameter
     */
    private fun deleteTask(name: String, day: Int, week: Int){
        val viewModel = ViewModelProvider(this).get(VMClass::class.java)
        viewModel.deleteTask(day,week)
    }


}