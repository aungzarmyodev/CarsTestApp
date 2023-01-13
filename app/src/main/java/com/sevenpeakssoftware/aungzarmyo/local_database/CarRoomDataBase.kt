package com.sevenpeakssoftware.aungzarmyo.local_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sevenpeakssoftware.aungzarmyo.car_main_home.CarModel

@Database(entities = [CarModel::class], version = 1, exportSchema = false)
abstract class CarRoomDataBase : RoomDatabase() {
    abstract fun carDao(): CarsDao

    companion object {
        @Volatile
        private var INSTANCE: CarRoomDataBase? = null
        fun getDatabase(context: Context): CarRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarRoomDataBase::class.java,
                    "car_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}