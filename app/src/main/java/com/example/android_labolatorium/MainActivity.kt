package com.example.android_labolatorium

import android.os.Bundle
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.android_labolatorium.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("SELFLOG", "CREATED")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onPause() {
        Log.d("SELFLOG", "PAUSED")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("SELFLOG", "DESTROY")
        super.onDestroy()
    }
}

