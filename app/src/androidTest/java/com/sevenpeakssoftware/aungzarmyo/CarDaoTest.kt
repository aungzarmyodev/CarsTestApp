package com.sevenpeakssoftware.aungzarmyo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModel
import com.sevenpeakssoftware.aungzarmyo.local_database.CarRoomDataBase
import com.sevenpeakssoftware.aungzarmyo.local_database.CarsDao
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@SmallTest
class CarDaoTest {

    private lateinit var carRoomDataBase: CarRoomDataBase
    private lateinit var carsDao: CarsDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        carRoomDataBase = Room.inMemoryDatabaseBuilder(context, CarRoomDataBase::class.java).build()
        carsDao = carRoomDataBase.carDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        carRoomDataBase.close()
    }

    @Test
    @Throws(Exception::class)
    fun addCarModelTest() {
        runBlocking {
            val carModel = CarModel(
                119302,
                "Q7 - Greatness starts, when you don't stop.",
                "25.05.2018 14:13",
                "The Audi Q7 is the result of an ambitious idea: never cease to improve.",
                "audi_q7.jpg"
            )

            carsDao.addCar(carModel)
            val test = carsDao.getCarModelById(carModel.id)
            Assert.assertEquals(test, carModel)
        }
    }
}