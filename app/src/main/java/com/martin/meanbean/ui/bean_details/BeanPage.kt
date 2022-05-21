package com.martin.meanbean.ui.bean_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.entities.HomeSubEntity

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BeanPage(subEntity: HomeSubEntity) {
	Column {
		Image(
			painter = rememberImagePainter(subEntity.image),
			contentDescription = subEntity.title,
			contentScale = ContentScale.FillWidth,
			alpha = 0.8f,
			modifier = Modifier
					.fillMaxWidth()
					.height(450.dp)
		)
		Text(
			text = subEntity.title,
			textAlign = TextAlign.Center,
			modifier = Modifier
					.fillMaxWidth()
					.align(Alignment.CenterHorizontally)
		)
	}
}
