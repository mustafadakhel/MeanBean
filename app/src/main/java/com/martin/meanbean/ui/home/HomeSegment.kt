package com.martin.meanbean.ui.home

import androidx.compose.runtime.Composable
import com.martin.meanbean.domain.entities.Data
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun HomeSegment(data: Data<HomeEntity>) {
	if (data.data?.type is HomeEntity.HomeEntityType.Beans)
		Beans(data.data)
	else if (data.data?.type is HomeEntity.HomeEntityType.Drinks)
		Drinks(data.data)
}
