package com.example.myownapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
class GradesActivity : AppCompatActivity() {
    private lateinit var searchBox: EditText
    private lateinit var searchButton: Button
    private lateinit var gradesContainer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grades_card)
        searchBox = findViewById(R.id.search_box)
        searchButton = findViewById(R.id.search_button)
        gradesContainer = findViewById(R.id.grades_container)
        searchButton.setOnClickListener {
            // Replace this with your own database query to get the student's grades
            val grades = getGradesForStudent(searchBox.text.toString())
            // Clear the current grades container
            // Loop through the grades and create a card view for each one
            for (grade in grades) {
                val cardView = layoutInflater.inflate(R.layout.card_view_grade, null) as CardView
                val subjectName = cardView.findViewById<TextView>(R.id.subject_name)
                val gradeTextView = cardView.findViewById<TextView>(R.id.grade)
                subjectName.text = grade.subjectName
                gradeTextView.text = "Grade: ${grade.grade}"
                gradesContainer.addView(cardView)
            }
        }
    }
    private fun getGradesForStudent(studentName: String): List<Grade> {
        // Replace this with your own database query to get the student's grades
        return listOf(
            Grade("Android", "A+"),
            Grade("Web Dev", "A-"),
            Grade("English", "A"),
            Grade("DSA", "C+")
        )
    }
    data class Grade(val subjectName: String, val grade: String)
}