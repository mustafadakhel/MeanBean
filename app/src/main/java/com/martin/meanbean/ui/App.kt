package com.martin.meanbean.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.martin.meanbean.domain.entities.HomeSubEntity
import com.martin.meanbean.domain.entities.Wrap
import com.martin.meanbean.ui.bean_details.BeanPage
import com.martin.meanbean.ui.home.HomePage
import com.martin.meanbean.ui.home.HomeViewModel
import com.martin.meanbean.utils.NavigationRoutes

val dummyArgs = listOf(navArgument("dummy") {
	defaultValue = true
	type = NavType.BoolType
})

@Composable
fun App(
	isDarkTheme: Boolean,
	homeViewModel: HomeViewModel = hiltViewModel(),
	themeChanged: (Boolean) -> Unit
) {
	Scaffold(topBar = {
		AppBar(isDarkTheme, themeChanged)
	}) {
		val feed by homeViewModel.wrappedHomeListFlow.collectAsState(initial = Wrap.loading())
		val navController = rememberNavController()
		NavHost(navController, NavigationRoutes.home) {
			composable(NavigationRoutes.home, arguments = dummyArgs) {
				label = "Mean Bean"
				HomePage(navController = navController, feed)
			}
			composable(NavigationRoutes.bean) {
				HomeSubEntity.fromArgs(navController.previousBackStackEntry?.arguments)
						?.let { subEntity ->
							label = subEntity.title
							BeanPage(subEntity)
						}
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