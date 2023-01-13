package com.sevenpeakssoftware.aungzarmyo.network

import com.sevenpeakssoftware.aungzarmyo.car_list.CarModel
import com.sevenpeakssoftware.aungzarmyo.car_list.CarModelResponse
import com.sevenpeakssoftware.aungzarmyo.local_database.CarsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarListRepository @Inject constructor(
    private val carApi: CarApi,
    private val carsDao: CarsDao
) {

    suspend fun getCarList(): CarModelResponse {
        return withContext(Dispatchers.IO) {
            carApi.getCarList()
        }
    }

    suspend fun saveInLocal(carModel: CarModel) {
        carsDao.addCar(carModel)
    }

    suspend fun getCarListFromLocalDatabase(): List<CarModel> {
        return withContext(Dispatchers.IO) {
            carsDao.getCarList()
        }
    }
}