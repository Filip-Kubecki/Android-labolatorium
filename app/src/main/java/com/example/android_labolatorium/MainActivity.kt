package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.core.view.marginBottom
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {


    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


            textView = findViewById(R.id.textView)
            editText = findViewById(R.id.editTextText)
            button  = findViewById(R.id.button)
    }

    fun onClick(view: View) {
        textView.text = editText.text
        val toast = Toast.makeText(this, "Podano imię i nazwisko:\n"+editText.text, Toast.LENGTH_LONG)
        toast.show()
    }


}
