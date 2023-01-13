package com.sevenpeakssoftware.aungzarmyo.local_database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalAppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        CarRoomDataBase::class.java,
        "car_db_name"
    ).fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCarDao(carDb : CarRoomDataBase) = carDb.carDao()
}