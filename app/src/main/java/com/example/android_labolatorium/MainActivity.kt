package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    private lateinit var counterText: TextView

    private lateinit var addOneButton: Button
    private lateinit var subOneButton: Button
    private lateinit var resetButton: Button

    var currentNumber = 0
    private final val message = "Licznik wciśnięć przycisku:"

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


            counterText = findViewById(R.id.counterText)
            addOneButton = findViewById(R.id.addOneButton)
            subOneButton = findViewById(R.id.subOneButton)
            resetButton = findViewById(R.id.resetButton)

            counterText.text = message + "\n" + currentNumber.toString()
    }

    @SuppressLint("SetTextI18n", "ShowToast")
    fun reset(view: View) {
        currentNumber = 0
        counterText.text = message + "\n" + currentNumber.toString()
        val toast = Toast.makeText(this, "Zresetowano licznik", Toast.LENGTH_LONG)
        toast.show()
    }

    @SuppressLint("SetTextI18n")
    fun addOne(view: View) {
        currentNumber += 1
        counterText.text = message + "\n" + currentNumber.toString()
    }

    @SuppressLint("SetTextI18n")
    fun subOne(view: View) {
        currentNumber -= 1
        counterText.text = message + "\n" + currentNumber.toString()
    }


}
