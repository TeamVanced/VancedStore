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
import com.vanced.store.domain.manager.ApplicationAccent
import com.vanced.store.domain.manager.ApplicationTheme
import com.vanced.store.ui.theme.accents.*

@Composable
fun VSTheme(
    theme: ApplicationTheme,
    accent: ApplicationAccent,
    content: @Composable () -> Unit
) {
    val isAndroid12OrHigher = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    MaterialTheme(
        colorScheme = provideColorScheme(
            canUseDynamic = isAndroid12OrHigher,
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
    theme: ApplicationTheme,
    accent: ApplicationAccent
): ColorScheme {
    val isDark = theme.isDark()
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
    accent: ApplicationAccent,
    isDark: Boolean,
): ColorScheme {
    return when (accent) {
        ApplicationAccent.BLUE -> {
            if (isDark) {
                BlueDarkThemeColors
            } else {
                BlueLightThemeColors
            }
        }
        ApplicationAccent.ORANGE -> {
            if (isDark) {
                OrangeDarkThemeColors
            } else {
                OrangeLightThemeColors
            }
        }
        ApplicationAccent.PINK -> {
            if (isDark) {
                PinkDarkThemeColors
            } else {
                PinkLightThemeColors
            }
        }
        ApplicationAccent.PURPLE -> {
            if (isDark) {
                PurpleDarkThemeColors
            } else {
                PurpleLightThemeColors
            }
        }
    }
}

@Composable
fun ApplicationTheme.isDark(): Boolean {
    return when (this) {
        ApplicationTheme.DARK -> true
        ApplicationTheme.SYSTEM -> isSystemInDarkTheme()
        else -> false
    }
}