package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.domain.entities.Result

@Composable
fun HomeSegment(result: Result<HomeEntity>) {
	LazyRow {
		items(result.data?.items ?: listOf()) {
			Text(text = it.title)
		}
	}
}
