package com.example.android_labolatorium

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.math.round

class BMIcalc : AppCompatActivity() {
    //    Unit setting
    private lateinit var weightUnitSpinner: Spinner
    private lateinit var heightUnitSpinner: Spinner

    //    Number inputs
    private lateinit var weightTextEdit: EditText
    private var weightUnits = listOf(
        WeightUnits.kg,
        WeightUnits.lb
    )
    private var selectedWeightUnit = WeightUnits.kg
    private lateinit var heightTextEdit: EditText
    private val heightUnits = listOf(
        HeightUnits.cm,
        HeightUnits.inch,
        HeightUnits.m
    )
    private var selectedHeightUnit = HeightUnits.cm

    //    Number output
    private lateinit var bmiTextView: TextView

    private lateinit var calcButton: Button

    @SuppressLint("MissingInflatedId", "NewApi", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SELF LOG", "CREATE BMI CALC")

        enableEdgeToEdge()
        setContentView(R.layout.activity_bmicalc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        Init input EditTexts
        weightTextEdit = findViewById(R.id.editTextWeight)
        heightTextEdit = findViewById(R.id.editTextHeight)
//        heightTextEdit.setText(selectedHeightUnit.toString())

//        Init Unit Spinners
        weightUnitSpinner = findViewById(R.id.weightUnit)
        val weightAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, weightUnits)
        weightAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        weightUnitSpinner.adapter = weightAdapter
        weightUnitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentVal = weightTextEdit.text.toString().toDouble()
                val currentUnit = weightUnitSpinner.selectedItem as WeightUnits

                weightTextEdit.setText(when(currentUnit){
                    WeightUnits.kg -> {
                        lbToKg(currentVal).round(2).toString()
                    }
                    WeightUnits.lb -> {
                        kgToLb(currentVal).round(2).toString()
                    }
                })

                selectedWeightUnit = weightUnitSpinner.selectedItem as WeightUnits
            }
        }

        heightUnitSpinner = findViewById(R.id.heightUnit)
        val heightAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, heightUnits)
        heightAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        heightUnitSpinner.adapter = heightAdapter
        heightUnitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentVal = heightTextEdit.text.toString().toDouble()
                val currentUnit = heightUnitSpinner.selectedItem as HeightUnits

                if(selectedHeightUnit == HeightUnits.cm){
                    heightTextEdit.setText(when (currentUnit) {
                        HeightUnits.m -> {cmToM(currentVal).round(3).toString()}
                        HeightUnits.inch -> {cmToInch(currentVal).round(3).toString()}
                        else -> {"180.0"}
                    })
                }else if(selectedHeightUnit == HeightUnits.m){
                    heightTextEdit.setText(when (currentUnit) {
                        HeightUnits.cm -> {mToCm(currentVal).round(2).toString()}
                        HeightUnits.inch -> {mToInch(currentVal).round(2).toString()}
                        else -> {"180.0"}
                    })
                }else if(selectedHeightUnit == HeightUnits.inch){
                    heightTextEdit.setText(when (currentUnit) {
                        HeightUnits.cm -> {inchToCm(currentVal).round(3).toString()}
                        HeightUnits.m -> {inchToM(currentVal).round(3).toString()}
                        else -> {"180.0"}
                    })
                }

                selectedHeightUnit = heightUnitSpinner.selectedItem as HeightUnits
            }
        }

//        Init output TextView
        bmiTextView = findViewById(R.id.textViewBMI)
        calcButton = findViewById(R.id.calculateButton)
    }


    override fun onPause() {
        super.onPause()
        Log.d("SELF LOG", "PAUSED BMI CALC")
    }

    override fun onResume() {
        super.onResume()
        Log.d("SELF LOG", "RESUMED BMI CALC")
    }

    override fun onStop() {
        super.onStop()
        Log.d("SELF LOG", "STOPPED BMI CALC")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SELF LOG", "DESTROYED BMI CALC")
    }

    @SuppressLint("SetTextI18n")
    fun calculateBMI(view: View) {
        if((weightTextEdit.text.toString() == "") || (heightTextEdit.text.toString() == "")){
            val errorPopup = Snackbar.make(view, R.string.errorNoInput, Snackbar.LENGTH_SHORT)
            errorPopup.show()

            bmiTextView.text = "Error"
        }else{
            var weight = weightTextEdit.text.toString().toDouble()
            var height = heightTextEdit.text.toString().toDouble()

            if(weightUnitSpinner.selectedItem == WeightUnits.lb)
                weight = lbToKg(weight)

            height = when(heightUnitSpinner.selectedItem as HeightUnits){
                HeightUnits.cm -> height/100
                HeightUnits.m -> height
                HeightUnits.inch -> inchToM(height)
            }

            val bmi =  (weight/(height*height)).round(2)

            bmiTextView.text = bmi.round(2).toString()


            if(bmi < 18){
                bmiTextView.setBackgroundColor("#5ac1e9".toColorInt())
                bmiTextView.setBackgroundResource(R.drawable.under_weight_circle)
            }else if(bmi < 25){
                bmiTextView.setBackgroundColor("#5ec982".toColorInt())
                bmiTextView.setBackgroundResource(R.drawable.normal_weight_circle)
            }else if(bmi < 30){
                bmiTextView.setBackgroundColor("#ffd701".toColorInt())
                bmiTextView.setBackgroundResource(R.drawable.overweight_weight_circle)
            }else if(bmi < 40){
                bmiTextView.setBackgroundColor("#fe8d00".toColorInt())
                bmiTextView.setBackgroundResource(R.drawable.obese_weight_circle)
            }else{
                bmiTextView.setBackgroundColor("#ff0100".toColorInt())
                bmiTextView.setBackgroundResource(R.drawable.morbidly_obese_weight_circle)
                bmiTextView.setTextColor("#ffffff".toColorInt())
            }

        }
    }
}

private fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

private enum class WeightUnits{
    kg, lb;
}

private enum class HeightUnits{
    cm, m, inch;
}

private fun kgToLb(value: Double): Double{
    return value * 2.2046
}

private fun lbToKg(value: Double): Double{
    return value * 0.453592
}

private fun cmToInch(value: Double): Double{
    return value / 2.54
}

private fun inchToCm(value: Double): Double{
    return value * 2.54
}

private fun cmToM(value: Double): Double{
    return value * 0.01
}

private fun mToCm(value: Double): Double{
    return value * 100
}

private fun inchToM(value: Double): Double{
    return cmToM(inchToCm(value))
}

private fun mToInch(value: Double): Double{
    return cmToInch(mToCm(value))
}
