package com.martin.meanbean.ui.bean_details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.HomeSubEntity

@Composable
fun BeanPage(subEntity: HomeSubEntity) {
	Column {
		Text(text = subEntity.title)
	}
}
