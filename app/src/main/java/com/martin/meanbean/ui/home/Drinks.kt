package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun Drinks(homeEntity: HomeEntity) {
    val rowCount = (homeEntity.items.size / 3f).toInt()
    BoxWithConstraints(modifier = Modifier.padding(16.dp)) {
        val width = maxWidth
        Column {
            Text(
                    text = homeEntity.title,
                    modifier=Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.h6
            )
            for (i: Int in 0 until rowCount)
                LazyRow {
                    for (j: Int in i + ((3 - 1) * i) until (i + ((3 - 1) * i) + 3))
                        item {
                            DrinkItem(homeDrink = homeEntity.items[j], width / 3)
                        }
                }
        }
    }
}
