package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun HomeList(homeViewModel: HomeViewModel) {
	val feed = homeViewModel.homeFeed.collectAsState(initial = listOf())
	LazyColumn {
		items(feed.value) {
			HomeSegment(it)
		}
	}
}
