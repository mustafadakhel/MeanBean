package com.martin.meanbean.ui.home

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.Result

@Composable
fun Error(type: Result.ErrorType?) {
	Text(type.toString())
}

fun LazyListScope.errorItem(errorType: Result.ErrorType?) {
	if (errorType != null)
		item {
			Error(errorType)
		}
}