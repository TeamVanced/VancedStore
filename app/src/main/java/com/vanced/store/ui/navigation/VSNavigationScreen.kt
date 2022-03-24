package com.vanced.store.ui.navigation

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.vanced.store.R
import kotlinx.parcelize.Parcelize

sealed class VSNavigationScreen(
    val route: String,

    @DrawableRes
    val iconRes: Int,

    @StringRes
    val labelRes: Int,
): Parcelable {

    companion object {
        val bottomBarItems by lazy {
            arrayOf(
                Browse,
                Library,
                More
            )
        }
    }

    @Parcelize
    object Browse : VSNavigationScreen(
        route = "browse",
        iconRes = R.drawable.ic_apps,
        labelRes = R.string.navigation_browse
    )

    @Parcelize
    object Library : VSNavigationScreen(
        route = "library",
        iconRes = R.drawable.ic_library,
        labelRes = R.string.navigation_library
    )

    @Parcelize
    object More : VSNavigationScreen(
        route = "more",
        iconRes = R.drawable.ic_more,
        labelRes = R.string.navigation_more
    )

    @Parcelize
    object Search : VSNavigationScreen(
        route = "search",
        iconRes = 0,
        labelRes = 0
    )

    @Parcelize
    object Repositories : VSNavigationScreen(
        route = "repositories",
        iconRes = 0,
        labelRes = 0,
    )

    @Parcelize
    object Themes : VSNavigationScreen(
        route = "themes",
        iconRes = 0,
        labelRes = 0
    )

    override fun equals(other: Any?): Boolean {
        if (other !is VSNavigationScreen)
            return false

        return this.route == other.route
    }

    override fun hashCode(): Int {
        var result = route.hashCode()
        result = 31 * result + iconRes
        result = 31 * result + labelRes
        return result
    }

}