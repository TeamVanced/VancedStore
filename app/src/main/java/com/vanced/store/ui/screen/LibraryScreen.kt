package com.vanced.store.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vanced.store.R
import com.vanced.store.ui.widget.ScreenScaffold
import com.vanced.store.ui.widget.ScreenTopAppBar

@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier
) {
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(scrollBehavior = scrollBehavior)
        }
    ) {

    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    ScreenTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = stringResource(R.string.navigation_library)
    )
}