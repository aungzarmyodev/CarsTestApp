package com.sevenpeakssoftware.aungzarmyo.car_main_home

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

    private var mutableLiveData = MutableLiveData<NetworkResult<List<CarModel>>>()
    val liveData: LiveData<NetworkResult<List<CarModel>>> = mutableLiveData

    fun getCarList() {
        viewModelScope.launch {
            try {
                val result = carListRepository.getCarList()
                mutableLiveData.postValue(NetworkResult.Success(result.content))
            } catch (e: Exception) {
                if (carListRepository.getCarListFromLocalDatabase().isNotEmpty()) {
                    mutableLiveData.postValue(NetworkResult.Success(carListRepository.getCarListFromLocalDatabase()))
                } else {
                    mutableLiveData.postValue(NetworkResult.Error(e))
                }
            }
        }
    }

    fun saveInLocal(carModel: CarModel) {
        viewModelScope.launch {
            carListRepository.saveInLocal(carModel)

        }
    }
}