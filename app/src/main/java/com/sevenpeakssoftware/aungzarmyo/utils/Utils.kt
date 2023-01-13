package com.sevenpeakssoftware.aungzarmyo.utils

import android.content.Context
import android.text.format.DateFormat
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.aungzarmyo.R
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun dateFormat(context: Context, date: Date): SimpleDateFormat {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return if (calendar.get(Calendar.YEAR) == currentYear) {
            if (DateFormat.is24HourFormat(context)) {
                SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault())
            } else {
                SimpleDateFormat("dd MMMM, hh:mm aa", Locale.getDefault())
            }
        } else {
            if (DateFormat.is24HourFormat(context)) {
                SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
            } else {
                SimpleDateFormat("dd MMMM yyyy, hh:mm aa", Locale.getDefault())
            }
        }
    }

    fun setImage(context: Context, imageUri: String?, imageView: AppCompatImageView) {

        Glide.with(context)
            .load(imageUri)
            .placeholder(R.drawable.ic_default_car_icon)
            .error(R.drawable.ic_default_car_icon)
            .into(imageView)
    }
}