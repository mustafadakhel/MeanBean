package com.martin.meanbean.domain.mapper

import org.mapstruct.MapperConfig
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValueMappingStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

interface BaseMapper<Dto, Domain> {
	fun mapDtoToDomain(dto: Dto): Domain
}


@MapperConfig(
	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
	nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
	nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
)
class CustomMapperConfig