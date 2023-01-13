package com.sevenpeakssoftware.aungzarmyo.local_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_test")
data class Cars(
    @PrimaryKey var id: Int?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "dateTime") var dateTime: String?,
    @ColumnInfo(name = "ingress") var ingress: String?,
    @ColumnInfo(name = "image") var image: String?
)

