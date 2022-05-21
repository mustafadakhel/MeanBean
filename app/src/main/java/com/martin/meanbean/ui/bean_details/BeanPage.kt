package com.martin.meanbean.ui.bean_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.entities.BeanEntity
import com.martin.meanbean.ui.common.Error
import com.martin.meanbean.ui.common.Loading

@Composable
fun BeanPage(beanId: Int?, viewModel: BeanPageViewModel = hiltViewModel()) {
	LaunchedEffect(key1 = beanId, block = {
		viewModel.fetch(beanId)
	})
	val bean by viewModel.beanFlow.collectAsState()
	val era by viewModel.beanTimeMachine.flowEras().collectAsState()
	when (era) {
		BeanPageViewModel.BeanPageEra.Loading -> Loading()
		BeanPageViewModel.BeanPageEra.Loaded -> BeanDetails(bean = bean!!)
		is BeanPageViewModel.BeanPageEra.Failure -> Error(throwable = (era as BeanPageViewModel.BeanPageEra.Failure).throwable)
	}
}

@Composable
fun BeanDetails(bean: BeanEntity) {
	Column {
		Image(
			painter = rememberImagePainter(bean.image),
			contentDescription = bean.name,
			contentScale = ContentScale.FillWidth,
			alpha = 0.8f,
			modifier = Modifier
					.fillMaxWidth()
					.height(450.dp)
		)
		Text(
			text = bean.name,
			textAlign = TextAlign.Center,
			modifier = Modifier
					.fillMaxWidth()
					.align(Alignment.CenterHorizontally)
		)
	}
}