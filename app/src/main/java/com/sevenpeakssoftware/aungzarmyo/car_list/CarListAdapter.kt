package com.sevenpeakssoftware.aungzarmyo.car_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.aungzarmyo.R
import com.sevenpeakssoftware.aungzarmyo.databinding.ItemListCarsLayoutBinding
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
            binding.tvDate.text = carModel?.dateTime

            // Need to calculate date format
           // val simpleDateFormat = SimpleDateFormat("dd.MMMM.yyyy ", Locale.US)

            Glide.with(binding.root.context)
                .load(carModel?.image)
                .placeholder(R.drawable.ic_default_car_icon)
                .error(R.drawable.ic_default_car_icon)
                .into(binding.ivCarLogo)
        }
    }
}