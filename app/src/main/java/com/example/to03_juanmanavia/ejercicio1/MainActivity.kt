package com.example.to03_juanmanavia.ejercicio1

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.to03_juanmanavia.R
import com.example.to03_juanmanavia.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var urlEditText: EditText
    private lateinit var downloadButton: Button
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        urlEditText = findViewById(R.id.urlEditText)
        downloadButton = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)

        downloadButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                DownloadFileTask(this).execute(url)
            } else {
                Toast.makeText(this, "Por favor, ingrese una URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showDownloadResult(file: File?) {
        if (file != null) {
            val fileName = file.name
            when {
                fileName.endsWith(".jpg") || fileName.endsWith(".png") -> {
                    imageView.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
                }
                fileName.endsWith(".html") || fileName.endsWith(".txt") -> {
                    imageView.visibility = View.GONE
                    textView.visibility = View.VISIBLE
                    textView.text = FileUtils.readFileContent(file)
                }
            }
            Toast.makeText(this, "Descarga completada: $fileName", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error en la descarga", Toast.LENGTH_SHORT).show()
        }
    }
}