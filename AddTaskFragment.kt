package com.example.finalproject_final_team_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import androidx.lifecycle.ViewModelProvider


import com.google.firebase.firestore.FirebaseFirestore

class AddTaskFragment : Fragment(R.layout.fragment_add_task) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.addButton).setOnClickListener{
            addTask(
                view.findViewById<EditText>(R.id.taskText).toString().toInt(), //Read in the task name
                view.findViewById<EditText>(R.id.dayText).toString().toInt(), //Read what day
                view.findViewById<EditText>(R.id.weekDelete).toString().toInt() //Read in the hours
            )

        }
    }


    /** == DATABASE ENTRY CREATION ==
     * @param email email used.
     * @param password account password
     *
     *
     * @Note Please lowercase all email entry before passed as a parameter
     * @Note Add entries as needed
     */

    fun addTask(week: Int, hours: Int, day:Int) {
        val viewModel = ViewModelProvider(this).get(VMClass::class.java)
        viewModel.addTask(week,day,hours)
    }

}