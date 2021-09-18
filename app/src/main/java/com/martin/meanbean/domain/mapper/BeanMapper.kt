package com.martin.meanbean.domain.mapper

import com.martin.meanbean.data.remote.dto.BeanDto
import com.martin.meanbean.domain.entities.BeanEntity
import org.mapstruct.Mapper

@Mapper(config = CustomMapperConfig::class)
interface BeanMapper : BaseMapper<BeanDto, BeanEntity>