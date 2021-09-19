package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.meanbean.utils.NavigationRoutes

@Composable
fun App() {
	val navController = rememberNavController()
	NavHost(navController, startDestination = NavigationRoutes.home) {
		composable(NavigationRoutes.home) {
			val backStackEntry = remember {
				navController.getBackStackEntry(NavigationRoutes.home)
			}
			val homeViewModel = hiltViewModel<HomeViewModel>(backStackEntry)
			HomePage(homeViewModel)
		}
	}
}
