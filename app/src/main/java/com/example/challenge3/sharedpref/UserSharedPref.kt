package com.example.challenge3.sharedpref

import android.content.Context
import android.location.Location

class UserSharedPref(context: Context) {

    companion object {
        const val SHARED_PREF_NAME = "user_pref"
        const val KEY_LOCATION = "location"
    }

    private val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun getLastLocation() = sharedPref.getString(KEY_LOCATION, null)

    fun saveLastLocation(location: String) =
        sharedPref.edit().putString(KEY_LOCATION, location).apply()
}