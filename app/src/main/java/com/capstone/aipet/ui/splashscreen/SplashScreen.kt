package com.capstone.aipet.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.capstone.aipet.DashboardActivity
import com.capstone.aipet.R
import com.capstone.aipet.pref.UserPreference

class SplashScreen : AppCompatActivity() {
    companion object {
        private const val DURATION: Long = 1500
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val SPLASH_TIME_OUT = 2000

        val sharedPref = UserPreference.init(this, "onSignIn")
        val token = sharedPref.getString("token", "")

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (token != "") {
                Intent(this, DashboardActivity::class.java)
            } else {
                Intent(this, WelcomeActivity::class.java)
            }

            startActivity(intent)
            finish()
        }, DURATION)
    }
}