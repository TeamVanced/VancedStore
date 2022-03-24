package com.vanced.store.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val VSSpacing = Spacing(
    extraLarge = 12.dp,
    large = 10.dp,
    medium = 8.dp,
    small = 6.dp,
    extraSmall = 4.dp,
)

@Immutable
data class Spacing(
    val extraLarge: Dp = 12.dp,
    val large: Dp = 10.dp,
    val medium: Dp = 8.dp,
    val small: Dp = 6.dp,
    val extraSmall: Dp = 4.dp,
)