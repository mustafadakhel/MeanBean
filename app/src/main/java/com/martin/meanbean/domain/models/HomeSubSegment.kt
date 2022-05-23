package com.martin.meanbean.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeSubSegment(val id: Int, val title: String, val image: String) : Parcelable