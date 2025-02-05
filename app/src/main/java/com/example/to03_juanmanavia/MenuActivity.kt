package com.example.to03_juanmanavia

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.to03_juanmanavia.ejercicio1.MainActivity
import com.example.to03_juanmanavia.ejercicio2.MainActivity2

class MenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnPrimeraApp = findViewById<Button>(R.id.btnPrimeraApp)
        val btnSegundaApp = findViewById<Button>(R.id.btnSegundaApp)

        btnPrimeraApp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSegundaApp.setOnClickListener {
val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)}
    }
}