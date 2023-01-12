package com.sevenpeakssoftware.aungzarmyo.car_list


data class CarModelResponse(
    var status: String,
    var content: List<CarModel>,
    var serverTime: Long
)

data class CarModel(
    var id: Int?,
    var title: String?,
    var dateTime: String?,
    var ingress: String?,
    var image: String?
)
