package com.martin.meanbean.domain.mapper

import com.martin.meanbean.data.remote.dto.DrinkDto
import com.martin.meanbean.domain.entities.DrinkEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(config = CustomMapperConfig::class)
interface DrinkMapper : BaseMapper<DrinkDto, DrinkEntity> {
	@Mappings(value = [Mapping(target = "description", source = "desc")])
	override fun dtoToEntity(dto: DrinkDto): DrinkEntity
}