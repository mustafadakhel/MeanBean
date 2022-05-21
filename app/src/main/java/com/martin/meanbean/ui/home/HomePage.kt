package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
	val feed by homeViewModel.homeListFlow.collectAsState(initial = listOf())
	val eras by homeViewModel.homeTimeMachine.flowEras().collectAsState()
	HomeList(navController, feed, eras)
}
