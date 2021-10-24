package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.Data
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.utils.NavigationRoutes.toBeanPage

@Composable
fun HomeSegment(data: Data<HomeEntity>, navController: NavController) {
	if (data.data?.type is HomeEntity.HomeEntityType.Beans)
		Beans(data.data, beanItemClicked = {
			navController.toBeanPage(it)
		})
	else if (data.data?.type is HomeEntity.HomeEntityType.Drinks)
		Drinks(data.data)
}
