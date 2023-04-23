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

        val calculateButton = findViewById<Button>(R.id.submitButton)

        calculateButton.setOnClickListener {

            val goalRadioGroup = findViewById<RadioGroup>(R.id.goalRadioGroup)
            val selectedGoalId = goalRadioGroup.checkedRadioButtonId
            val selectedGoal = findViewById<RadioButton>(selectedGoalId).text.toString()


            val ageEditText = findViewById<EditText>(R.id.ageEditText)
            val age = ageEditText.text.toString().toInt()

            val heightEditText = findViewById<EditText>(R.id.heightEditText)
            val height = heightEditText.text.toString().toDouble()

            val weightEditText = findViewById<EditText>(R.id.weightEditText)
            val weight = weightEditText.text.toString().toDouble()

            val dailyCalorieIntake = if (selectedGoal == "Weight Loss") {
                (10 * weight) + (6.25 * height) - (5 * age) - 500
            } else {
                (10 * weight) + (6.25 * height) - (5 * age) + 500
            }

            val intent = Intent(this, CalorieIntakeActivity::class.java)

            intent.putExtra("DAILY_CALORIE_INTAKE", dailyCalorieIntake)

            startActivity(intent)
        }
    }
}
