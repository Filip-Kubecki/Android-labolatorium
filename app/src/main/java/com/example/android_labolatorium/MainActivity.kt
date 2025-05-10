package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.math.round
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {

//    Unit setting
    private lateinit var weightUnit: Spinner
    private lateinit var heightUnit: Spinner

//    Number inputs
    private lateinit var weightTextEdit: EditText
    private val weightUnits = listOf("kg", "lb")
    private lateinit var heightTextEdit: EditText
    private val heightUnits = listOf("cm", "inch", "m")

//    Number output
    private lateinit var bmiTextView: TextView
//    private lateinit var bmiProgress: ProgressBar

    private lateinit var calcButton: Button

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

//        Init Unit Spinners
        weightUnit = findViewById(R.id.weightUnit)
        val weightAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightUnits)
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightUnit.adapter = weightAdapter
//        weightUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
//
//        }
        heightUnit = findViewById(R.id.heightUnit)
        val heightAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, heightUnits)
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightUnit.adapter = heightAdapter

//        Init input EditTexts
        weightTextEdit = findViewById(R.id.editTextWeight)
        heightTextEdit = findViewById(R.id.editTextHeight)

//        Init output TextView
        bmiTextView = findViewById(R.id.textViewBMI)
//        bmiProgress = findViewById(R.id.progressBarBmi)

        calcButton = findViewById(R.id.calculateButton)
    }

    fun calculateBMI(view: View) {
        if((weightTextEdit.text.toString() == "") || (heightTextEdit.text.toString() == "")){
            val errorPopup = Snackbar.make(view, R.string.errorNoInput, Snackbar.LENGTH_SHORT)
            errorPopup.show()

            bmiTextView.text = "error"
        }else{
            val weight = weightTextEdit.text.toString().toDouble()
            val height = heightTextEdit.text.toString().toDouble()/100
            val bmi =  weight/(height*height)

            bmiTextView.text = bmi.round(2).toString()
//            bmiProgress.progress = bmi.toInt()

            if(bmi < 18){
                bmiTextView.setBackgroundColor("#5ac1e9".toColorInt())
            }else if(bmi < 25){
                bmiTextView.setBackgroundColor("#5ec982".toColorInt())
            }else if(bmi < 30){
                bmiTextView.setBackgroundColor("#ffd701".toColorInt())
            }else if(bmi < 40){
                bmiTextView.setBackgroundColor("#fe8d00".toColorInt())
            }else{
                bmiTextView.setBackgroundColor("#ff0100".toColorInt())
            }

        }
    }

}

private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}
