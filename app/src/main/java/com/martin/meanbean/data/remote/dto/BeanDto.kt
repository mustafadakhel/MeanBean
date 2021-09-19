package com.martin.meanbean.data.remote.dto

import com.google.gson.annotations.Expose

data class BeanDto(
	@Expose
	val name: String = "",
	@Expose
	val image: String = "",
	@Expose
	val desc: String = "",
)