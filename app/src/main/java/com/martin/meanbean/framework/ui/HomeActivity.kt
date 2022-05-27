package com.martin.meanbean.framework.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.martin.meanbean.framework.ui.theme.AppTheme
import com.martin.meanbean.framework.ui.theme.MeanBeanTheme
import com.martin.meanbean.framework.utils.Navigator
import com.martin.meanbean.framework.utils.PrefsManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
	@Inject
	lateinit var prefsManager: PrefsManager

	@Inject
	lateinit var navigator: Navigator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MeanBeanTheme(initialTheme = prefsManager.getAppTheme()) {
				App(isDarkTheme = isDarkTheme(), navigator = navigator) {
					changeTheme(it)
					prefsManager.setAppTheme(if (it) AppTheme.DarkTheme else AppTheme.LightTheme)
				}
			}
		}
	}
}
