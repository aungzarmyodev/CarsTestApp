package com.sevenpeakssoftware.aungzarmyo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sevenpeakssoftware.aungzarmyo.databinding.ActivityCarListingBinding

class CarListingActivity : AppCompatActivity() {

    private var keep = true
    private val delay = 2000L
    lateinit var binding: ActivityCarListingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityCarListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition {
            keep
        }
        Handler(Looper.getMainLooper()).postDelayed({ keep = false }, delay)
    }
}