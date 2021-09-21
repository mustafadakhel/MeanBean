package com.martin.meanbean.ui.theme


import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Neutral8 = Color(0xff121722)
val Neutral7 = Color(0xff141c26)
val Neutral6 = Color(0xff17222a)
val Neutral5 = Color(0x61000000)
val Neutral4 = Color(0x1f000000)
val Neutral3 = Color(0x1fffffff)
val Neutral2 = Color(0x61ffffff)
val Neutral1 = Color(0xffdddddd)
val Neutral0 = Color(0xffffffff)

val Red = Color(0xffd00036)
val RedDark = Color(0xffea6d7e)
val Green = Color(0xff52c41a)
val Grey = Color(0xfff6f6f6)
val DarkGrey = Color(0xff2e2e2e)

val DarkColors = darkColors(
	primary = Neutral6,
	onPrimary = Neutral1,
	secondary = Neutral5,
	background = Neutral8,
	surface = Neutral6,
	onBackground = Neutral1,
	onSecondary = Neutral0,
	onSurface = Neutral1,
	primaryVariant = Neutral8,
	secondaryVariant = Neutral3,
	error = Red,
	onError = Neutral0,
)

val LightColors = lightColors(
	primary = Neutral0,
	onPrimary = Neutral7,
	secondary = Neutral3,
	background = Neutral1,
	surface = Neutral0,
	onBackground = Neutral7,
	onSecondary = Neutral8,
	onSurface = Neutral7,
	primaryVariant = Neutral1,
	secondaryVariant = Neutral5,
	error = Red,
	onError = Neutral0,
)

sealed class AppTheme {
	object DarkTheme : AppTheme()
	object LightTheme : AppTheme()
}
