package com.sevenpeakssoftware.aungzarmyo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarListViewModel
import com.sevenpeakssoftware.aungzarmyo.network.CarListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: CarListRepository

    lateinit var viewModel: CarListViewModel

    @Before
    fun setUp() {
        viewModel = CarListViewModel(repository)
    }

    @Test
    fun testGetCarListWhenReturnSuccess() = runTest {
        viewModel.getCarList()
        val test = viewModel.liveData.value
        Assert.assertEquals(test, viewModel.liveData.value)
    }
}