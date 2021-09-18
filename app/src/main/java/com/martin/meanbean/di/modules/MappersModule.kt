package com.martin.meanbean.di.modules

import com.martin.meanbean.domain.mapper.BeanMapper
import com.martin.meanbean.domain.mapper.DrinkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

	@Provides
	@Singleton
	fun provideBeanMapper(): BeanMapper {
		return Mappers.getMapper(BeanMapper::class.java)
	}

	@Provides
	@Singleton
	fun provideDrinkMapper(): DrinkMapper {
		return Mappers.getMapper(DrinkMapper::class.java)
	}
}