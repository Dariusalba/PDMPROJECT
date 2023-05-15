package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AddFoodActivity : AppCompatActivity() {

    private lateinit var foodNameEditText: EditText
    private lateinit var foodAmountEditText: EditText
    private lateinit var addMealButton: Button
    private lateinit var calculateCaloriesButton: Button
    private lateinit var caloriesResultTextView: TextView

    private val mealsList = mutableListOf<Pair<String, Float>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        foodNameEditText = findViewById(R.id.food_name_edit_text)
        foodAmountEditText = findViewById(R.id.food_amount_edit_text)
        addMealButton = findViewById(R.id.add_meal_button)
        calculateCaloriesButton = findViewById(R.id.calculate_calories_button)
        caloriesResultTextView = findViewById(R.id.calories_result_text_view)

        addMealButton.setOnClickListener {
            val foodName = foodNameEditText.text.toString()
            val foodAmount = foodAmountEditText.text.toString().toFloatOrNull()

            if (foodAmount != null) {
                mealsList.add(Pair(foodName, foodAmount))
                Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show()
                clearInputFields()
            } else {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }

        calculateCaloriesButton.setOnClickListener {
            val totalCalories = calculateTotalCalories()
            val resultText = "Total Calories: $totalCalories"
            caloriesResultTextView.text = resultText
        }
    }

    private fun calculateTotalCalories(): Int {
        var totalCalories = 0

        for (meal in mealsList) {
            val caloriesPer100g = 100 // Example: Assume 100 calories per 100 grams of food
            val calories = ((meal.second / 100) * caloriesPer100g).toInt()
            totalCalories += calories
        }

        val recommendedCalories = intent.getIntExtra("CALORIE_INTAKE", 0)

        if (totalCalories > recommendedCalories) {
            val difference = totalCalories - recommendedCalories
            val warningMessage = "You have exceeded your recommended daily calorie intake by $difference calories!"
            Toast.makeText(this, warningMessage, Toast.LENGTH_LONG).show()
        } else if (totalCalories < recommendedCalories) {
            val difference = recommendedCalories - totalCalories
            val warningMessage = "You are below your recommended daily calorie intake by $difference calories!"
            Toast.makeText(this, warningMessage, Toast.LENGTH_LONG).show()
        }

        return totalCalories
    }


    private fun clearInputFields() {
        foodNameEditText.text.clear()
        foodAmountEditText.text.clear()
    }
}
