package com.martin.meanbean.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bean(
	override val id: Int,
	override var name: String = "",
	override var image: String = "",
	var description: String = "",
) : Parcelable, HomeFeedItem