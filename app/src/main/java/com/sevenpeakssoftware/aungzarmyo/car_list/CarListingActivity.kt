package com.sevenpeakssoftware.aungzarmyo.car_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenpeakssoftware.aungzarmyo.databinding.ActivityCarListingBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarListingActivity : AppCompatActivity() {

    private var keep = true
    private val delay = 2000L
    lateinit var binding: ActivityCarListingBinding

    @Inject
    lateinit var adapter: CarListAdapter

    private val viewModel: CarListViewModel by viewModels()

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
        initObservable()
    }

    private fun initView() {
        adapter = CarListAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        viewModel.getCarList()
    }

    private fun initObservable() {
        viewModel.liveData.observe(this) { result ->
            if (!result?.content.isNullOrEmpty()) {
                adapter.addData(result?.content!!)
            }
        }
    }
}