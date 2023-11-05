package com.example.myownapp


import android.app.DatePickerDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.util.*

class AddEventActivity : AppCompatActivity() {

    private lateinit var editTextDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        editTextDate = findViewById(R.id.edit_text_event_date)
        editTextDate.setOnClickListener {
            showDatePickerDialog()
        }

        val buttonAddEvent = findViewById<Button>(R.id.button_add_event)
        buttonAddEvent.setOnClickListener {
            // Get input values
            val editTextTitle = findViewById<EditText>(R.id.edit_text_event_title)
            val editTextDescription = findViewById<EditText>(R.id.edit_text_event_description)
            val title = editTextTitle.text.toString()
            val date = editTextDate.text.toString()
            val description = editTextDescription.text.toString()

            // Add new event to file
            val event = Event(title, date, description)
            addEventToFile(event)

            // Return to main activity
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addEventToFile(event: Event) {
        val file = File(filesDir, "events.txt")
        val fileWriter = FileWriter(file, true)
        val printWriter = PrintWriter(fileWriter)

        printWriter.println("${event.title},${event.date},${event.description}")

        printWriter.close()
        fileWriter.close()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            val selectedDate = "${getMonthName(month)} $dayOfMonth $year"
            editTextDate.setText(selectedDate)
        }, year, month, day)
        datePickerDialog.show()
    }

    private fun getMonthName(month: Int): String {
        val formatter = SimpleDateFormat("MMMM",Locale.US)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        return formatter.format(calendar.time)
    }
}
