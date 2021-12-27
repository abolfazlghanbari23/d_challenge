package com.example.challenge3.sharedpref

import android.content.Context
import android.location.Location
import com.example.challenge3.base.Extensions.ll
import com.example.challenge3.ui.fragment.places.PlacesFragment

class UserSharedPref(context: Context) {

    companion object {
        const val SHARED_PREF_NAME = "user_pref"
        const val KEY_LOCATION = "location"
    }

    private val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun getLastLocation() : Location? {
        val locStr = sharedPref.getString(KEY_LOCATION, null)
        return if (locStr == null) {
            null
        } else {
            val location = Location("").apply {
                val locStrs = locStr.split(",")
                longitude = locStrs[1].toDouble()
                latitude = locStrs[0].toDouble()
            }
            location
        }
    }

    fun saveLastLocation(location: Location) {
        sharedPref.edit().putString(KEY_LOCATION, location.ll()).apply()
    }

}