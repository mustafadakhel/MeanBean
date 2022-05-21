package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.Wrap
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun HomePage(navController: NavController, feed: Wrap<List<HomeEntity>>) {
	HomeList(navController, feed)
}
