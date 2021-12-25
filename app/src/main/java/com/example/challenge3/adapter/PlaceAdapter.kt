package com.example.challenge3.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.challenge3.R
import com.example.challenge3.core.domain.Place
import com.example.challenge3.databinding.ItemPlaceBinding

class PlaceAdapter(private val callBack: CallBack) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Place>() {

        override fun areItemsTheSame(oldItem: Place, newItem: Place) =
            oldItem.fsqId == newItem.fsqId

        override fun areContentsTheSame(oldItem: Place, newItem: Place) = oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = DataBindingUtil.inflate<ItemPlaceBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_place, parent, false
        )

        return PlaceViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PlaceViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Place>) {
        differ.submitList(list)
    }

    class PlaceViewHolder(
        private val binding: ItemPlaceBinding,
        private val callBack: CallBack
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.callback = callBack
            binding.place = place
            binding.executePendingBindings()
        }
    }

    interface CallBack {
        fun onItemClick(place: Place)
    }
}

