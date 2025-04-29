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
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.core.view.marginBottom
import androidx.transition.Visibility

class MainActivity : AppCompatActivity() {

    //    Toggle Buttons
    private lateinit var varA: ToggleButton
    private lateinit var varB: ToggleButton
    private lateinit var result: ToggleButton

    private lateinit var gateImage: ImageView
    private var currentOperator = LogicOperators.AND

    private var list = listOf(
        LogicOperators.AND,
        LogicOperators.OR,
        LogicOperators.NOT,
        LogicOperators.NAND,
        LogicOperators.NOR,
        LogicOperators.XOR
    )
    private lateinit var spinner: Spinner

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

        varA = findViewById(R.id.varA)
        varB = findViewById(R.id.varB)
        result = findViewById(R.id.operationResult)

        spinner = findViewById(R.id.spinner2)
        val ad = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = ad
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currentOperator = spinner.selectedItem as LogicOperators
                gateImage.setImageResource(enumToImgResource(currentOperator))

//                When NOT gate is choosen
                if (currentOperator == LogicOperators.NOT){
                    varB.visibility = INVISIBLE
                }else{
                    varB.visibility = VISIBLE
                }
//                Dupochron
                if (view != null) {
                    onToggleClick(view)
                }
            }
        }

        gateImage = findViewById(R.id.gateImage)
        gateImage.setBackgroundResource(R.drawable.and)

    }

    private fun enumToImgResource(enum: LogicOperators): Int{
        return when (enum){
            LogicOperators.AND -> R.drawable.and
            LogicOperators.NOT -> R.drawable.not
            LogicOperators.OR -> R.drawable.or
            LogicOperators.NAND -> R.drawable.nand
            LogicOperators.NOR -> R.drawable.nor
            LogicOperators.XOR -> R.drawable.xor
        }
    }


    fun onToggleClick(view: View) {
        val varA = varToBool(varA.text.toString())
        val varB = varToBool(varB.text.toString())

        result.text = when (currentOperator){
            LogicOperators.NOT -> boolToToggleString(varA.not())
            LogicOperators.AND -> boolToToggleString(varA and varB)
            LogicOperators.OR -> boolToToggleString(varA or varB)
            LogicOperators.NAND -> boolToToggleString(!(varA and varB))
            LogicOperators.NOR -> boolToToggleString(!(varA or varB))
            LogicOperators.XOR -> boolToToggleString(varA xor varB)
        }
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
}


private enum class LogicOperators {
    NOT, AND, OR, NAND, NOR, XOR
}
