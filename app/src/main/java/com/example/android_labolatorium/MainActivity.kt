package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.Image
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
    private lateinit var coinImageView: ImageView


    private val baseString = "You got: "
    private var currentSide = true // true - orzeł, false - reszka

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

        coinImageView = findViewById(R.id.coinImageView)
        coinImageView.setImageResource(R.drawable.pln_awers)
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

    fun randomActivity(view: View) {

        val tmp = Toast.makeText(this, "Leć malysz leć!", Toast.LENGTH_LONG)
        tmp.show()

        val randomNumber = (1..6).random()
        rotateCoin(randomNumber)
    }

    private fun rotateCoin(n: Int){
        coinImageView.animate().apply {
            duration = 200
            rotationXBy(90f)
        }.withEndAction(){
            if (currentSide){
                coinImageView.setImageResource(R.drawable.pln_rewers)
            }else{
                coinImageView.setImageResource(R.drawable.pln_awers)
            }
            currentSide = !currentSide
            coinImageView.animate().apply {
                duration = 200
                rotationXBy(90f)
            }.withEndAction(){
                if(n-1 > 0){
                    rotateCoin(n-1)
                }else{
                    if (currentSide)
                        activityTextView.text = baseString+"head"
                    else
                        activityTextView.text = baseString+"tails"
                }
            }.start()
        }.start()
    }
}



