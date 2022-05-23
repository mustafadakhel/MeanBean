package com.martin.meanbean.di.modules

import com.martin.meanbean.BuildConfig
import com.martin.meanbean.data.sources.implementation.remote.retrofit.MeanBeanApi
import com.martin.meanbean.di.qualifiers.MeanBeanRetrofit
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
	@MeanBeanRetrofit
	fun provideMeanBeanRetrofit(): Retrofit {
		return Retrofit.Builder()
				.baseUrl(BuildConfig.COFFEE_API)
				.addConverterFactory(GsonConverterFactory.create())
				.build()
	}

	@Provides
	@Singleton
	fun provideMeanBeanApi(@MeanBeanRetrofit retrofit: Retrofit): MeanBeanApi {
		return retrofit.create(MeanBeanApi::class.java)
	}
}
