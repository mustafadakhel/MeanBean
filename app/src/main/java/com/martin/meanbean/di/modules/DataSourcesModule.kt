package com.martin.meanbean.di.modules

import com.martin.meanbean.data.sources.BeansSource
import com.martin.meanbean.data.sources.DrinksSource
import com.martin.meanbean.data.sources.MutableBeansSource
import com.martin.meanbean.data.sources.MutableDrinksSource
import com.martin.meanbean.framework.sources.local.LocalBeansSource
import com.martin.meanbean.framework.sources.local.LocalDrinksSource
import com.martin.meanbean.framework.sources.remote.RemoteBeansSource
import com.martin.meanbean.framework.sources.remote.RemoteDrinksSource
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