package com.sevenpeakssoftware.aungzarmyo.network

import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModelResponse
import retrofit2.http.GET

interface CarApi {

    @GET("carlist")
    suspend fun getCarList() : CarModelResponse
}