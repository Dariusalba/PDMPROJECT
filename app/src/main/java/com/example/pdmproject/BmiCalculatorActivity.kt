package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BmiCalculatorActivity : AppCompatActivity() {

    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)

        heightEditText = findViewById(R.id.height_edit_text)
        weightEditText = findViewById(R.id.weight_edit_text)
        calculateButton = findViewById(R.id.calculate_button)
        resultTextView = findViewById(R.id.result_text_view)

        calculateButton.setOnClickListener {
            val height = heightEditText.text.toString().toFloatOrNull()
            val weight = weightEditText.text.toString().toFloatOrNull()

            if (height != null && weight != null) {
                val bmi = calculateBMI(height, weight)
                val resultText = "Your BMI: $bmi"
                resultTextView.text = resultText
            } else {
                Toast.makeText(this, "Please enter valid height and weight", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculateBMI(height: Float, weight: Float): Float {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }
}
