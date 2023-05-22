package com.example.pdmproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class AddFoodActivity : AppCompatActivity() {

    private lateinit var foodNameEditText: EditText
    private lateinit var foodAmountEditText: EditText
    private lateinit var addMealButton: Button
    private lateinit var calculateCaloriesButton: Button
    private lateinit var caloriesResultTextView: TextView
    private lateinit var caloriesChart: BarChart

    private val mealsList = mutableListOf<Pair<String, Float>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        foodNameEditText = findViewById(R.id.food_name_edit_text)
        foodAmountEditText = findViewById(R.id.food_amount_edit_text)
        addMealButton = findViewById(R.id.add_meal_button)
        calculateCaloriesButton = findViewById(R.id.calculate_calories_button)
        caloriesResultTextView = findViewById(R.id.calories_result_text_view)
        caloriesChart = findViewById(R.id.calories_chart)

        addMealButton.setOnClickListener {
            val foodName = foodNameEditText.text.toString()
            val foodAmount = foodAmountEditText.text.toString().toFloatOrNull()

            if (foodAmount != null) {
                mealsList.add(Pair(foodName, foodAmount))
                Toast.makeText(this, "Meal added", Toast.LENGTH_SHORT).show()
                clearInputFields()
                updateCaloriesChart()
            } else {
                Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
            }
        }

        calculateCaloriesButton.setOnClickListener {
            val totalCalories = calculateTotalCalories()
            val resultText = "Total Calories: $totalCalories"
            caloriesResultTextView.text = resultText
        }

        val bmiCalculatorButton: Button = findViewById(R.id.bmi_calculator_button)
        bmiCalculatorButton.setOnClickListener {
            val intent = Intent(this, BmiCalculatorActivity::class.java)
            startActivity(intent)
        }

        val workoutPlanButton: Button = findViewById(R.id.workout_plan_button)
        workoutPlanButton.setOnClickListener {
            val intent = Intent(this, WorkoutPlanActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculateTotalCalories(): Int {
        var totalCalories = 0

        for (meal in mealsList) {
            val caloriesPer100g = 230
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

    private fun updateCaloriesChart() {
        val barEntries = mutableListOf<BarEntry>()
        var xAxisLabel = mutableListOf<String>()

        for ((index, meal) in mealsList.withIndex()) {
            val caloriesPer100g = 230
            val calories = ((meal.second / 100) * caloriesPer100g).toInt()
            barEntries.add(BarEntry(index.toFloat(), calories.toFloat()))
            xAxisLabel.add(meal.first)
        }

        val barDataSet = BarDataSet(barEntries, "Calories Consumed")
        val barData = BarData(barDataSet)

        barDataSet.color = resources.getColor(R.color.colorAccent)
        barData.barWidth = 0.5f

        val xAxis = caloriesChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f

        caloriesChart.setTouchEnabled(false)
        caloriesChart.isDragEnabled = false
        caloriesChart.setScaleEnabled(false)

        caloriesChart.data = barData
        caloriesChart.invalidate()
    }
}
