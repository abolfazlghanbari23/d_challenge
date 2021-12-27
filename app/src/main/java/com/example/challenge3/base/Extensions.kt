package com.example.challenge3.base

import android.location.Location
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object Extensions {
    fun RecyclerView.isBottomOfListReached(): Boolean {
        if (adapter == null || layoutManager == null)
            return false
        val linearLayoutManager = layoutManager as LinearLayoutManager?
        return linearLayoutManager?.findLastCompletelyVisibleItemPosition() == (adapter?.itemCount
            ?: 0) - 1

    }

    fun Location.ll() = "$latitude,$longitude"

}