package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var activityButton: Button
    private lateinit var activityTextView: TextView

    private lateinit var coinImageView: ImageView
    private lateinit var coinImageView2: ImageView
    private lateinit var coinImageView3: ImageView
    private lateinit var coinImageView4: ImageView


    private val baseString = "You got: "
    private var currentSide = mutableListOf(true, true, true, true) // true - orzeł, false - reszka

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

        coinImageView2 = findViewById(R.id.coinImageView2)
        coinImageView2.setImageResource(R.drawable.pln_awers)

        coinImageView3 = findViewById(R.id.coinImageView3)
        coinImageView3.setImageResource(R.drawable.pln_awers)

        coinImageView4 = findViewById(R.id.coinImageView4)
        coinImageView4.setImageResource(R.drawable.pln_awers)
    }


    fun randomActivity(view: View) {
        activityTextView.text = baseString
        val tmp = Toast.makeText(this, "Losowanie", Toast.LENGTH_LONG)
        tmp.show()


        var randomNumber = (3..8).random()
        rotateCoin(randomNumber, coinImageView, 0)

        randomNumber = (3..8).random()
        rotateCoin(randomNumber, coinImageView2, 1)

        randomNumber = (3..8).random()
        rotateCoin(randomNumber, coinImageView3, 2)

        randomNumber = (3..8).random()
        rotateCoin(randomNumber, coinImageView4, 3)
    }

    @SuppressLint("SetTextI18n")
    private fun rotateCoin(n: Int, coinImageView: ImageView, side: Int){
        val time = (150..210).random().toLong()
        coinImageView.animate().apply {
            duration = time
            rotationXBy(90f)
        }.withEndAction(){
            if (currentSide[side]){
                coinImageView.setImageResource(R.drawable.pln_rewers)
            }else{
                coinImageView.setImageResource(R.drawable.pln_awers)
            }
            currentSide[side] = !currentSide[side]

            coinImageView.animate().apply {
                duration = time
                rotationXBy(90f)
            }.withEndAction(){
                if(n-1 > 0){
                    rotateCoin(n-1, coinImageView, side)
                }else{
                    if (currentSide[side])
                        activityTextView.text  = "${activityTextView.text}\n Coin ${side+1} head"
                    else
                        activityTextView.text  = "${activityTextView.text}\n Coin ${side+1} tail"
                }
            }.start()
        }.start()
    }
}



