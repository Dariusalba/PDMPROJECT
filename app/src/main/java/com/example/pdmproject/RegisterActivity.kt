package com.example.pdmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                if (registerUser(username, password)) {
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(username: String, password: String): Boolean {
        val userFile = File(filesDir, "users.txt")

        if (userFile.exists()) {
            val existingUsers = userFile.readText()
            val users = existingUsers.split("\n")

            for (user in users) {
                val userInfo = user.split(",")
                if (userInfo.size == 2 && userInfo[0] == username) {
                    return false // Username already exists
                }
            }
        }

        try {
            FileOutputStream(userFile, true).bufferedWriter().use { writer ->
                writer.write("$username,$password\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }

        return true
    }
}
