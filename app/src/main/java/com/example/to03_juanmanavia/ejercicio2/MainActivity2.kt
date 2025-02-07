package com.example.to03_juanmanavia.ejercicio2


import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.to03_juanmanavia.R
import okhttp3.*
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private val URL_CAMBIO = "https://raw.githubusercontent.com/JuanmaNaviaOrtega/To03_JuanmaNavia/master/app/src/main/java/com/example/to03_juanmanavia/ejercicio2/cambio.txt"
    private val client = OkHttpClient()
    private var tasaCambio: Double = 1.0

    private lateinit var edtCantidad: EditText
    private lateinit var rbDolarAEuro: RadioButton
    private lateinit var rbEuroADolar: RadioButton
    private lateinit var txtResultado: TextView
    private lateinit var btnConvertir: Button
    private  lateinit var txtTasaCambio : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        edtCantidad = findViewById(R.id.edtCantidad)
        rbDolarAEuro = findViewById(R.id.rbDolarAEuro)
        rbEuroADolar = findViewById(R.id.rbEuroADolar)
        txtResultado = findViewById(R.id.txtResultado)
        btnConvertir = findViewById(R.id.btnConvertir)
        txtTasaCambio = findViewById(R.id.txtTasaCambio)

        // Descargar la tasa de cambio al iniciar la app
        descargarTasaDeCambio()

        //Agregar listener en el  Botón de conversión
        btnConvertir.setOnClickListener {
            val input = edtCantidad.text.toString()
            if (input.isNotEmpty()) {
                val cantidad = input.toDouble()
                val resultado: Double

                if (rbDolarAEuro.isChecked) {
                    resultado = cantidad * tasaCambio
                    txtResultado.text = "%.2f €".format(resultado)
                } else if (rbEuroADolar.isChecked) {
                    resultado = cantidad / tasaCambio
                    txtResultado.text = "%.2f $".format(resultado)
                } else {
                    Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun descargarTasaDeCambio() {
        val request = Request.Builder().url(URL_CAMBIO).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@MainActivity2, "Error al descargar la tasa de cambio", Toast.LENGTH_LONG).show()
                    Log.e("ERROR", "No se pudo descargar el archivo", e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity2, "Error en la respuesta del servidor", Toast.LENGTH_LONG).show()
                    }
                    return
                }

                val body = response.body?.string()?.trim()
                try {
                    tasaCambio = body?.toDouble() ?: 1.0
                    runOnUiThread {
                        txtTasaCambio.text = "%.2f".format(tasaCambio)
                        Toast.makeText(this@MainActivity2, "Tasa de cambio actualizada: $tasaCambio", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NumberFormatException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity2, "Formato incorrecto en el archivo", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}
