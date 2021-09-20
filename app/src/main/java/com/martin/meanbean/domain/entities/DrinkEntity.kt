package com.martin.meanbean.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "DRINK")
@Parcelize
data class DrinkEntity(
	@PrimaryKey
	val id: Int,
	var name: String = "",
	var description: String = "",
	var image: String = "",
	var ratio: String = "",
	var cup: String = "",
) : Parcelable {
	fun toHomeSubEntity(): HomeSubEntity {
		return HomeSubEntity(
			title = this.name,
			image = this.image
		)
	}
}

@JvmName("toHomeEntityDrinkEntity")
fun List<DrinkEntity>.toHomeEntity(): HomeEntity {
	return HomeEntity(
		title = "Drinks",
		items = map { it.toHomeSubEntity() })
}