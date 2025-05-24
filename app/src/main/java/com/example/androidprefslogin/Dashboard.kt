package com.example.androidprefslogin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidprefslogin.databinding.ActivityDashboardBinding
import androidx.core.content.edit

class Dashboard : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE)

        val username = sharedPreferences.getString("username", "username not found")
        val password = sharedPreferences.getString("password", "password not found")

        binding.textViewPrint.text = "Username: $username \nPassword: $password"

        binding.btnLogout.setOnClickListener {
            sharedPreferences.edit {
                remove("username")
                remove("password")
            }

            startActivity(Intent(this@Dashboard, MainActivity::class.java))
            finish()
        }
    }
}