package com.example.challenge3.sharedpref

import android.content.Context

class UserSharedPref(context: Context) {

    companion object {
        const val SHARED_PREF_NAME = "user_pref"
        const val KEY_LOCATION = "location"
        const val KEY_FOREGROUND_ENABLED = "tracking_foreground_location"
    }

    private val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun getLastLocation() = sharedPref.getString(KEY_LOCATION, null)

    fun saveLastLocation(location: String) =
        sharedPref.edit().putString(KEY_LOCATION, location).apply()

    fun getLocationTrackingPref() = sharedPref.getBoolean(KEY_FOREGROUND_ENABLED, false)

    fun saveLocationTrackingPref(requestingLocationUpdates: Boolean) =
        sharedPref.edit().putBoolean(KEY_FOREGROUND_ENABLED, requestingLocationUpdates).apply()
}