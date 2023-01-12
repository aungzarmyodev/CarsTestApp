package com.sevenpeakssoftware.aungzarmyo.car_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sevenpeakssoftware.aungzarmyo.R
import com.sevenpeakssoftware.aungzarmyo.databinding.ActivityCarListingBinding
import com.sevenpeakssoftware.aungzarmyo.network.Status
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
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
        initObservers()
    }

    private fun initView() {
        adapter = CarListAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        binding.progressBar.show()
        viewModel.getCarList()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCarList()
        }
    }

    private fun initObservers() {
        viewModel.liveData.observe(this) { result ->
            binding.swipeRefreshLayout.isRefreshing = false
            binding.progressBar.hide()

            val carModelResponse = result?.data
            when (result.status) {
                Status.SUCCESS -> {
                    if (carModelResponse != null && carModelResponse.content.isNotEmpty()) {
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.addData(carModelResponse.content)
                    } else {
                        binding.recyclerView.visibility = View.GONE
                    }
                }
                Status.ERROR -> {
                    when (result?.error) {
                        is IOException -> {
                            val snackBar = Snackbar.make(
                                binding.rootView,
                                getString(R.string.label_no_network_error),
                                Snackbar.LENGTH_INDEFINITE
                            )
                            snackBar.setAction(
                                getString(R.string.label_ok)
                            ) { snackBar.dismiss() }
                            snackBar.show()
                        }
                        else -> {
                            val snackBar = Snackbar.make(
                                binding.rootView,
                                if (!result?.error?.message.isNullOrEmpty()) result?.error?.message.toString() else getString(
                                    R.string.label_unknown_error
                                ),
                                Snackbar.LENGTH_LONG
                            )
                            snackBar.show()
                        }
                    }
                }
                Status.LOADING -> {

                }
            }
        }
    }
}