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
    private var mutableLiveDataLocalDatabase = MutableLiveData<List<CarModel>>()
    val liveDataLocalDatabase: LiveData<List<CarModel>> =
        mutableLiveDataLocalDatabase

    fun getCarList() {
        viewModelScope.launch {
            try {
                val result = carListRepository.getCarList()
                mutableLiveData.postValue(NetworkResult.Success(result.content))
            } catch (e: Exception) {
                mutableLiveData.postValue(NetworkResult.Error(e))
            }
        }
    }

    fun getCarListFromLocalDatabase() {
        viewModelScope.launch {
            if (carListRepository.getCarListFromLocalDatabase().isNotEmpty()) {
                mutableLiveDataLocalDatabase.postValue(
                    carListRepository.getCarListFromLocalDatabase().reversed()
                )
            } else {
                mutableLiveDataLocalDatabase.postValue(emptyList())
            }
        }
    }

    fun saveInLocal(carModel: CarModel?) {
        carModel?.let {
            viewModelScope.launch {
                carListRepository.saveInLocal(carModel)
            }
        }
    }
}