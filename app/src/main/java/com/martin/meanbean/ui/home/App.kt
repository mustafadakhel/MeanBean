package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.meanbean.utils.NavigationRoutes

@Composable
fun App(isDarkTheme: Boolean, themeChanged: (Boolean) -> Unit) {
	Scaffold(topBar = {
		AppBar(isDarkTheme, themeChanged)
	}) {
		NavHost(rememberNavController(), NavigationRoutes.home) {
			composable(NavigationRoutes.home) {
				HomePage()
			}
		}
	}
}

@Composable
fun AppBar(isDarkTheme: Boolean, themeChanged: (Boolean) -> Unit) {
	Card(backgroundColor = MaterialTheme.colors.background, elevation = 12.dp) {
		Row(
			modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 12.dp, horizontal = 16.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(text = "Mean Bean", style = MaterialTheme.typography.h5)
			Switch(
				checked = isDarkTheme,
				onCheckedChange = (themeChanged),
			)
		}
	}
}