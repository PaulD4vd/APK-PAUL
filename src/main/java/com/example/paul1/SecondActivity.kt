package com.example.paul1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack

class SecondActivity : AppCompatActivity() {
    private lateinit var workingTv: TextView
    private lateinit var resultsTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        workingTv = findViewById(R.id.working)
        resultsTV = findViewById(R.id.results)

        // Initialize button and set up its OnClickListener
        val buttonGoBack: Button = findViewById(R.id.btnBack)
        buttonGoBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Ends the current activity
        }
    }

    fun numberAction(view: View) {
        if (view is Button) {
            workingTv.append(view.text)
        }
    }

    fun operationAction(view: View) {
        if (view is Button) {
            workingTv.append(
                when (view.text) {
                    "÷" -> "/"
                    "×" -> "*"
                    else -> view.text
                }
            )
        }
    }

    fun allClearAction(view: View) {
        workingTv.text = ""
        resultsTV.text = ""
    }

    fun backSpaceAction(view: View) {
        val length = workingTv.text.length
        if (length > 0) {
            workingTv.text = workingTv.text.subSequence(0, length - 1)
        }
    }

    fun equalsAction(view: View) {
        try {
            val expression = workingTv.text.toString()
            val result = evaluateExpression(expression)
            resultsTV.text = result.toString()
        } catch (e: Exception) {
            resultsTV.text = "Error"
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val tokens = tokenize(expression)
        val output = mutableListOf<String>()
        val operators = Stack<String>()

        for (token in tokens) {
            when {
                token.matches(Regex("\\d+")) -> output.add(token)
                token == "(" -> operators.push(token)
                token == ")" -> {
                    while (operators.isNotEmpty() && operators.peek() != "(") {
                        output.add(operators.pop())
                    }
                    operators.pop()
                }
                token in setOf("+", "-", "*", "/") -> {
                    while (operators.isNotEmpty() && precedence(operators.peek()) >= precedence(token)) {
                        output.add(operators.pop())
                    }
                    operators.push(token)
                }
            }
        }

        while (operators.isNotEmpty()) {
            output.add(operators.pop())
        }

        return evaluateRpn(output)
    }

    private fun tokenize(expression: String): List<String> {
        return expression
            .replace("×", "*")
            .replace("÷", "/")
            .replace(Regex("([+\\-*/()])"), " $1 ")
            .trim()
            .split("\\s+".toRegex())
    }

    private fun precedence(operator: String): Int {
        return when (operator) {
            "+", "-" -> 1
            "*", "/" -> 2
            else -> 0
        }
    }

    private fun evaluateRpn(tokens: List<String>): Double {
        val stack = Stack<Double>()

        for (token in tokens) {
            when {
                token.matches(Regex("\\d+")) -> stack.push(token.toDouble())
                token in setOf("+", "-", "*", "/") -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    val result = when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> a / b
                        else -> 0.0
                    }
                    stack.push(result)
                }
            }
        }

        return stack.pop()
    }
}
