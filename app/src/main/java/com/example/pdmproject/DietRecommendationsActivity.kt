package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DietRecommendationsActivity : AppCompatActivity() {

    private lateinit var dietTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_recommendations)

        dietTextView = findViewById(R.id.diet_text_view)

        val dietRecommendations = when (intent.getIntExtra("CALORIES", 0)) {
            in 1200..1500 -> "For a 1200-1500 calorie diet, try eating:\n\n" +
                    "- Breakfast: 1 cup oatmeal with 1/2 cup berries and 1 tbsp honey\n" +
                    "- Snack: 1 apple with 1 tbsp almond butter\n" +
                    "- Lunch: 2 cups mixed greens with 3 oz grilled chicken, 1/4 cup croutons, and 2 tbsp balsamic vinaigrette\n" +
                    "- Snack: 1 hard-boiled egg and 1 small orange\n" +
                    "- Dinner: 4 oz baked salmon with 1 cup roasted vegetables and 1/2 cup brown rice\n" +
                    "- Snack: 1/2 cup low-fat cottage cheese with 1/2 cup sliced peaches"
            in 1501..1800 -> "For a 1500-1800 calorie diet, try eating:\n\n" +
                    "- Breakfast: 2 slices whole grain toast with 2 tbsp peanut butter and 1 small banana\n" +
                    "- Snack: 1/2 cup grapes and 1 oz cheddar cheese\n" +
                    "- Lunch: 2 cups mixed greens with 3 oz grilled chicken, 1/4 cup croutons, and 2 tbsp Caesar dressing\n" +
                    "- Snack: 1/2 cup low-fat cottage cheese with 1/2 cup diced pineapple\n" +
                    "- Dinner: 4 oz baked chicken with 1 cup roasted vegetables and 1/2 cup quinoa\n" +
                    "- Snack: 1 small apple with 1 tbsp almond butter"
            in 1801..2100 -> "For a 1800-2100 calorie diet, try eating:\n\n" +
                    "- Breakfast: 1 cup Greek yogurt with 1/2 cup berries and 1/4 cup granola\n" +
                    "- Snack: 1/2 cup edamame\n" +
                    "- Lunch: 2 cups mixed greens with 3 oz grilled chicken, 1/4 cup croutons, and 2 tbsp balsamic vinaigrette\n" +
                    "- Snack: 1 small apple with 2 tbsp peanut butter\n" +
                    "- Dinner: 4 oz baked salmon with 1 cup roasted vegetables and 1/2 cup quinoa\n" +
                    "- Snack: 1 small banana with 1/2 cup low-fat cottage cheese"
            else -> "Sorry, no diet recommendations available for your calorie intake."
        }

        dietTextView.text = dietRecommendations

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }
}
