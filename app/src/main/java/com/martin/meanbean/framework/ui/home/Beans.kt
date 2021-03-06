package com.martin.meanbean.framework.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.martin.meanbean.domain.models.HomeSegment
import com.martin.meanbean.domain.models.HomeSubSegment

@Composable
fun Beans(homeBeans: HomeSegment, beanItemClicked: (HomeSubSegment) -> Unit) {
	Column(Modifier.padding(16.dp)) {
		Text(
			text = homeBeans.title,
			modifier = Modifier.padding(bottom = 12.dp),
			style = MaterialTheme.typography.h6
		)
		LazyRow { beanItems(homeBeans.items, onItemClicked = beanItemClicked) }
	}
}
