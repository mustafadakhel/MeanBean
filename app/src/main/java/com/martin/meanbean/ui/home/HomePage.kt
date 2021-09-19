package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Result

@Composable
fun HomePage(homeViewModel: HomeViewModel) {
	HomeList(homeViewModel)
}
