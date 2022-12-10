package com.example.finalproject_final_team_1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.androidplot.xy.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//The test data we use for the chart
var week1 = arrayOf<Number>(0,6,15,18,2,3,0);
var week2 = arrayOf<Number>(4,5,4,5,0,23,3);
var week3 = arrayOf<Number>(13,19,14,10,3,11,8);



/**
 * A simple [Fragment] subclass.
 * Use the [OverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OverviewFragment : Fragment(R.layout.fragment_overview) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OverviewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(VMClass::class.java)


        //Creates the plot object
        val plot = view.findViewById<XYPlot>(R.id.plot)

        //Sets up the x/y axis and their displayed numbers
        plot.setDomainBoundaries(0,7.5, BoundaryMode.FIXED)
        plot.setRangeBoundaries(0,24,BoundaryMode.FIXED)
        plot.setRangeStep(StepMode.INCREMENT_BY_VAL,1.0)
        plot.setDomainStep(StepMode.INCREMENT_BY_VAL,1.0)

        fillArrays()

        //The different bars for each index
        var bar1 = SimpleXYSeries(Arrays.asList(* viewModel.week1),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 1")
        var bar2 = SimpleXYSeries(Arrays.asList(* viewModel.week2),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 2")
        var bar3 = SimpleXYSeries(Arrays.asList(* viewModel.week3),SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Week 3")

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

    }

    private fun fillArrays()
    {
        val viewModel = ViewModelProvider(this).get(VMClass::class.java)
        viewModel.fillArray(week1,1)
        viewModel.fillArray(week1,2)
        viewModel.fillArray(week1,3)
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