package com.example.challenge3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge3.R
import com.example.challenge3.core.domain.sub.Category
import com.example.challenge3.databinding.ItemCategoryBinding

class CategoryAdapter(private val callBack: CallBack) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Category>() {

        override fun areItemsTheSame(oldItem: Category, newItem: Category) = oldItem.id ==  newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category) = oldItem ==  newItem

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_category,
            parent,
            false
        )
        return CategoryViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Category>) {
        differ.submitList(list)
    }

    class CategoryViewHolder
    constructor(
        private val binding: ItemCategoryBinding,
        private val callBack: CallBack?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.callBack = callBack
            binding.category = category
            binding.executePendingBindings()
        }
    }

    interface CallBack {
        fun onClick(category: Category)
    }
}

