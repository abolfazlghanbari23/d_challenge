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

    fun Location?.toText(): String {
        return if (this != null) {
            "($latitude, $longitude)"
        } else {
            "Unknown location"
        }
    }

    /**
     * Based on meters
     */
    fun Location.distance(loc2: Location): Double {
        val theta = this.longitude - loc2.longitude
        var dist =
            sin(deg2rad(this.latitude)) * sin(deg2rad(loc2.latitude)) + cos(deg2rad(this.latitude)) * cos(
                deg2rad(loc2.latitude)
            ) * cos(deg2rad(theta))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515 * 1.609344 * 1000
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}