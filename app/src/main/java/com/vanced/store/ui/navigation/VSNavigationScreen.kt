package com.vanced.store.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vanced.store.R

sealed class VSNavigationScreen(
    val route: String,

    @DrawableRes
    val iconRes: Int,

    @StringRes
    val labelRes: Int,
) {

    companion object {
        val bottomBarItems get() = arrayOf(
            Browse,
            Library
        )
    }

    object Browse : VSNavigationScreen(
        route = "browse",
        iconRes = R.drawable.ic_apps,
        labelRes = R.string.navigation_browse
    )

    object Library : VSNavigationScreen(
        route = "library",
        iconRes = R.drawable.ic_library,
        labelRes = R.string.navigation_library
    )

    object Search : VSNavigationScreen(
        route = "search",
        iconRes = 0,
        labelRes = 0
    )

}