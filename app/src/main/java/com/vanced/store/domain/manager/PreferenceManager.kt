package com.vanced.store.domain.manager

import android.content.SharedPreferences

interface PreferenceManager {

    var browseLayoutMode: BrowseLayoutMode

    var applicationTheme: ApplicationTheme

    var applicationAccent: ApplicationAccent

}

class PreferenceManagerImpl(
    private val preferences: SharedPreferences
) : PreferenceManager {

    override var browseLayoutMode: BrowseLayoutMode
        get() {
            return BrowseLayoutMode.fromOrdinal(
                getInt(BROWSE_LAYOUT_MODE_KEY, BROWSE_LAYOUT_MODE_DEFAULT)
            )
        }
        set(value) {
            putInt(BROWSE_LAYOUT_MODE_KEY, value.ordinal)
        }

    override var applicationTheme: ApplicationTheme
        get() {
            return ApplicationTheme.fromOrdinal(
                getInt(APPLICATION_THEME_KEY, APPLICATION_THEME_DEFAULT)
            )
        }
        set(value) {
            putInt(APPLICATION_THEME_KEY, value.ordinal)
        }

    override var applicationAccent: ApplicationAccent
        get() {
            return ApplicationAccent.fromOrdinal(
                getInt(APPLICATION_ACCENT_KEY, APPLICATION_ACCENT_DEFAULT)
            )
        }
        set(value) {
            putInt(APPLICATION_ACCENT_KEY, value.ordinal)
        }

    companion object {
        const val BROWSE_LAYOUT_MODE_KEY = "browse_layout_mode"
        const val BROWSE_LAYOUT_MODE_DEFAULT = 0

        const val APPLICATION_THEME_KEY = "appearance_theme"
        const val APPLICATION_THEME_DEFAULT = 0

        const val APPLICATION_ACCENT_KEY = "appearance_accent"
        const val APPLICATION_ACCENT_DEFAULT = 0
    }

    private fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    private fun putInt(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }

}

enum class BrowseLayoutMode {
    LIST, GRID;

    fun opposite(): BrowseLayoutMode {
        return when (this) {
            LIST -> GRID
            GRID -> LIST
        }
    }

    companion object {
        fun fromOrdinal(ordinal: Int): BrowseLayoutMode {
            return when (ordinal) {
                LIST.ordinal -> LIST
                GRID.ordinal -> GRID
                else -> LIST
            }
        }
    }
}

enum class ApplicationTheme {
    SYSTEM,
    LIGHT,
    DARK;

    companion object {
        fun fromOrdinal(ordinal: Int): ApplicationTheme {
            return when (ordinal) {
                LIGHT.ordinal -> LIGHT
                DARK.ordinal -> DARK
                SYSTEM.ordinal -> SYSTEM
                else -> LIGHT
            }
        }
    }
}

enum class ApplicationAccent {
    BLUE,
    ORANGE,
    PINK,
    PURPLE;

    companion object {
        fun fromOrdinal(ordinal: Int): ApplicationAccent {
            return when (ordinal) {
                BLUE.ordinal -> BLUE
                ORANGE.ordinal -> ORANGE
                PINK.ordinal -> PINK
                PURPLE.ordinal -> PURPLE
                else -> BLUE
            }
        }
    }
}