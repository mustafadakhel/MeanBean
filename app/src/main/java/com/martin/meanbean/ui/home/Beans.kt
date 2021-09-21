package com.martin.meanbean.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.martin.meanbean.domain.entities.HomeEntity

@Composable
fun Beans(homeBeans: HomeEntity) {
    Column(Modifier.padding(16.dp)) {
        Text(text = homeBeans.title,modifier=Modifier.padding(bottom = 12.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)
        LazyRow { beanItems(homeBeans.items) }
    }
}
