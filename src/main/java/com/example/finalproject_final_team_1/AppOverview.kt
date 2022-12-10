package com.example.finalproject_final_team_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.Intent
import android.view.MenuItem
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView

class AppOverview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_overview)

        val overview_fragment = OverviewFragment()
        val add_task_fragment = AddTaskFragment()
        val delete_task_fragment = DeleteTaskFragment()

        setCurrentFragment(overview_fragment)

        findViewById<BottomNavigationView>(R.id.bottomNavigationMenu).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomNavOverviewButton -> setCurrentFragment(overview_fragment)
                R.id.bottomNavAddTaskButton -> setCurrentFragment(add_task_fragment)
                R.id.bottomNavDelTaskButton -> setCurrentFragment(delete_task_fragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, fragment)
            commit()
        }
    }
}