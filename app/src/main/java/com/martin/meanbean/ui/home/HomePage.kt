package com.martin.meanbean.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomePage(homeViewModel: HomeViewModel = hiltViewModel()) {
	Scaffold {
		HomeList(homeViewModel)
	}
}
