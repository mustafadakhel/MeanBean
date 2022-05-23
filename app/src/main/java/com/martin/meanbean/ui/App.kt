package com.martin.meanbean.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.martin.meanbean.ui.bean_details.BeanPage
import com.martin.meanbean.ui.drink_details.DrinkPage
import com.martin.meanbean.ui.home.HomePage
import com.martin.meanbean.utils.NavigationDestinations
import com.martin.meanbean.utils.Navigator
import com.martin.meanbean.utils.asLifecycleAwareState

const val BeanIdArgName = "beanId"
const val BeanTitleArgName = "beanTitle"

val BeanIdArg = navArgument(BeanIdArgName) {
	type = NavType.IntType
	nullable = false
}

val BeanTitleArg = navArgument(BeanTitleArgName) {
	defaultValue = "Bean"
	type = NavType.StringType
}

const val DrinkIdArgName = "beanId"
const val DrinkTitleArgName = "beanTitle"

val DrinkIdArg = navArgument(DrinkIdArgName) {
	type = NavType.IntType
	nullable = false
}

val DrinkTitleArg = navArgument(DrinkTitleArgName) {
	defaultValue = "Drink"
	type = NavType.StringType
}

@Composable
fun App(
	isDarkTheme: Boolean,
	navigator: Navigator,
	themeChanged: (Boolean) -> Unit
) {
	Scaffold(topBar = {
		AppBar(isDarkTheme, themeChanged)
	}) {
		val navController = rememberNavController()
		val lifecycleOwner = LocalLifecycleOwner.current
		val navigatorState by navigator.navActions.asLifecycleAwareState(lifecycleOwner)
		LaunchedEffect(navigatorState) {
			navigatorState?.let {
				it.args.forEach { arg ->
					navController.currentBackStackEntry?.arguments?.putParcelable(
						arg.key,
						arg.value
					)
				}
				navController.navigate(it.destination, it.navOptions)
			}
		}

		NavHost(navController, NavigationDestinations.homePage) {
			homePage(navController)
			beanPage()
			drinkPage()
		}
	}
}

private fun NavGraphBuilder.homePage(navController: NavHostController) = composable(
	route = NavigationDestinations.homePage
) {
	label = "Mean Bean"
	HomePage(navController = navController)
}

private fun NavGraphBuilder.beanPage() {
	composable(
		route = NavigationDestinations.beanPage,
		arguments = listOf(BeanIdArg, BeanTitleArg)
	) {
		label = it.arguments?.getString(BeanTitleArgName)
		BeanPage(it.arguments?.getInt(BeanIdArgName))
	}
}

private fun NavGraphBuilder.drinkPage() {
	composable(
		route = NavigationDestinations.drinkPage,
		arguments = listOf(DrinkIdArg, DrinkTitleArg)
	) {
		label = it.arguments?.getString(DrinkTitleArgName)
		DrinkPage(it.arguments?.getInt(DrinkIdArgName))
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
