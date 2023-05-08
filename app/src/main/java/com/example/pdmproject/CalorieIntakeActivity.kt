package com.example.pdmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalorieIntakeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_intake)

        val dailyCalorieIntake = intent.getIntExtra("CALORIE_INTAKE", 0)

        val calorieIntakeTextView = findViewById<TextView>(R.id.calorieIntakeTextView)
        val dietRecommendationButton = findViewById<Button>(R.id.dietRecommendationsButton)
        val dietRecommendationTextView = findViewById<TextView>(R.id.dietRecommendationTextView)

        calorieIntakeTextView.text = "Your daily calorie intake should be: $dailyCalorieIntake"

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        when (dailyCalorieIntake) {
            in 1200..1500 -> {
                dietRecommendationTextView.text = "Focus on low-calorie, nutrient-dense foods such as vegetables, fruits, lean protein sources, and whole grains. Aim for three meals and one or two snacks per day."
            }
            in 1500..1800 -> {
                dietRecommendationTextView.text = "Similar to the above, but with slightly larger portions and potentially more variety in the types of foods included."
            }
            in 1800..2200 -> {
                dietRecommendationTextView.text = "At this level, there's more room for healthy fats, carbohydrates, and protein. Focus on eating a variety of whole foods and limiting processed foods."
            }
            in 2200..2500 -> {
                dietRecommendationTextView.text = "At this level, there's more room for healthy fats, carbohydrates, and protein. Focus on eating a variety of whole foods and limiting processed foods, but with larger portions and potentially more frequent meals or snacks to meet calorie needs."
            }
            else -> {
                dietRecommendationTextView.text = "Consult a registered dietitian or healthcare provider for personalized dietary recommendations."
            }
        }

        // Set up the button to navigate to the DietRecommendationsActivity
        dietRecommendationButton.setOnClickListener {
            val intent = Intent(this, DietRecommendationsActivity::class.java)
            startActivity(intent)
        }
    }
}