package com.example.to03_juanmanavia.ejercicio1

import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.to03_juanmanavia.R
import com.example.to03_juanmanavia.databinding.ActivityMainBinding
import java.io.File
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var urlEditText: EditText
    private lateinit var downloadButton: Button
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var timeTextView: TextView

    private var startTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        urlEditText = findViewById(R.id.urlEditText)
        downloadButton = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        timeTextView = findViewById(R.id.timeTextView)

        downloadButton.setOnClickListener {
            val url = urlEditText.text.toString()
            if (url.isNotEmpty()) {
                startTime = System.currentTimeMillis()
                DownloadFileTask(this).execute(url)
            } else {
                Toast.makeText(this, "Por favor, ingrese una URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showDownloadResult(file: File?) {
        val endTime = System.currentTimeMillis()
        val downloadTime = (endTime - startTime) / 1000.0
        timeTextView.text = "$downloadTime segundos"

        if (file != null) {
            val fileName = file.name
            if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                imageView.visibility = View.VISIBLE
                textView.visibility = View.GONE
                imageView.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
            } else if (fileName.endsWith(".html") || fileName.endsWith(".txt")) {
                imageView.visibility = View.GONE
                textView.visibility = View.VISIBLE

                val content = FileUtils.readFileContent(file)
                textView.text = content

                // Detectar enlaces en el contenido y hacerlos clickeables
                val urlPattern = Pattern.compile("(http|https)://[\\w\\-\\.]+\\.[a-z]{2,3}(/[\\w\\-\\.?=%&]*)?")
                Linkify.addLinks(textView, urlPattern, null)

                // Habilitar la interacción con los enlaces
                textView.movementMethod = LinkMovementMethod.getInstance()
            }

            Toast.makeText(this, "Descarga completada: $fileName", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error en la descarga", Toast.LENGTH_SHORT).show()
        }
    }
}
