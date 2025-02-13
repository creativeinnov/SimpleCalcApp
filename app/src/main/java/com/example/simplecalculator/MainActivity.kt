package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text1 = findViewById<EditText>(R.id.text1)
        val text2 = findViewById<EditText>(R.id.text2)
        val result = findViewById<TextView>(R.id.result)
        val add = findViewById<Button>(R.id.add)
        val sub = findViewById<Button>(R.id.sub)
        val mul = findViewById<Button>(R.id.mul)
        val div = findViewById<Button>(R.id.div)
        val equal = findViewById<Button>(R.id.equal)
        val ac = findViewById<Button>(R.id.ac)

        var operation: ((Double, Double) -> Double)? = null

        add.setOnClickListener {
            operation = { a, b -> a + b }
        }

        sub.setOnClickListener {
            operation = { a, b -> a - b }
        }

        mul.setOnClickListener {
            operation = { a, b -> a * b }
        }

        div.setOnClickListener {
            operation = { a, b -> if (b != 0.0) a / b else Double.NaN }
        }

        equal.setOnClickListener {
            val num1 = text1.text.toString().toDoubleOrNull()
            val num2 = text2.text.toString().toDoubleOrNull()
            if (num1 != null && num2 != null && operation != null) {
                val output = operation?.invoke(num1, num2)
                if (output != null && !output.isNaN()) {
                    result.text = "Result: $output"
                } else {
                    result.text = "Cannot divide by zero"
                }
            } else {
                result.text = "Invalid Input"
            }
        }

        ac.setOnClickListener {
            text1.text.clear()
            text2.text.clear()
            result.text = ""
        }
    }
}
