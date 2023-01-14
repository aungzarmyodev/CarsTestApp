package com.sevenpeakssoftware.aungzarmyo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarListViewModel
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModel
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import com.sevenpeakssoftware.aungzarmyo.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var repository: CarListRepository

    @Mock
    lateinit var observer: Observer<NetworkResult<List<CarModel>>>

    lateinit var viewModel: CarListViewModel

    @Before
    fun setUp() {
        viewModel = CarListViewModel(repository)
    }

    @Test
    fun testTwoNumber() {

        Assert.assertEquals(2 + 2, 4)
    }

    @Test
    fun testGetCarListWhenReturnData() {
        val emptyList = emptyList<CarModel>()
        testCoroutineRule.runBlockingTest {
            viewModel.liveData.observeForever(observer)
            whenever(repository.getCarList()).thenAnswer {
                Result.success(NetworkResult.Success(emptyList))
            }
            viewModel.getCarList()
            Assert.assertNotNull(viewModel.liveData.value)
            Assert.assertEquals(Result.success(NetworkResult.Success(emptyList)), viewModel.liveData.value)
        }
    }
}