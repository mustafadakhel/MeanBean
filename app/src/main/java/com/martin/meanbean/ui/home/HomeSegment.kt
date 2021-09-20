package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.martin.meanbean.domain.entities.Data
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun HomeSegment(data: Data<HomeEntity>) {
	if (data.data?.type is HomeEntity.HomeEntityType.Beans)
		LazyRow(Modifier.padding(16.dp)) {
			items(data.data.items) {
				BeanItem(it)
			}
		}
	else if (data.data?.type is HomeEntity.HomeEntityType.Drinks)
		LazyRow {
			items(data.data.items) {
				Text(text = it.title)
			}
		}
}
