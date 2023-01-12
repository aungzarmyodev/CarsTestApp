package com.sevenpeakssoftware.aungzarmyo.network

import com.sevenpeakssoftware.aungzarmyo.car_list.CarModelResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarListRepository @Inject constructor(private val carApi: CarApi) {

    suspend fun getCarList(): CarModelResponse {
        return withContext(Dispatchers.IO) {
            carApi.getCarList()
        }
    }
}