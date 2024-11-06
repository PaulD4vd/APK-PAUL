package com.example.paul1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val buttonGoToNext: Button = findViewById(R.id.button1)
        buttonGoToNext.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val buttonGoToNext2: Button = findViewById(R.id.button2)
        buttonGoToNext2.setOnClickListener {  // Use buttonGoToNext2 here
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        val buttonGoToNext3: Button = findViewById(R.id.button3)
        buttonGoToNext3.setOnClickListener {  // Use buttonGoToNext3 here
            val intent = Intent(this, FourthActivity::class.java)
            startActivity(intent)
        }
    }
}
