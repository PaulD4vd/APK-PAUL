package com.example.paul1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class ActivitySplash : AppCompatActivity() {  // Tambahkan tanda kurung di sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        supportActionBar?.hide()

        // Timer Splash screen selama 6 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)  // Ganti intent(this, ...) dengan Intent()
            startActivity(intent)
            finish()
        }, 6000)  // 6000ms = 6 detik
    }
}
