package com.sevenpeakssoftware.aungzarmyo.car_main_home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.sevenpeakssoftware.aungzarmyo.utils.Const
import com.sevenpeakssoftware.aungzarmyo.utils.Utils
import com.sevenpeakssoftware.aungzarmyo.databinding.ItemListCarsLayoutBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CarListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var carList = mutableListOf<CarModel>()
    val itemClickLiveData = MutableLiveData<CarModel>()
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
            holder.itemView.setOnClickListener {
                itemClickLiveData.postValue(carList[position])
            }
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
                        binding.tvDate.text = Utils.dateFormat(binding.root.context, carModelDate)
                            .format(carModelDate)
                    }
                } catch (e: ParseException) {
                    Log.e("TAG", e.message.toString())
                }
            }

            Utils.setImage(
                binding.root.context,
                "${Const.BASE_URL}${carModel?.image}",
                binding.ivCarLogo
            )
        }
    }
}