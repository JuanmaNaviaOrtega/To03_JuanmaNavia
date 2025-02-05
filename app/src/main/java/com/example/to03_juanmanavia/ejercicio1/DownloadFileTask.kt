package com.example.to03_juanmanavia.ejercicio1

import android.content.Context
import android.os.AsyncTask
import java.io.File

class DownloadFileTask(private val context: Context) : AsyncTask<String, Void, File>() {

    override fun doInBackground(vararg urls: String): File? {
        val fileUrl = urls[0]
        return NetworkUtils.downloadFile(fileUrl)
    }

    override fun onPostExecute(file: File?) {
        if (context is MainActivity) {
            context.showDownloadResult(file)
        }
    }
}