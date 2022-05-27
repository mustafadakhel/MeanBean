package com.martin.meanbean.di.modules

import com.martin.meanbean.framework.utils.ComposeNavigator
import com.martin.meanbean.framework.utils.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
	@Singleton
	@Binds
	fun mapComposeNavigatorToNavigator(composeNavigator: ComposeNavigator): Navigator
}