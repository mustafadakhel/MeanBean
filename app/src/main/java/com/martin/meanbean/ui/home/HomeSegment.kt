package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.HomeEntity
import com.martin.meanbean.utils.NavigationActions

@Composable
fun HomeSegment(data: HomeEntity, navController: NavController) {
	if (data.type is HomeEntity.HomeEntityType.Beans)
		Beans(data, beanItemClicked = {
			navController.navigate(
				NavigationActions.HomePage.homeToBeanPage(
					it.id,
					it.title
				).destination
			)
		})
	else if (data.type is HomeEntity.HomeEntityType.Drinks)
		Drinks(data)
}
