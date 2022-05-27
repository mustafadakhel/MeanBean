package com.martin.meanbean.framework.ui.bean_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.models.Bean
import com.martin.meanbean.framework.ui.common.Error
import com.martin.meanbean.framework.ui.common.Loading

@Composable
fun BeanPage(beanId: Int?, viewModel: BeanPageViewModel = hiltViewModel()) {

	LaunchedEffect(key1 = beanId, block = {
		viewModel.fetch(beanId)
	})
	val bean by viewModel.beanFlow.collectAsState()
	val era by viewModel.timeMachine.flowEras().collectAsState()
	when (era) {
		BeanPageViewModel.Eras.Loading -> Loading()
		BeanPageViewModel.Eras.Loaded -> BeanDetails(bean = bean!!)
		is BeanPageViewModel.Eras.Failure -> Error(throwable = (era as BeanPageViewModel.Eras.Failure).throwable)
	}
}

@Composable
fun BeanDetails(bean: Bean) {
	Column(
		Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
	) {
		BeanImage(image = bean.image, description = bean.name)
		BeanTitle(bean.name)
		BeanDescription(bean.description)
	}
}

@Composable
fun BeanImage(image: String, description: String) {
	Image(
		painter = rememberImagePainter(image),
		contentDescription = description,
		contentScale = ContentScale.FillWidth,
		alpha = 0.8f,
		modifier = Modifier
				.fillMaxWidth()
				.height(450.dp)
	)
}

@Composable
fun BeanTitle(name: String) {
	Text(
		text = name,
		textAlign = TextAlign.Center,
		modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp, vertical = 12.dp),
		fontWeight = FontWeight.Bold,
		fontSize = 18.sp
	)
}

@Composable
fun BeanDescription(description: String) {
	Text(
		text = description,
		textAlign = TextAlign.Start,
		modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp, vertical = 12.dp)
	)
}
