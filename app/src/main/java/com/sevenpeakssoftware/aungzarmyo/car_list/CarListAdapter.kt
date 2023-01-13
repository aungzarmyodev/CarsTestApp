package com.sevenpeakssoftware.aungzarmyo.car_list

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.aungzarmyo.R
import com.sevenpeakssoftware.aungzarmyo.databinding.ItemListCarsLayoutBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CarListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var carList = mutableListOf<CarModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CarsListViewHolder(
            ItemListCarsLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CarsListViewHolder) {
            holder.onBind(carList[position])
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    fun addData(list: List<CarModel>) {
        carList.clear()
        carList.addAll(list)
        notifyDataSetChanged()
    }

    inner class CarsListViewHolder(private val binding: ItemListCarsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(carModel: CarModel?) {
            binding.tvTitle.text = carModel?.title
            binding.tvIngress.text = carModel?.ingress

            if (!carModel?.dateTime.isNullOrEmpty()) {
                try {
                    val simpleDateFormat = SimpleDateFormat("dd.mm.yyyy HH:mm", Locale.getDefault())
                    val carModelDate = simpleDateFormat.parse(carModel?.dateTime!!)
                    if (carModelDate != null) {
                        binding.tvDate.text = dateFormat(carModelDate).format(carModelDate)
                    }
                } catch (e: ParseException) {
                    Log.e("TAG", e.message.toString())
                }
            }

            Glide.with(binding.root.context)
                .load("https://cars-sevenpeaks.web.app/${carModel?.image}")
                .placeholder(R.drawable.ic_default_car_icon)
                .error(R.drawable.ic_default_car_icon)
                .into(binding.ivCarLogo)
        }

        private fun dateFormat(date: Date): SimpleDateFormat {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return if (calendar.get(Calendar.YEAR) == currentYear) {
                if (DateFormat.is24HourFormat(binding.root.context)) {
                    SimpleDateFormat("dd MMMM, HH:mm", Locale.getDefault())
                } else {
                    SimpleDateFormat("dd MMMM, hh:mm aa", Locale.getDefault())
                }
            } else {
                if (DateFormat.is24HourFormat(binding.root.context)) {
                    SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault())
                } else {
                    SimpleDateFormat("dd MMMM yyyy, hh:mm aa", Locale.getDefault())
                }
            }
        }
    }
}