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

        val calculateButton: Button = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val ageEditText: EditText = findViewById(R.id.ageEditText)
            val heightEditText: EditText = findViewById(R.id.heightEditText)
            val weightEditText: EditText = findViewById(R.id.weightEditText)
            val activityLevelRadioGroup: RadioGroup = findViewById(R.id.activityLevelRadioGroup)
            val goalRadioGroup: RadioGroup = findViewById(R.id.goalRadioGroup)

            val age: Int = ageEditText.text.toString().toInt()
            val height: Int = heightEditText.text.toString().toInt()
            val weight: Double = weightEditText.text.toString().toDouble()

            val activityLevel: Double = when (activityLevelRadioGroup.checkedRadioButtonId) {
                R.id.sedentaryRadioButton -> 1.2
                R.id.lightlyActiveRadioButton -> 1.375
                R.id.veryActiveRadioButton -> 1.725
                else -> 1.2
            }

            val goal: Double = when (goalRadioGroup.checkedRadioButtonId) {
                R.id.loseWeightRadioButton -> -0.2
                R.id.maintainWeightRadioButton -> 0.0
                R.id.gainWeightRadioButton -> 0.2
                else -> 0.0
            }

            val bmr: Double = calculateBMR(age, height, weight)
            val dailyCalorieIntake: Int = (bmr * activityLevel * (1.0 + goal)).toInt()

            val intent = Intent(this, CalorieIntakeActivity::class.java)
            intent.putExtra("CALORIE_INTAKE", dailyCalorieIntake)
            startActivity(intent)
        }
    }
    private fun calculateBMR(age: Int, height: Int, weight: Double): Double {
        return 10 * weight + 6.25 * height - 5 * age + 5
    }
}
