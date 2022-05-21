package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, homeViewModel: HomeViewModel) {
	val feed by homeViewModel.HomeListFlow.collectAsState(initial = listOf())
	val eras by homeViewModel.homeTimeMachine.flow().collectAsState()
	HomeList(navController, feed, eras)
}
