package com.example.myownapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myownapp.R

data class Timetable(
    val dayOfWeek: String,
    val startTime: String,
    val teacher: String,
    val subject: String
)
// Step 3: Create a Map of Timetable lists for each day of the week
val timetableMap = mapOf(
    "Monday" to listOf(
        Timetable("Monday", "9:00 AM to 10:00 AM","Subhita" ,"Math"),
        Timetable("Monday", "10:00 AM to 11:00 AM", "Jeevan Bala","Science"),
        // Add more Timetable objects for Monday
    ),
    "Tuesday" to listOf(
        Timetable("Tuesday", "9:00 AM", "Senthil", "English"),
        Timetable("Tuesday", "10:00 AM", "Akram", "History"),
        // Add more Timetable objects for Tuesday
    ),
    "Wednesday" to listOf(
        Timetable("Monday", "9:00 AM to 10:00 AM","Manisha" ,"Math"),
        Timetable("Monday", "10:00 AM to 11:00 AM", "Jeevan Bala","Science"),
        // Add more Timetable objects for Monday
    ),
    "Thursday" to listOf(
        Timetable("Monday", "9:00 AM to 10:00 AM","Subhita" ,"Math"),
        Timetable("Monday", "10:00 AM to 11:00 AM", "Senthil","Science"),
        // Add more Timetable objects for Monday
    ),
    "Friday" to listOf(
        Timetable("Monday", "9:00 AM to 10:00 AM","Subhita" ,"Math"),
        Timetable("Monday", "10:00 AM to 11:00 AM", "Akram","Science"),
        // Add more Timetable objects for Monday
    ),

    // Add Timetable lists for the rest of the days of the week
)
// Step 5: Create an Adapter for the RecyclerView
class TimetableAdapter(private val timetableList: List<Timetable>) :
    RecyclerView.Adapter<TimetableAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define the views in your timetable item layout
        val startTimeTextView: TextView = itemView.findViewById(R.id.tv_class_time)
        val teacherTextView: TextView = itemView.findViewById(R.id.tv_class_teacher)
        val subjectTextView: TextView = itemView.findViewById(R.id.tv_class_name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the timetable item layout
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.timetable_item_layout, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind the Timetable object to the views in the timetable item layout
        val timetable = timetableList[position]
        holder.startTimeTextView.text = timetable.startTime
        holder.teacherTextView.text = timetable.teacher
        holder.subjectTextView.text = timetable.subject
    }
    override fun getItemCount() = timetableList.size
}
// Step 4 and 6: Retrieve the selected day and display the corresponding timetable
class TimetableActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timetable_card)
        // Initialize the views in your layout
        spinner = findViewById(R.id.day_spinner)
        recyclerView = findViewById(R.id.timetable_recycler_view)
        // Set the RecyclerView layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Set the Spinner adapter with the days of the week
        val daysOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, daysOfWeek)
        spinner.adapter = spinnerAdapter

        val emptyAdapter = TimetableAdapter(emptyList())
        recyclerView.adapter = emptyAdapter
        // Set an OnItemSelectedListener to retrieve the selected day and display the timetable
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedDay = parent?.getItemAtPosition(position) as String
                val timetableList = timetableMap[selectedDay] ?: emptyList()
                val adapter = TimetableAdapter(timetableList)
                recyclerView.adapter = adapter
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}
