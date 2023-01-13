package com.sevenpeakssoftware.aungzarmyo.car_main_home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class CarModelResponse(
    var status: String,
    var content: List<CarModel>?,
    var serverTime: Long
)

@Entity(tableName = "car")
data class CarModel(
    @PrimaryKey
    @SerializedName("id") var id: Int?,
    @ColumnInfo(name = "title")
    @SerializedName("title") var title: String?,
    @ColumnInfo(name = "dateTime")
    @SerializedName("dateTime") var dateTime: String?,
    @ColumnInfo(name = "ingress")
    @SerializedName("ingress") var ingress: String?,
    @ColumnInfo(name = "image")
    @SerializedName("image") var image: String?
)
