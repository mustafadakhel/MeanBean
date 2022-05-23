package com.martin.meanbean.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.models.HomeSubSegment

@OptIn(ExperimentalCoilApi::class)
@Composable
fun BeanItem(homeBean: HomeSubSegment, click: (HomeSubSegment) -> Unit) {
	Card(
		modifier = Modifier
				.padding(start = 4.dp, end = 4.dp)
				.clickable { click(homeBean) },
	) {
		Column {
			Image(
				painter = rememberImagePainter(homeBean.image),
				contentDescription = homeBean.title,
				contentScale = ContentScale.Crop,
				alpha = 0.8f,
				modifier = Modifier
						.height(120.dp)
						.aspectRatio(0.7f)
			)
			Text(
				text = homeBean.title,
				modifier = Modifier.padding(8.dp),
				style = MaterialTheme.typography.subtitle1
			)
		}
	}
}

fun LazyListScope.beanItems(
	homeBeans: List<HomeSubSegment>,
	onItemClicked: (HomeSubSegment) -> Unit
) {
	items(homeBeans) { BeanItem(homeBean = it, click = onItemClicked) }
}