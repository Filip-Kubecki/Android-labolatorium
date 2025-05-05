package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
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
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private lateinit var activityButton: Button
    private lateinit var activityTextView: TextView

    private val baseString = "The selected activity is:"


    private var list = listOf(
        "Abdominal bench",
        "Eliptical",
        "Monkey bars",
        "Stationary bike",
        "Treadmill",
        "Yoga",
        "Bober1",
        "Bober2",
        "Bober3"
    )

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


        activityButton = findViewById(R.id.activityButton)
        activityTextView = findViewById(R.id.activityTextView)

        activityTextView.text = baseString
    }


    private fun varToBool(value: String): Boolean{
        return if (value == "ON") true else {
            if(value == "OFF"){
                false
            }else{
                throw IllegalArgumentException("Wrong value")
            }
        }
    }

    private fun boolToToggleString(value: Boolean): String{
        return if(value) "ON" else "OFF"
    }

    @SuppressLint("SetTextI18n")
    fun randomActivity(view: View) {
        val tmp = Toast.makeText(this, "Losu losu losu...", Toast.LENGTH_LONG)
        tmp.show()

        val hand: Handler = Handler()
        hand.run {
            postDelayed(Runnable() {
                activityTextView.text = baseString+"\n"+list.random()
            }, 3000)
        }
    }
}



