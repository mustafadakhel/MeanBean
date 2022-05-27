package com.martin.meanbean.framework.ui.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.martin.meanbean.domain.models.HomeSegment
import com.martin.meanbean.framework.utils.NavigationActions

@Composable
fun HomeSegment(data: HomeSegment, navController: NavController) {
	if (data.type is HomeSegment.HomeSegmentType.Beans)
		Beans(data, beanItemClicked = {
			navController.navigate(
				NavigationActions.HomePage.homeToBeanPage(
					it.id,
					it.title
				).destination
			)
		})
	else if (data.type is HomeSegment.HomeSegmentType.Drinks)
		Drinks(data) {
			navController.navigate(
				NavigationActions.HomePage.homeToDrinkPage(
					it.id,
					it.title
				).destination
			)
		}
}
