package com.sevenpeakssoftware.aungzarmyo.car_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.aungzarmyo.R
import com.sevenpeakssoftware.aungzarmyo.databinding.ItemListCarsLayoutBinding

class CarListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var carList = mutableListOf<String>()

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
            holder.onBind()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class CarsListViewHolder(private val binding: ItemListCarsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            Glide.with(binding.root.context)
                .load(R.drawable.car_icon)
                .placeholder(R.drawable.ic_splash_background)
                .error(R.drawable.ic_splash_background)
                .into(binding.imageView)

        }
    }
}