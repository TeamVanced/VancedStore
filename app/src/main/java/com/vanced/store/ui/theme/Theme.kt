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
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.vanced.store.ui.theme.accents.BlueDarkThemeColors
import com.vanced.store.ui.theme.accents.BlueLightThemeColors

@Composable
fun VSTheme(
    darkMode: Boolean,
    content: @Composable () -> Unit
) {
    val isAndroid12OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    MaterialTheme(
        colorScheme = provideColorScheme(
            canUseDynamic = isAndroid12OrHigher,
            dynamic = { dynamicColorScheme(darkMode) },
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
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

}

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
private fun dynamicColorScheme(darkMode: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (darkMode) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }
}