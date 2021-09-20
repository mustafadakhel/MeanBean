package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Data

@Composable
fun HomeSegment(data: Data<HomeEntity>) {
	LazyRow {
		items(data.data?.items ?: listOf()) {
			Text(text = it.title)
		}
	}
}
