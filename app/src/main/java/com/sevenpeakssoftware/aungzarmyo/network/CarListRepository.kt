package com.sevenpeakssoftware.aungzarmyo.network

import javax.inject.Inject

class CarListRepository @Inject constructor(private val carApi: CarApi) {

    suspend fun getCarList() = carApi.getCarList()
}