package com.sevenpeakssoftware.aungzarmyo.local_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenpeakssoftware.aungzarmyo.car_list.CarModel

@Dao
interface CarsDao {

    @Query("SELECT * FROM car")
    suspend fun getCarList(): List<CarModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCar(carModel: CarModel)
}