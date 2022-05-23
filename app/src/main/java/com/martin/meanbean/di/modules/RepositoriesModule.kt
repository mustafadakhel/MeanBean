package com.martin.meanbean.di.modules

import com.martin.meanbean.domain.repository.BeansRepository
import com.martin.meanbean.domain.repository.DrinksRepository
import com.martin.meanbean.domain.repository.HomeRepository
import com.martin.meanbean.data.repository.DefaultBeansRepository
import com.martin.meanbean.data.repository.DefaultDrinksRepository
import com.martin.meanbean.data.repository.DefaultHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

	@Binds
	@Singleton
	fun bindDefaultBeansRepositoryToBeansRepository(defaultBeansRepository: DefaultBeansRepository): BeansRepository

	@Binds
	@Singleton
	fun bindDefaultDrinksRepositoryToDrinksRepository(defaultDrinksRepository: DefaultDrinksRepository): DrinksRepository

	@Binds
	@Singleton
	fun bindDefaultHomeRepositoryToHomeRepository(defaultHomeRepository: DefaultHomeRepository): HomeRepository

}