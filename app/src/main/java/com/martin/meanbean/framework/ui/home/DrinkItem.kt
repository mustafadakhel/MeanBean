package com.martin.meanbean.framework.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.models.HomeSubSegment

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DrinkItem(homeDrink: HomeSubSegment, width: Dp, onClicked: () -> Unit) {
	Card(
		modifier = Modifier
				.width(width)
				.padding(6.dp)
				.clickable { onClicked() },
		elevation = 8.dp
	) {
		Column {
			Image(
				painter = rememberImagePainter(homeDrink.image),
				contentDescription = homeDrink.title,
				contentScale = ContentScale.Crop,
				alpha = 0.8f,
				modifier = Modifier.height(120.dp)
			)
			Text(
				homeDrink.title,
				softWrap = true,
				modifier = Modifier.padding(8.dp),
				overflow = TextOverflow.Ellipsis,
				maxLines = 1,
				style = MaterialTheme.typography.subtitle1
			)
		}
	}
}