package com.martin.meanbean.domain.entities

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeSubEntity(val id: Int, val title: String, val image: String) : Parcelable