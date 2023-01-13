package com.sevenpeakssoftware.aungzarmyo.car_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import com.sevenpeakssoftware.aungzarmyo.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val carListRepository: CarListRepository) :
    ViewModel() {

    private var mutableLiveData = MutableLiveData<NetworkResult<CarModelResponse>>()
    val liveData: LiveData<NetworkResult<CarModelResponse>> = mutableLiveData

    fun getCarList() {
        viewModelScope.launch {
            try {
                val result = carListRepository.getCarList()
                mutableLiveData.postValue(NetworkResult.Success(result))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkResult.Error(e))
            }
        }
    }
}