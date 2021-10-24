package com.martin.meanbean.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun LazyListScope.loadingItem(loading: Boolean) {
	if (loading) item { Loading() }
}

@Composable
fun Loading() {
	Box(
		contentAlignment = Alignment.Center,
		modifier = Modifier
				.fillMaxWidth()
				.padding(all = 16.dp)
				.requiredSize(24.dp)
	) { CircularProgressIndicator(strokeWidth = 3.dp) }
}
