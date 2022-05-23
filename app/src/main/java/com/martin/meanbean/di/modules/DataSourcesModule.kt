package com.martin.meanbean.di.modules

import com.martin.meanbean.data.sources.contract.BeansSource
import com.martin.meanbean.data.sources.contract.DrinksSource
import com.martin.meanbean.data.sources.contract.MutableBeansSource
import com.martin.meanbean.data.sources.contract.MutableDrinksSource
import com.martin.meanbean.data.sources.implementation.local.LocalBeansSource
import com.martin.meanbean.data.sources.implementation.local.LocalDrinksSource
import com.martin.meanbean.data.sources.implementation.remote.RemoteBeansSource
import com.martin.meanbean.data.sources.implementation.remote.RemoteDrinksSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {
	@Binds
	@Singleton
	fun bindLocalBeansSourceToMutableBeansSource(localBeansSource: LocalBeansSource): MutableBeansSource

	@Binds
	@Singleton
	fun bindLocalDrinksSourceToMutableDrinksSource(localDrinksSource: LocalDrinksSource): MutableDrinksSource

	@Binds
	@Singleton
	fun bindRemoteBeansSourceToBeansSource(remoteBeansSource: RemoteBeansSource): BeansSource

	@Binds
	@Singleton
	fun bindRemoteDrinksSourceToDrinksSource(remoteDrinksSource: RemoteDrinksSource): DrinksSource
}