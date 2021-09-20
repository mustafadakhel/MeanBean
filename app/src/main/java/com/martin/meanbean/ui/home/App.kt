package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.martin.meanbean.utils.NavigationRoutes

@Composable
fun App() {
	NavHost(rememberNavController(), NavigationRoutes.home) {
		composable(NavigationRoutes.home) { HomePage() }
	}
}
