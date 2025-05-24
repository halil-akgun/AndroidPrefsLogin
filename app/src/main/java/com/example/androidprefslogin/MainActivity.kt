package com.example.androidprefslogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidprefslogin.databinding.ActivityMainBinding
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        val prefsUsername = sharedPreferences.getString("username", "username not found")
        val prefsPassword = sharedPreferences.getString("password", "password not found")

        if (prefsUsername == "admin" && prefsPassword == "1234") {
            startActivity(Intent(this@MainActivity, Dashboard::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {

            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (username == "admin" && password == "1234") {
                sharedPreferences.edit {
                    putString("username", username)
                    putString("password", password)
                }

                startActivity(Intent(this@MainActivity, Dashboard::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
}