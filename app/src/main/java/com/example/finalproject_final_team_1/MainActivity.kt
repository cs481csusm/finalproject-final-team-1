package com.example.finalproject_final_team_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mainMenuLoginButton).setOnClickListener {
            startActivity(Intent(this, AppOverview::class.java))
        }

    }
}