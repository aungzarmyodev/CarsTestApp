package com.sevenpeakssoftware.aungzarmyo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModel
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModelResponse
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarListRepositoryTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CarListRepository

    @Mock
    lateinit var carModelResponse: CarModelResponse

    @Mock
    lateinit var mockCarModel: CarModel

    @Mock
    lateinit var mockException: HttpException

    @Test
    fun getCarListReturnSuccessTest() = runTest {
        Mockito.`when`(repository.getCarList()).thenAnswer {
            Result.success(carModelResponse)
        }
        Assert.assertNotNull(repository.getCarList())
        Assert.assertEquals(Result.success(carModelResponse), repository.getCarList())
    }

    @Test
    fun getCarListReturnFailTest() = runTest{

        Mockito.`when`(repository.getCarList()).thenAnswer {
            Result.failure<HttpException>(mockException)
        }
        Assert.assertNotNull(repository.getCarList())
        Assert.assertEquals(
            Result.failure<HttpException>(mockException),
            repository.getCarList()
        )
    }

    @Test
    fun getCarListFromLocalReturnSuccessTest() = runTest {

        Mockito.`when`(repository.getCarListFromLocalDatabase()).thenAnswer {
            Result.success(carModelResponse)
        }
        Assert.assertNotNull(repository.getCarListFromLocalDatabase())
        Assert.assertEquals(
            Result.success(carModelResponse),
            repository.getCarListFromLocalDatabase()
        )
    }

    @Test
    fun getCarListFromLocalReturnFailTest() = runTest {

        Mockito.`when`(repository.getCarListFromLocalDatabase()).thenAnswer {
            Result.failure<HttpException>(mockException)
        }
        Assert.assertNotNull(repository.getCarListFromLocalDatabase())
        Assert.assertEquals(
            Result.failure<HttpException>(mockException),
            repository.getCarListFromLocalDatabase()
        )
    }
}