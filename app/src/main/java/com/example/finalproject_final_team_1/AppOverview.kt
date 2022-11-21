package com.example.finalproject_final_team_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.Intent
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class AppOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_overview)

        val overview_fragment = OverviewFragment()
        val add_task_fragment = AddTaskFragment()
        val delete_task_fragment = DeleteTaskFragment()
        val theBottomNavMenu = findViewById<BottomNavigationMenuView>(R.id.bottomNavigationMenu)

        //theBottomNavMenu.OnItemSelectedListener

        findViewById<BottomNavigationMenuView>(R.id.bottomNavigationMenu).On
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}