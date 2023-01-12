package com.sevenpeakssoftware.aungzarmyo.car_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import com.sevenpeakssoftware.aungzarmyo.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val carListRepository: CarListRepository) :
    ViewModel() {

    private var mutableLiveData = MutableLiveData<Result<CarModelResponse>>()
    val liveData: LiveData<Result<CarModelResponse>> = mutableLiveData

    fun getCarList() {
        viewModelScope.launch {
            try {
                val result = carListRepository.getCarList()
                mutableLiveData.postValue(Result.Success(result))
            } catch (e: Exception) {
                mutableLiveData.postValue(Result.Error(e))
            }
        }
    }
}