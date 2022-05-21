package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.Data
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun HomePage(navController: NavController, feed: State<List<Data<HomeEntity>>>) {
	HomeList(navController, feed)
}
