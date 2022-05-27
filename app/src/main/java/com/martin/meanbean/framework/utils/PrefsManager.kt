package com.martin.meanbean.framework.utils

import android.app.Application
import androidx.preference.PreferenceManager
import com.martin.meanbean.framework.ui.theme.AppTheme
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsManager @Inject constructor(
	application: Application
) {

	private val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(
		application.applicationContext
	)

	fun setAppTheme(appTheme: AppTheme) {
		sharedPrefs.edit().putInt(AppTheme::class.simpleName, appTheme.value).apply()
	}

	fun getAppTheme(): AppTheme {
		return AppTheme.from(
			sharedPrefs.getInt(
				AppTheme::class.simpleName,
				AppTheme.DarkTheme.value
			)
		)
	}
}