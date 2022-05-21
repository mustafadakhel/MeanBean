package com.martin.meanbean.ui.common

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.Wrap

@Composable
fun Error(type: Wrap.ErrorType?) {
	Text(type.toString())
}

fun LazyListScope.errorItem(errorType: Wrap.ErrorType?) {
	if (errorType != null)
		item {
			Error(errorType)
		}
}