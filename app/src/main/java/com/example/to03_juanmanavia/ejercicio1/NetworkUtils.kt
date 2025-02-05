package com.example.to03_juanmanavia.ejercicio1

import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

object NetworkUtils {

    fun downloadFile(fileUrl: String): File? {
        return try {
            val url = URL(fileUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.connect()

            // Obtener el nombre del archivo desde la URL
            val fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1)

            // Crear el archivo en la memoria externa
            val outputFile = FileUtils.createFileInDownloads(fileName)

            val inputStream = connection.inputStream
            val outputStream = FileOutputStream(outputFile)

            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } != -1) {
                outputStream.write(buffer, 0, length)
            }

            outputStream.close()
            inputStream.close()

            outputFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
