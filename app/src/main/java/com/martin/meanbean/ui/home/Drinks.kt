package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.martin.meanbean.domain.entities.HomeSubEntity

@Composable
fun Drinks(homeDrinks: List<HomeSubEntity>) {
	val rowCount = (homeDrinks.size / 3f).toInt()
	BoxWithConstraints(modifier = Modifier.padding(16.dp)) {
		val width = maxWidth
		Column {
			for (i: Int in 0 until rowCount)
				Row {
					for (j: Int in i + ((3 - 1) * i) until (i + ((3 - 1) * i) + 3))
						DrinkItem(homeDrink = homeDrinks[j], width / 3)
				}
		}
	}
}
