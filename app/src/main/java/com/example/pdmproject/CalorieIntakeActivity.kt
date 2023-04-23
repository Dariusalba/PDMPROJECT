package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalorieIntakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_intake)

        // Retrieve the daily calorie intake value passed from the previous activity
        val dailyCalorieIntake = intent.getDoubleExtra("DAILY_CALORIE_INTAKE", 0.0)

        // Find the calorieIntakeTextView TextView
        val calorieIntakeTextView = findViewById<TextView>(R.id.calorieIntakeTextView)

        // Display the daily calorie intake value in the TextView
        calorieIntakeTextView.text = "Your daily calorie intake should be: $dailyCalorieIntake"

        // Find the backButton button and set a click listener to finish the activity and return to the previous activity
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}