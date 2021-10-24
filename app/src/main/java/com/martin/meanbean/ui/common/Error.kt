package com.martin.meanbean.ui.common

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.Data

@Composable
fun Error(type: Data.ErrorType?) {
	Text(type.toString())
}

fun LazyListScope.errorItem(errorType: Data.ErrorType?) {
	if (errorType != null)
		item {
			Error(errorType)
		}
}