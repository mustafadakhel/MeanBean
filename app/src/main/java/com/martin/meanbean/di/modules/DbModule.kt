package com.martin.meanbean.di.modules

import android.app.Application
import androidx.room.Room
import com.martin.meanbean.data.sources.implementation.local.db.BeansDao
import com.martin.meanbean.data.sources.implementation.local.db.DrinksDao
import com.martin.meanbean.data.sources.implementation.local.db.HomeDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

	@Provides
	@Singleton
	fun provideHomeDB(application: Application): HomeDB {
		return Room.databaseBuilder(application.applicationContext, HomeDB::class.java, "HOME_DB")
				.build()
	}

	@Provides
	@Singleton
	fun provideBeansDao(homeDB: HomeDB): BeansDao {
		return homeDB.beansDao
	}

	@Provides
	@Singleton
	fun provideDrinksDao(homeDB: HomeDB): DrinksDao {
		return homeDB.drinksDao
	}
}