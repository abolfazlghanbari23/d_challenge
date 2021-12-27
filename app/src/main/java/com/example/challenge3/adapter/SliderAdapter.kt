package com.example.challenge3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3.R
import com.example.challenge3.core.domain.PlaceImage
import com.example.challenge3.databinding.ItemSlideContainerBinding

class SliderAdapter(
    private val images: List<PlaceImage>,
    private val callBack: CallBack
) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding: ItemSlideContainerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_slide_container,
            parent,
            false
        )
        return SliderViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) =
        holder.bind(images[position])

    override fun getItemCount() = images.size

    class SliderViewHolder(
        private val binding: ItemSlideContainerBinding,
        private val callBack: CallBack
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(placeImage: PlaceImage) {
            binding.placeImage = placeImage
            binding.callBack = callBack
            binding.executePendingBindings()
        }

    }

    interface CallBack {
        fun onClick(placeImage: PlaceImage)
    }

}