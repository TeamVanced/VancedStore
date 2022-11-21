package com.vanced.store.ui.theme

import android.os.Build
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.vanced.store.datasource.AppAccent
import com.vanced.store.datasource.AppTheme
import com.vanced.store.ui.theme.accents.*

@Composable
fun VSTheme(
    theme: AppTheme,
    accent: AppAccent,
    canUseDynamic: Boolean = true,
    content: @Composable () -> Unit
) {
    val isAndroid12OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    MaterialTheme(
        colorScheme = provideColorScheme(
            canUseDynamic = isAndroid12OrHigher && canUseDynamic,
            theme = theme,
            accent = accent
        ),
        typography = VSTypography,
        shapes = VSShapes
    ) {
        CompositionLocalProvider(
            LocalSpacing provides VSSpacing,
            LocalOverscrollConfiguration provides if (isAndroid12OrHigher) OverscrollConfiguration() else null
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
        get() = MaterialTheme.shapes

    val spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacing.current

}

@Composable
private fun provideColorScheme(
    canUseDynamic: Boolean,
    theme: AppTheme,
    accent: AppAccent
): ColorScheme {
    val isDark = theme == AppTheme.Dark || (theme == AppTheme.System && isSystemInDarkTheme())
    return if (canUseDynamic) {
        provideDynamicColorScheme(isDark)
    } else {
        provideStaticColorScheme(accent, isDark)
    }
}

@Composable
fun provideDynamicColorScheme(isDark: Boolean): ColorScheme {
    val context = LocalContext.current
    return if (isDark) {
        dynamicDarkColorScheme(context)
    } else {
        dynamicLightColorScheme(context)
    }
}

@Composable
fun provideStaticColorScheme(
    accent: AppAccent,
    isDark: Boolean,
): ColorScheme {
    return when (accent) {
        AppAccent.Blue -> {
            if (isDark) {
                BlueDarkThemeColors
            } else {
                BlueLightThemeColors
            }
        }
        AppAccent.Orange -> {
            if (isDark) {
                OrangeDarkThemeColors
            } else {
                OrangeLightThemeColors
            }
        }
        AppAccent.Pink -> {
            if (isDark) {
                PinkDarkThemeColors
            } else {
                PinkLightThemeColors
            }
        }
        AppAccent.Purple -> {
            if (isDark) {
                PurpleDarkThemeColors
            } else {
                PurpleLightThemeColors
            }
        }
    }
}