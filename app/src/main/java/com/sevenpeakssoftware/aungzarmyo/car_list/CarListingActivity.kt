package com.sevenpeakssoftware.aungzarmyo.car_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenpeakssoftware.aungzarmyo.databinding.ActivityCarListingBinding

class CarListingActivity : AppCompatActivity() {

    private var keep = true
    private val delay = 2000L
    lateinit var binding: ActivityCarListingBinding
    lateinit var adapter: CarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityCarListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashScreen.setKeepOnScreenCondition {
            keep
        }
        Handler(Looper.getMainLooper()).postDelayed({ keep = false }, delay)
        setSupportActionBar(binding.toolBar)
        initView()
    }

    private fun initView() {
        adapter = CarListAdapter()
        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}