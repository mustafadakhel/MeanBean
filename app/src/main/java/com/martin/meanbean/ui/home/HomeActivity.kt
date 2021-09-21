package com.martin.meanbean.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.martin.meanbean.ui.theme.DarkColors
import com.martin.meanbean.ui.theme.LightColors
import com.martin.meanbean.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MeanBeanTheme {
				App()
			}
		}
	}
}

@Composable
fun MeanBeanTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	MaterialTheme(
		colors = if (darkTheme) DarkColors else LightColors,
		typography = Typography,
		content = content
	)
}