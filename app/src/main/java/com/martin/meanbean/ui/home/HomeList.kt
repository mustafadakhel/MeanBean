package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.martin.meanbean.domain.entities.Data

@Composable
fun HomeList(homeViewModel: HomeViewModel) {
	val feed = homeViewModel.homeFeed.collectAsState(initial = listOf(Data()))
	LazyColumn(Modifier.fillMaxWidth()) {
		loadingItem(feed.value.any { it.isLoading })
		items(feed.value) { HomeSegment(it) }
		errorItem(feed.value.find { it.isError }?.errorType)
	}
}