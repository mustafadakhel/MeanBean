package com.martin.meanbean.domain.entities

import android.os.Bundle
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeSubEntity(val title: String, val image: String) : Parcelable {
	companion object {
		fun fromArgs(arguments: Bundle?): HomeSubEntity? {
			return arguments?.getParcelable("bean")
		}
	}
}