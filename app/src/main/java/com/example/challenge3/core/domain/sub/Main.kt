package com.example.challenge3.core.domain.sub

import com.google.gson.annotations.SerializedName

data class Main(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)
