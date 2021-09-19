package com.martin.meanbean.data.remote.dto

import com.google.gson.annotations.Expose

data class DrinkDto(
	@Expose
	val name: String = "",
	@Expose
	val desc: String = "",
	@Expose
	val image: String = "",
	@Expose
	val ratio: String = "",
	@Expose
	val cup: String = "",
)