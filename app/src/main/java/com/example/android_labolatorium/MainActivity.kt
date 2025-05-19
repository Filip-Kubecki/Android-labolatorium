package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "NewApi", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SELF LOG", "CREATED MAIN")

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, BMIcalc::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    override fun onStop() {
        super.onStop()
        Log.d("SELF LOG", "STOPPED MAIN")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SELF LOG", "DESTROYED MAIN")
    }
}

