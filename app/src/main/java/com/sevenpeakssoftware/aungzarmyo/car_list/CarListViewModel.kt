package com.sevenpeakssoftware.aungzarmyo.car_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(private val carListRepository: CarListRepository) :
    ViewModel() {

    private var mutableLiveData = MutableLiveData<CarModelResponse>()
    val liveData : LiveData<CarModelResponse> = mutableLiveData

    fun getCarList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = carListRepository.getCarList()
                mutableLiveData.postValue(result)

            }catch (e : Exception) {

            }
        }
    }
}