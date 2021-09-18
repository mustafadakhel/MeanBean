package com.martin.meanbean.di.modules

import com.martin.meanbean.BuildConfig
import com.martin.meanbean.data.remote.network.MeanBeanApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideMeanBeanApi(): MeanBeanApi {
		return Retrofit.Builder()
				.baseUrl(BuildConfig.COFFEE_API)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(MeanBeanApi::class.java)
	}
}