package com.vanced.store.domain.manager

import android.content.SharedPreferences

interface PreferenceManager {

    var browseLayoutMode: BrowseLayoutMode

}

class PreferenceManagerImpl(
    private val preferences: SharedPreferences
) : PreferenceManager {

    override var browseLayoutMode: BrowseLayoutMode
        get() = BrowseLayoutMode.fromOrdinal(
            getInt(
                BROWSE_LAYOUT_MODE_KEY,
                BROWSE_LAYOUT_MODE_DEFAULT
            )
        )
        set(value) {
            putInt(BROWSE_LAYOUT_MODE_KEY, value.ordinal)
        }

    companion object {
        const val BROWSE_LAYOUT_MODE_KEY = "browse_layout_mode"
        const val BROWSE_LAYOUT_MODE_DEFAULT = 0
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
                0 -> LIST
                1 -> GRID
                else -> LIST
            }
        }
    }
}