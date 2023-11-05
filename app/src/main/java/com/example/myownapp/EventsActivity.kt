package com.example.myownapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class Event(val title: String, val description: String, val date: String)

class EventsActivity : AppCompatActivity() {

    private lateinit var eventsList: MutableList<Event>
    private lateinit var linearLayoutEvents: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        eventsList = readEventsFromFile()
        linearLayoutEvents = findViewById(R.id.linear_layout_events)


        for (event in eventsList) {
            val cardView = layoutInflater.inflate(R.layout.card_event, null) as CardView
            val textViewTitle = cardView.findViewById<TextView>(R.id.text_view_event_title)
            val textViewDate = cardView.findViewById<TextView>(R.id.text_view_event_date)
            val textViewDescription = cardView.findViewById<TextView>(R.id.text_view_event_description)

            textViewTitle.text = event.title
            textViewDate.text = event.date
            textViewDescription.text = event.description

            linearLayoutEvents.addView(cardView,0)
        }

        val buttonAddEvent = findViewById<Button>(R.id.button_add_event)
        buttonAddEvent.setOnClickListener {
            // Open add event activity
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readEventsFromFile(): MutableList<Event> {
        val eventsList = mutableListOf<Event>()
        val file = File(filesDir, "events.txt")

        if (file.exists()) {
            val inputStream = FileInputStream(file)
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String? = bufferedReader.readLine()
            while (line != null) {
                val eventProperties = line.split(",").toTypedArray()
                val event = Event(eventProperties[0], eventProperties[1], eventProperties[2])
                eventsList.add(event)
                line = bufferedReader.readLine()
            }

            bufferedReader.close()
            inputStreamReader.close()
            inputStream.close()
        }

        return eventsList
    }
}
