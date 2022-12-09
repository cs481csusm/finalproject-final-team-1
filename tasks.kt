package com.example.finalproject_final_team_1

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore

class VMClass : ViewModel(){
    var week1 = arrayOf<Number>(0,6,15,18,2,3,0);
    var week2 = arrayOf<Number>(4,5,4,5,0,23,3);
    var week3 = arrayOf<Number>(13,19,14,10,3,11,8);

    fun addTask(week: Int, day: Int, hours: Int)
    {
        if (week == 1) {week1[day] = hours}
        if (week == 2) {week2[day] = hours}
        if (week == 3) {week3[day] = hours}

        val user: MutableMap<String, Any> = HashMap()
        val db = FirebaseFirestore.getInstance()
        //Add additional fields as needed
        user["name"] = week
        user["day"] = day
        user["hours"] = hours

        db.collection("task").add(user)
    }

    fun fillArray(array: Array<Number>, week: Int): Array<Number> {
        val index = 0
        if (week == 1) {for (i in week1){array[index] = i}}
        if (week == 2) {for (i in week2){array[index] = i}}
        if (week == 3) {for (i in week3){array[index] = i}}

        val database = FirebaseFirestore.getInstance()
        var dbReference = database.collection("data").whereEqualTo("week", 1).get().addOnSuccessListener {
            for(document in it ){
                week1[document.data.getValue("day") as Int] = document.data.getValue("hours") as Int
            }
        }
        dbReference = database.collection("data").whereEqualTo("week", 2).get().addOnSuccessListener {
            for(document in it ){
                week1[document.data.getValue("day") as Int] = document.data.getValue("hours") as Int
            }
        }
        dbReference = database.collection("data").whereEqualTo("week", 3).get().addOnSuccessListener {
            for(document in it ){
                week1[document.data.getValue("day") as Int] = document.data.getValue("hours") as Int
            }
        }

        return array
    }

    fun deleteTask(week: Int, day: Int)
    {
        if (week == 1) {week1[day] = 0}
        if (week == 2) {week2[day] = 0}
        if (week == 3) {week3[day] = 0}

        val database = FirebaseFirestore.getInstance()
        val dbReference = database.collection("task").whereEqualTo("day",day).get().addOnSuccessListener {
            for(document in it ){
                database.collection("data").document(document.id).delete()
            }
        }
    }

}