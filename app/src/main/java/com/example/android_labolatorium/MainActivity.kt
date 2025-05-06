package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

class MainActivity : AppCompatActivity() {

    private lateinit var activityButton: Button
    //    private lateinit var activityTextView: TextView
    private lateinit var diceImageView: GifImageView
    private lateinit var gifDrawable: GifDrawable




    private val baseString = "You got: "
    private val baseToastString = "You rolled"

    @SuppressLint("MissingInflatedId", "NewApi")
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

//        activityTextView = findViewById(R.id.activityTextView)
//        activityTextView.text = baseString

        diceImageView = findViewById(R.id.diceGifImageView)

        gifDrawable = diceImageView.drawable as GifDrawable

        gifDrawable.stop()
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
        var resultToast = Toast.makeText(this, "3", Toast.LENGTH_LONG)
        var infoToast = Toast.makeText(this, "3", Toast.LENGTH_SHORT)
        infoToast.setText("Rolling rolling...")
        infoToast.show()

        val randomDelay:Long = ((1..5).random() * 500).toLong()

        gifDrawable.setSpeed(0.8f)
        gifDrawable.start()


        val tr = Handler()
        tr.run {
            postDelayed(
                {
                    infoToast.cancel()
                    gifDrawable.stop()
                    val index = gifDrawable.currentFrameIndex
                    var toastString = ""
                    if (index > 0){
                        toastString = "$baseToastString $index points!"
                    }else if(index == 0){
                        toastString = "$baseToastString 6 points!"
                    }else{
                        toastString = "Critical error"
                    }
                    resultToast.setText(toastString)
                    resultToast.show()
                }
                , randomDelay
            )
        }

    }

}
