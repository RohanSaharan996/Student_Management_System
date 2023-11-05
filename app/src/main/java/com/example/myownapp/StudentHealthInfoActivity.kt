package com.example.myownapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class StudentHealthInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.health_card)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val ageTextView = findViewById<TextView>(R.id.ageTextView)
        val heightTextView = findViewById<TextView>(R.id.heightTextView)
        val weightTextView = findViewById<TextView>(R.id.weightTextView)
        val bloodTypeTextView = findViewById<TextView>(R.id.bloodTypeTextView)

        // Replace the placeholder values with actual data from the student's health record
        nameTextView.text = "Name: John Doe"
        ageTextView.text = "Age: 18"
        heightTextView.text = "Height: 170 cm"
        weightTextView.text = "Weight: 60 kg"
        bloodTypeTextView.text = "Blood type: O+"
    }
}
