package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun HomePage(homeViewModel: HomeViewModel) {
	val feed = homeViewModel.homeFeed.collectAsState(initial = listOf())
	LazyColumn {
		items(feed.value) {
			LazyRow {
				items(it.data?.items ?: listOf()) {
					Text(text = it.title)
				}
			}
		}
	}
}