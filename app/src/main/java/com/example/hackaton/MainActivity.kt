package com.example.hackaton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView: WebView = findViewById(R.id.webview)
        val webAppInterface = WebAppInterface()

        webView.addJavascriptInterface(webAppInterface, "Android")
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/index.html")

    }
}