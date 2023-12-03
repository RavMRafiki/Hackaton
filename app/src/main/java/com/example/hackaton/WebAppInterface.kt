package com.example.hackaton

import android.content.Context
import android.webkit.JavascriptInterface
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.nio.charset.StandardCharsets

class WebAppInterface(
//    private val mContext: Context
) {

//    var data: String? = null

    @JavascriptInterface
    fun sendData(material: String, isolation: String, coresLoaded: String, typeOfInstalation: String, temperature: String, soilResistivity: String, power: String, cos: Double): String {
        val url = "http://ec2-54-227-117-32.compute-1.amazonaws.com:8080/filter-cables"
        var response: String = ""
        try {
            val urlObject = java.net.URL(url)
            val connection = urlObject.openConnection() as HttpURLConnection
            with(connection) {
                val requestMethod = "POST"
                val doOutput = true
                setRequestMethod(requestMethod)
                setDoOutput(doOutput)
                setRequestProperty("Content-Type", "application/json")

                val postData =  """
    {
            "material": "${material}",
            "isolation": "${isolation}",
            "coresLoaded": "${coresLoaded}",
            "typeOfInstalation": "${typeOfInstalation}",
            "temperature": "${temperature}",
            "soilResistivity": "${soilResistivity}",
            "power": "${power}",
            "cos": "${cos}"
    }
""".trimIndent()
println(postData)
                OutputStreamWriter(outputStream, StandardCharsets.UTF_8).use { writer ->
                    writer.write(postData)
                }

                val responseCode = responseCode
                println("Response Code: $responseCode")

                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    inputStream.bufferedReader(StandardCharsets.UTF_8).use {
//                        println("Response: ${it.readText()}")
//                    }
                    val jsonResponse = inputStream.bufferedReader(StandardCharsets.UTF_8).use {
                        it.readText()
                    }
                    response = jsonResponse
                } else {
                    println("POST request failed")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}
