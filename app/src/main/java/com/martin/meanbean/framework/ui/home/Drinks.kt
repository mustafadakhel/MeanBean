package com.martin.meanbean.framework.ui.home

import androidx.compose.foundation.layout.BoxWithConstraints
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
fun Drinks(homeSegment: HomeSegment, drinkItemClicked: (HomeSubSegment) -> Unit) {
	val rowCount = (homeSegment.items.size / 3f).toInt()
	BoxWithConstraints(modifier = Modifier.padding(16.dp)) {
		val width = maxWidth
		Column {
			Text(
				text = homeSegment.title,
				modifier = Modifier.padding(bottom = 4.dp),
				style = MaterialTheme.typography.h6
			)
			for (i: Int in 0 until rowCount)
				LazyRow {
					for (j: Int in i + ((3 - 1) * i) until (i + ((3 - 1) * i) + 3))
						item {
							DrinkItem(
								homeDrink = homeSegment.items[j],
								width / 3
							) { drinkItemClicked(homeSegment.items[j]) }
						}
				}
		}
	}
}
