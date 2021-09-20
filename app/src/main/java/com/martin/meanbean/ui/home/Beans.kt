package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.martin.meanbean.domain.entities.HomeSubEntity

@Composable
fun Beans(homeBeans: List<HomeSubEntity>) {
	LazyRow(Modifier.padding(16.dp)) { beanItems(homeBeans) }
}
