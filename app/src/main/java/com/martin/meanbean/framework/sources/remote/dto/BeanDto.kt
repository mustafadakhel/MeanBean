package com.martin.meanbean.framework.sources.remote.dto

import com.martin.meanbean.domain.models.Bean

data class BeanDto(
	val id: Int = -1,
	val name: String = "",
	val image: String = "",
	val desc: String = "",
)

fun BeanDto.toBean() = Bean(
	id = id,
	name = name,
	image = image,
	description = desc,
)