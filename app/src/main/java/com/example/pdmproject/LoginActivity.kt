package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidUser(username, password)) {
                val intent = Intent(this, WeightManagementActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        val registerButton: Button = findViewById(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun isValidUser(username: String, password: String): Boolean {
        val userFile = File(filesDir, "users.txt")

        if (userFile.exists()) {
            try {
                BufferedReader(FileReader(userFile)).use { reader ->
                    var line: String? = reader.readLine()

                    while (line != null) {
                        val userInfo = line.split(",")
                        if (userInfo.size == 2 && userInfo[0] == username && userInfo[1] == password) {
                            return true
                        }
                        line = reader.readLine()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return false
    }
}
