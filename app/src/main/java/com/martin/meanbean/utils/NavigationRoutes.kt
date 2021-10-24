package com.martin.meanbean.utils

import androidx.navigation.NavController
import com.martin.meanbean.domain.entities.HomeSubEntity

object NavigationRoutes {
	const val home = "home"
	const val bean = "$home/bean"
	const val drink = "$home/drink"

	fun NavController.toBeanPage(subEntity: HomeSubEntity) {
		currentBackStackEntry?.arguments?.putParcelable("bean", subEntity)
		navigate(bean)
	}
}