package com.example.to03_juanmanavia.ejercicio1

import android.os.Environment
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

object FileUtils {

    fun createFileInDownloads(fileName: String): File {
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        return File(storageDir, fileName)
    }

    fun readFileContent(file: File): String {
        val stringBuilder = StringBuilder()
        try {
            val reader = BufferedReader(FileReader(file))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}