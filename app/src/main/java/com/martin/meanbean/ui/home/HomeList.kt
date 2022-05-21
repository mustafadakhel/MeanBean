package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Wrap
import com.martin.meanbean.ui.common.errorItem
import com.martin.meanbean.ui.common.loadingItem

@Composable
fun HomeList(navController: NavController, feed: Wrap<List<HomeEntity>>) {
	LazyColumn(Modifier.fillMaxWidth()) {
		loadingItem(feed.isLoading)
		items(feed.data ?: listOf()) { HomeSegment(it, navController) }
		errorItem(feed.errorType)
	}
}