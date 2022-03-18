package com.vanced.store.ui.navigation

import androidx.compose.runtime.*

@Composable
fun rememberVSNavigatorBackstack(initial: VSNavigationScreen): VSNavigator {
    return remember {
        VSNavigatorBackstackImpl(initial)
    }
}

@Composable
fun rememberVSNavigator(initial: VSNavigationScreen): VSNavigator {
    return remember {
        VSNavigatorImpl(initial)
    }
}

interface VSNavigator {

    val current: VSNavigationScreen

    fun navigate(screen: VSNavigationScreen)

    fun back(): Boolean

}

class VSNavigatorImpl(initial: VSNavigationScreen) : VSNavigator {

    private var _current by mutableStateOf(initial)

    override val current: VSNavigationScreen
        get() = _current

    override fun navigate(screen: VSNavigationScreen) {
        _current = screen
    }

    override fun back(): Boolean {
        return false
    }
}

class VSNavigatorBackstackImpl(initial: VSNavigationScreen) : VSNavigator {

    private val _current = mutableStateListOf(initial)

    override val current: VSNavigationScreen
        get() = _current.last()

    override fun navigate(screen: VSNavigationScreen) {
        _current.add(screen)
    }

    override fun back(): Boolean {
        if (_current.isNotEmpty()) {
            _current.removeLast()
            return true
        }
        return false
    }
}