package com.martin.meanbean.domain.mapper

import com.martin.meanbean.data.remote.dto.DrinkDto
import com.martin.meanbean.domain.entities.DrinkEntity
import org.mapstruct.Mapper

@Mapper(config = CustomMapperConfig::class)
interface DrinkMapper : BaseMapper<DrinkDto, DrinkEntity>