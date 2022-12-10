package com.example.finalproject_final_team_1

import com.google.firebase.firestore.FirebaseFirestore

interface Repository {

    var week1: Array<Number>
        get() = arrayOf<Number>(4,5,4,5,0,23,3)
        set(value) = fillArray(week1,1)
    var week2: Array<Number>
        get() = arrayOf<Number>(4,5,4,5,0,23,3)
        set(value) = fillArray(week2,1)
    var week3: Array<Number>
        get() = arrayOf<Number>(4,5,4,5,0,23,3)
        set(value) = fillArray(week3,1)

    fun addTask(week: Int, day: Int, hours: Int)
    {
        val user: MutableMap<String, Any> = HashMap()
        val db = FirebaseFirestore.getInstance()
        //Add additional fields as needed
        user["name"] = week
        user["day"] = day
        user["hours"] = hours

        db.collection("task").add(user)
        if (week == 1) {week1[day] = hours}
        else if (week == 2) {week2[day] = hours}
        else if (week == 3) {week3[day] = hours}
    }

    fun fillArray(array: Array<Number>, week: Int) {

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

        val index = 0
        if (week == 1) {for (i in week1){array[index] = i}}
        if (week == 2) {for (i in week2){array[index] = i}}
        if (week == 3) {for (i in week3){array[index] = i}}

    }

    fun deleteTask(week: Int, day: Int)
    {

        val database = FirebaseFirestore.getInstance()
        val dbReference = database.collection("task").whereEqualTo("day",day).get().addOnSuccessListener {
            for(document in it ){
                database.collection("data").document(document.id).delete()
            }
        }
        if (week == 1) {week1[day] = 0}
        if (week == 2) {week2[day] = 0}
        if (week == 3) {week3[day] = 0}
    }

}