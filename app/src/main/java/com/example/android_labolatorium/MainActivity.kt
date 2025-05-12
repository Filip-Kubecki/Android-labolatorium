package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import android.media.MediaParser
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.math.round
import androidx.core.graphics.toColorInt
import org.w3c.dom.Text
import java.util.Locale
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
//  Minimalna krajowa brutto
    private val minimalPay = 4666
    private var currentGender = true // true male false female

    private lateinit var salary: TextView
    private lateinit var employerPer: TextView
    private lateinit var employeePer: TextView
    private lateinit var age: TextView
    private lateinit var gender: ImageView
    private lateinit var savings: TextView

    private lateinit var employerPerSeekBar: SeekBar
    private lateinit var employeePerSeekBar: SeekBar




    @SuppressLint("MissingInflatedId", "NewApi", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        employerPerSeekBar = findViewById(R.id.employerSeekBar)
        employerPerSeekBar.max = 25
        employerPerSeekBar.min = 5
        employerPerSeekBar.progress = 15
        employerPerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                employerPer.text = (employerPerSeekBar.progress/10.0).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        employeePerSeekBar = findViewById(R.id.employeeSeekBar)
        employeePerSeekBar.max = 25
        employeePerSeekBar.min = 5
        employeePerSeekBar.progress = 20
        employeePerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                employeePer.text = (employeePerSeekBar.progress/10.0).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        salary = findViewById(R.id.salaryTextView)

        employeePer = findViewById(R.id.employeePercent)
        employerPer = findViewById(R.id.employerPercent)

        savings = findViewById(R.id.savingsTextView)

        age = findViewById(R.id.ageTextView)
        gender = findViewById(R.id.genderImage)
        gender.setBackgroundResource(R.drawable.male_symbol)
    }

    @SuppressLint("SetTextI18n")
    fun subAge(view: View) {
        age.text = (age.text.toString().toInt() - 1).toString()
    }

    @SuppressLint("SetTextI18n")
    fun addAge(view: View) {
        age.text = (age.text.toString().toInt() + 1).toString()
    }

    fun changeGender(view: View) {
        currentGender = !currentGender
        if (currentGender){
            gender.setBackgroundResource(R.drawable.male_symbol)
        }else{
            gender.setBackgroundResource(R.drawable.female_symbol)
        }
    }

    @SuppressLint("SetTextI18n")
    fun calculatePpk(view: View) {
        val ePer = employerPer.text.toString().toDouble()
        val eePer = employeePer.text.toString().toDouble()
        val salary = salary.text.toString().toDouble()
        val age = age.text.toString().toInt()

        val timeToPension = if (currentGender) 65 - age else 60 - age
        val tmp = (((salary*ePer)/100+(salary*eePer)/100)*12*timeToPension)

        savings.text = numberFormater(tmp)
    }

    fun numberFormater(value: Double):String{
//        var tmp = ""
//        var i = 0
//        value.roundToInt().toString().forEach { it ->
//            tmp = tmp+it
//            if (i % 3 == 0){
//                tmp = "$tmp "
//            }
//            i++
//        }
//
//        return tmp
        return value.toString()
    }
}

