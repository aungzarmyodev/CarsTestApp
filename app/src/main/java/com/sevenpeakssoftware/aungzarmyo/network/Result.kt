package com.sevenpeakssoftware.aungzarmyo.network

sealed class Result<T>(
    val data: T? = null,
    val error: Exception? = null,
    val status: Status
) {

    class Success<T>(data: T) : Result<T>(data, null, Status.SUCCESS)

    class Error<T>(exception: Exception) : Result<T>(null, exception, Status.ERROR)

    class Loading<T> : Result<T>(null, null, Status.LOADING)

}

