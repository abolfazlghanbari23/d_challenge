package com.example.challenge3.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object Extensions {
    fun RecyclerView.isBottomOfListReached(): Boolean {
        if (adapter == null || layoutManager == null)
            return false
        val linearLayoutManager = layoutManager as LinearLayoutManager?
        return linearLayoutManager?.findLastCompletelyVisibleItemPosition() == (adapter?.itemCount
            ?: 0) - 1

    }
}