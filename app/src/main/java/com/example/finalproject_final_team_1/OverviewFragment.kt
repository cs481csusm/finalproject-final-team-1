package com.example.finalproject_final_team_1

import android.graphics.Color
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import androidx.fragment.app.Fragment
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.androidplot.xy.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

//The test data we use for the chart
var week1 = arrayOf<Number>(12,10,5,8,0,3,0);
var week2 = arrayOf<Number>(0,15,0,5,0,14,0);
var week3 = arrayOf<Number>(1,6,4,0,3,0,8);

/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment(R.layout.fragment_overview) {

    lateinit var dateTV: TextView
    lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Creates the plot object
        val plot = view.findViewById<XYPlot>(R.id.plot)

        //Sets up the x/y axis and their displayed numbers
        plot.setDomainBoundaries(0,7.5, BoundaryMode.FIXED)
        plot.setRangeBoundaries(0,24,BoundaryMode.FIXED)
        plot.setRangeStep(StepMode.INCREMENT_BY_VAL,1.0)
        plot.setDomainStep(StepMode.INCREMENT_BY_VAL,1.0)

        fillArrays()

        //The different bars for each index
        var bar1 = SimpleXYSeries(Arrays.asList(* week1),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 1")
        var bar2 = SimpleXYSeries(Arrays.asList(* week2),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 2")
        var bar3 = SimpleXYSeries(Arrays.asList(* week3),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 3")

        //Coloring the bars
        var barformatter1 = BarFormatter(Color.RED, Color.YELLOW);
        var barformatter2 = BarFormatter(Color.GREEN, Color.YELLOW);
        var barformatter3 = BarFormatter(Color.BLUE, Color.YELLOW);

        //Plotting the bars to the chart
        plot.addSeries(bar1, barformatter1)
        plot.addSeries(bar2, barformatter2)
        plot.addSeries(bar3, barformatter3)

        //Changing how the bars rendered so they are grouped, set side by side, and extended to become more easily visible
        val renderer = plot.getRenderer(BarRenderer::class.java)
        renderer.setBarOrientation(BarRenderer.BarOrientation.SIDE_BY_SIDE)
        renderer.setBarGroupWidth(BarRenderer.BarGroupWidthMode.FIXED_GAP,75f)

        //code for google calendar integration
        val mButton = requireView().findViewById<FloatingActionButton>(R.id.fButton)
        val startTime = "2022-02-1T09:00:00"
        val endTime = "2022-02-1T12:00:00"
        // Parsing the date and time
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val mStartTime = mSimpleDateFormat.parse(startTime)
        val mEndTime = mSimpleDateFormat.parse(endTime)
        //Intent is started to create an event with given time
        mButton.setOnClickListener {
            val mIntent = Intent(Intent.ACTION_EDIT)
            mIntent.type = "vnd.android.cursor.item/event"
            mIntent.putExtra("beginTime", mStartTime.time)
            mIntent.putExtra("time", true)
            mIntent.putExtra("rule", "FREQ=YEARLY")
            mIntent.putExtra("endTime", mEndTime.time)
            mIntent.putExtra("title", "CS481 Event")
            startActivity(mIntent)
        } //end of mButton.setOnClickListener

    } //end of onViewCreated

    private fun fillArrays()
    {
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
    }
}
