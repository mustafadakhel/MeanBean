package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.Data
import com.martin.meanbean.ui.common.errorItem
import com.martin.meanbean.ui.common.loadingItem

@Composable
fun HomeList(homeViewModel: HomeViewModel, navController: NavController) {
	val feed = homeViewModel.homeFeed.collectAsState(initial = listOf(Data()))
	LazyColumn(Modifier.fillMaxWidth()) {
		loadingItem(feed.value.any { it.isLoading })
		items(feed.value) { HomeSegment(it, navController) }
		errorItem(feed.value.find { it.isError }?.errorType)
	}
}