package com.sevenpeakssoftware.aungzarmyo

import android.app.Application
import com.sevenpeakssoftware.aungzarmyo.local_database.CarRoomDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarListApp : Application() {
    val dataBase: CarRoomDataBase by lazy { CarRoomDataBase.getDatabase(this) }
}