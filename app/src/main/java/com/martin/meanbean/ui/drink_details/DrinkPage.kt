package com.martin.meanbean.ui.drink_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.martin.meanbean.domain.models.Drink
import com.martin.meanbean.ui.common.Error
import com.martin.meanbean.ui.common.Loading

@Composable
fun DrinkPage(drinkId: Int?, viewModel: DrinkPageViewModel = hiltViewModel()) {

	LaunchedEffect(key1 = drinkId, block = {
		viewModel.fetch(drinkId)
	})
	val drink by viewModel.drinkFlow.collectAsState()
	val era by viewModel.timeMachine.flowEras().collectAsState()
	when (era) {
		DrinkPageViewModel.Eras.Loading -> Loading()
		DrinkPageViewModel.Eras.Loaded -> DrinkDetails(drink = drink!!)
		is DrinkPageViewModel.Eras.Failure -> Error(throwable = (era as DrinkPageViewModel.Eras.Failure).throwable)
	}
}

@Composable
fun DrinkDetails(drink: Drink) {
	Column(
		Modifier
				.fillMaxSize()
				.verticalScroll(rememberScrollState())
	) {
		DrinkImage(image = drink.image, description = drink.name)
		DrinkTitle(drink.name)
		Text(
			modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.padding(top = 8.dp),
			text = drink.cup, fontWeight = FontWeight.Bold
		)
		Text(
			modifier = Modifier
					.align(Alignment.CenterHorizontally)
					.padding(bottom = 8.dp),
			text = drink.ratio,
			fontWeight = FontWeight.Bold
		)
		DrinkDescription(drink.description)
	}
}

@Composable
fun DrinkImage(image: String, description: String) {
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
fun DrinkTitle(name: String) {
	Text(
		text = name,
		textAlign = TextAlign.Center,
		modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp, vertical = 12.dp),
		fontSize = 24.sp
	)
}

@Composable
fun DrinkDescription(description: String) {
	Text(
		text = description,
		textAlign = TextAlign.Start,
		modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp, vertical = 12.dp)
	)
}
