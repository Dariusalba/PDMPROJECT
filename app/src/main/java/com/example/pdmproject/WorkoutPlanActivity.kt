package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WorkoutPlanActivity : AppCompatActivity() {

    private lateinit var workoutPlanTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_plan)

        workoutPlanTextView = findViewById(R.id.workout_plan_text_view)

        val workoutPlan = generateWorkoutPlan()

        workoutPlanTextView.text = workoutPlan
    }

    private fun generateWorkoutPlan(): String {
        val exerciseList = listOf(
            "Squats",
            "Push-ups",
            "Lunges",
            "Plank",
            "Jumping Jacks",
            "Mountain Climbers",
            "Burpees",
            "Russian Twists",
            "High Knees",
            "Wall Sits"
        )

        val workoutDurationMinutes = 60
        val exerciseDurationSeconds = 60
        val numExercises = exerciseList.size

        val exerciseTime = exerciseDurationSeconds * numExercises
        val restTime = workoutDurationMinutes * 60 - exerciseTime

        val workoutPlan = StringBuilder()
        workoutPlan.append("Your workout plan:\n\n")

        for (i in 0 until numExercises) {
            val exercise = exerciseList[i]
            workoutPlan.append("Exercise ${i + 1}: $exercise\n")
            workoutPlan.append("Duration: $exerciseDurationSeconds seconds\n\n")
        }

        if (restTime > 0) {
            workoutPlan.append("Rest for $restTime seconds\n\n")
        }

        return workoutPlan.toString()
    }
}
