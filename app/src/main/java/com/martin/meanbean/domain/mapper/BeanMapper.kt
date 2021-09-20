package com.martin.meanbean.domain.mapper

import com.martin.meanbean.data.remote.dto.BeanDto
import com.martin.meanbean.domain.entities.BeanEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(config = CustomMapperConfig::class)
interface BeanMapper : BaseMapper<BeanDto, BeanEntity> {
	@Mappings(value = [Mapping(target = "description", source = "desc")])
	override fun dtoToEntity(dto: BeanDto): BeanEntity
}