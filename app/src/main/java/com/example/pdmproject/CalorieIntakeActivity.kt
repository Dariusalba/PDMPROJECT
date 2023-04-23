package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalorieIntakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_intake)

        val dailyCalorieIntake = intent.getDoubleExtra("DAILY_CALORIE_INTAKE", 0.0)

        val calorieIntakeTextView = findViewById<TextView>(R.id.calorieIntakeTextView)

        calorieIntakeTextView.text = "Your daily calorie intake should be: $dailyCalorieIntake"

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }
    }
}