package com.martin.meanbean.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*

@Composable
fun MeanBeanTheme(initialTheme: AppTheme, content: @Composable() (ThemeScope.() -> Unit)) {
	var themeState by remember { mutableStateOf(value = initialTheme) }
	MaterialTheme(
		colors = if (themeState is AppTheme.DarkTheme) DarkColors else LightColors,
		typography = Typography,
		content = {
			content(object : ThemeScope {
				override fun isDarkTheme(): Boolean {
					return themeState is AppTheme.DarkTheme
				}

				override fun changeTheme(isDark: Boolean) {
					themeState = if (isDark) AppTheme.DarkTheme else AppTheme.LightTheme
				}
			})
		}
	)
}

interface ThemeScope {
	fun isDarkTheme(): Boolean
	fun changeTheme(isDark: Boolean) {}
}
