package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.utils.NavigationRoutes.toBeanPage

@Composable
fun HomeSegment(data: HomeEntity, navController: NavController) {
	if (data.type is HomeEntity.HomeEntityType.Beans)
		Beans(data, beanItemClicked = {
			navController.toBeanPage(it)
		})
	else if (data.type is HomeEntity.HomeEntityType.Drinks)
		Drinks(data)
}
