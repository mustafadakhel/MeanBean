package com.martin.meanbean.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.martin.meanbean.ui.theme.AppTheme
import com.martin.meanbean.ui.theme.MeanBeanTheme
import com.martin.meanbean.utils.PrefsManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
	@Inject
	lateinit var prefsManager: PrefsManager

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MeanBeanTheme(initialTheme = prefsManager.getAppTheme()) {
				App(isDarkTheme = isDarkTheme()) {
					changeTheme(it)
					prefsManager.setAppTheme(if (it) AppTheme.DarkTheme else AppTheme.LightTheme)
				}
			}
		}
	}
}
