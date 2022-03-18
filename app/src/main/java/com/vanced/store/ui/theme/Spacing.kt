package com.vanced.store.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val VSSpacing = Spacing(
    outerEdge = 12.dp,
    outerSmall = 4.dp,
    outerMedium = 8.dp,
    outerLarge = 12.dp,
    innerEdge = 8.dp,
    innerSmall = 4.dp,
    innerMedium = 6.dp,
    innerLarge = 8.dp,
)

@Immutable
data class Spacing(
    val outerEdge: Dp = 8.dp,
    val outerSmall: Dp = 4.dp,
    val outerMedium: Dp = 8.dp,
    val outerLarge: Dp = 12.dp,
    val innerEdge: Dp = 8.dp,
    val innerSmall: Dp = 4.dp,
    val innerMedium: Dp = 6.dp,
    val innerLarge: Dp = 8.dp,
)