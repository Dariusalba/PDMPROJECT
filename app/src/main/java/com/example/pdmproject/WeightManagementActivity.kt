package com.example.pdmproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class WeightManagementActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_management)

        // Find the calculate button
        val calculateButton = findViewById<Button>(R.id.submitButton)

        // Set a click listener on the button
        calculateButton.setOnClickListener {
            // Get the user's weight management goal
            val goalRadioGroup = findViewById<RadioGroup>(R.id.goalRadioGroup)
            val selectedGoalId = goalRadioGroup.checkedRadioButtonId
            val selectedGoal = findViewById<RadioButton>(selectedGoalId).text.toString()

            // Get the user's age, height, and weight
            val ageEditText = findViewById<EditText>(R.id.ageEditText)
            val age = ageEditText.text.toString().toInt()

            val heightEditText = findViewById<EditText>(R.id.heightEditText)
            val height = heightEditText.text.toString().toDouble()

            val weightEditText = findViewById<EditText>(R.id.weightEditText)
            val weight = weightEditText.text.toString().toDouble()

            // Calculate the user's daily calorie intake based on their weight management goal
            val dailyCalorieIntake = if (selectedGoal == "Weight Loss") {
                (10 * weight) + (6.25 * height) - (5 * age) - 500
            } else {
                (10 * weight) + (6.25 * height) - (5 * age) + 500
            }

            // Create an Intent to start the CalorieIntakeActivity
            val intent = Intent(this, CalorieIntakeActivity::class.java)

            // Pass the daily calorie intake value to the CalorieIntakeActivity
            intent.putExtra("DAILY_CALORIE_INTAKE", dailyCalorieIntake)

            // Start the CalorieIntakeActivity
            startActivity(intent)
        }
    }
}
