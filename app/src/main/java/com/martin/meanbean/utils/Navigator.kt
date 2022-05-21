package com.martin.meanbean.utils

import android.os.Parcelable
import androidx.navigation.NavOptions
import com.martin.meanbean.ui.BeanIdArgName
import com.martin.meanbean.ui.BeanTitleArgName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

interface Navigator {
	val navActions: StateFlow<NavigationAction?>
	fun navigate(navAction: NavigationAction?)
}

@Singleton
class ComposeNavigator @Inject constructor() : Navigator {
	private val _navActions: MutableStateFlow<NavigationAction?> by lazy {
		MutableStateFlow(null)
	}
	override val navActions = _navActions.asStateFlow()

	override fun navigate(navAction: NavigationAction?) {
		_navActions.update { navAction }
	}
}

object NavigationDestinations {
	const val homePage = "homePage"
	const val beanPage = "$homePage/beanPage/{$BeanIdArgName}?$BeanTitleArgName={$BeanTitleArgName}"
}

interface NavigationAction {
	val destination: String
	val args: Map<String, Parcelable>
		get() = emptyMap()
	val navOptions: NavOptions
		get() = NavOptions.Builder().build()
}

object NavigationActions {
	object HomePage {
		fun homeToBeanPage(beanId: Int, beanTitle: String) = object : NavigationAction {
			override val destination =
				"${NavigationDestinations.homePage}/beanPage/$beanId?$BeanTitleArgName=$beanTitle"
			override val navOptions = NavOptions.Builder()
					.setPopUpTo(NavigationDestinations.homePage, inclusive = true, saveState = true)
					.setRestoreState(true)
					.build()
		}
	}

	object BeanPage
}