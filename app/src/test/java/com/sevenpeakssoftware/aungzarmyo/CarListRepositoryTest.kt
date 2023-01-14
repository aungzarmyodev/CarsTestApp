package com.sevenpeakssoftware.aungzarmyo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModel
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModelResponse
import com.sevenpeakssoftware.aungzarmyo.local_database.CarsDao
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarListRepositoryTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: CarListRepository

    @Mock
    lateinit var carModelResponse: CarModelResponse

    @Mock
    lateinit var mockCarModel : CarModel

    @Test
    fun getCarListReturnSuccessTest() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCarList()).thenAnswer {
                Result.success(carModelResponse)
            }
            Assert.assertNotNull(repository.getCarList())
            Assert.assertEquals(Result.success(carModelResponse), repository.getCarList())
        }
    }

    @Test
    fun getCarListReturnErrorTest() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCarList()).thenAnswer {
                Result.success(null)
            }
            Assert.assertNotNull(repository.getCarList())
            Assert.assertNotEquals(Result.success(carModelResponse), repository.getCarList())
        }
    }

    @Test
    fun getCarListFromLocalReturnSuccessTest() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCarListFromLocalDatabase()).thenAnswer {
                Result.success(carModelResponse)
            }
            Assert.assertNotNull(repository.getCarListFromLocalDatabase())
            Assert.assertEquals(Result.success(carModelResponse), repository.getCarListFromLocalDatabase())
        }
    }

    @Test
    fun getCarListFromLocalReturnErrorTest() {

        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getCarListFromLocalDatabase()).thenAnswer {
                Result.success(null)
            }
            Assert.assertNotNull(repository.getCarListFromLocalDatabase())
            Assert.assertNotEquals(Result.success(carModelResponse), repository.getCarListFromLocalDatabase())
        }
    }

    @Test
    fun insertCarModelTest() {

        mockCarModel.id = 119302
        mockCarModel.dateTime = "25.05.2018 14:13"
        mockCarModel.title = "Q7 - Greatness starts, when you don't stop."
        mockCarModel.image = "audi_q7.jpg"
        // need to edit
    }
}