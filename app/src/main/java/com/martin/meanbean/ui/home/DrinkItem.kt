package com.martin.meanbean.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.entities.HomeSubEntity

@Composable
fun DrinkItem(homeDrink: HomeSubEntity, width: Dp) {
	Card(
		modifier = Modifier
				.width(width)
				.padding(6.dp),
		elevation = 8.dp
	) {
		Column(Modifier.background(Color.Black.copy(alpha = 0.8f))) {
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
				color = Color.White,
				modifier = Modifier.padding(8.dp),
				fontWeight = FontWeight.Bold,
				overflow = TextOverflow.Ellipsis,
				maxLines = 1
			)
		}
	}
}