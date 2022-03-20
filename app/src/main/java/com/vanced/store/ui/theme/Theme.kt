package com.vanced.store.ui.theme

import android.os.Build
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.gestures.OverScrollConfiguration
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import com.vanced.store.ui.theme.accents.BlueDarkThemeColors
import com.vanced.store.ui.theme.accents.BlueLightThemeColors

private inline fun provideColorScheme(
    dynamic: () -> ColorScheme,
    static: () -> ColorScheme,
    canUseDynamic: Boolean,
): ColorScheme {
    return if (canUseDynamic) {
        dynamic()
    } else {
        static()
    }
}

@Composable
fun VSTheme(
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isAndroid12OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    MaterialTheme(
        colorScheme = provideColorScheme(
            canUseDynamic = isAndroid12OrHigher,
            dynamic = {
                if (darkMode) {
                    dynamicDarkColorScheme(context)
                } else {
                    dynamicLightColorScheme(context)
                }
            },
            static = {
                if (darkMode) {
                    BlueDarkThemeColors
                } else {
                    BlueLightThemeColors
                }
            }
        ),
        typography = VSTypography
    ) {
        CompositionLocalProvider(
            LocalShapes provides VSShapes,
            LocalSpacing provides VSSpacing,
            LocalOverScrollConfiguration provides OverScrollConfiguration(
                forceShowAlways = isAndroid12OrHigher
            )
        ) {
            content()
        }
    }
}

object VSTheme {

    val colorScheme
        @Composable get() = MaterialTheme.colorScheme

    val typography
        @Composable get() = MaterialTheme.typography

    val shapes
        @Composable get() = LocalShapes.current

    val spacing
        @Composable get() = LocalSpacing.current

}