package com.martin.meanbean.framework.ui.common

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun Error(throwable: Throwable?) {
	Text(throwable?.message ?: "Something went wrong")
}

fun LazyListScope.errorItem(errorType: Throwable?) {
	if (errorType != null)
		item {
			Error(errorType)
		}
}