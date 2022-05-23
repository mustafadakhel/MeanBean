package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.martin.meanbean.domain.models.HomeSegment
import com.martin.meanbean.ui.common.errorItem
import com.martin.meanbean.ui.common.loadingItem

@Composable
fun HomeList(navController: NavController, feed: List<HomeSegment>, era: HomeViewModel.HomeEras) {
	LazyColumn(Modifier.fillMaxWidth()) {
		loadingItem(era is HomeViewModel.HomeEras.Loading)
		items(feed) { HomeSegment(it, navController) }
		errorItem(if ((era is HomeViewModel.HomeEras.Failure)) era.throwable else null)
	}
}