package com.sevenpeakssoftware.aungzarmyo.network

import com.sevenpeakssoftware.aungzarmyo.car_list.CarModelResponse
import retrofit2.http.GET

interface CarApi {

    @GET("/carlist")
    suspend fun getCarList() : CarModelResponse
}