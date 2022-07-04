package com.vanced.store.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.util.category
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Composable
fun BrowseScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BrowseViewModel = getViewModel()
) {
    val state = viewModel.state
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(
                searchButtonEnabled = state.isLoaded,
                onSearchClick = onSearchClick,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        VSSwipeRefresh(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = rememberSwipeRefreshState(state.isLoading),
            onRefresh = {
                viewModel.loadApps()
            }
        ) {
            when (state) {
                is BrowseViewModel.State.Loading -> {
                    BrowseScreenLoading(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                is BrowseViewModel.State.Loaded -> {
                    BrowseScreenApps(
                        pinnedApps = state.pinnedApps,
                        repositoryApps = state.repoApps,
                    )
                }
                is BrowseViewModel.State.Error -> {

                }
            }
        }
    }
}

@Composable
fun BrowseScreenApps(
    pinnedApps: List<DomainBrowseApp>,
    repositoryApps: List<DomainBrowseApp>,
) {
    CardLazyVerticalGrid(modifier = Modifier.fillMaxSize()) {
        if (pinnedApps.isNotEmpty()) {
            category(title = {
                Text(stringResource(id = R.string.browse_category_vanced))
            }) {
                items(pinnedApps) { app ->
                    BrowseAppCardLoaded(
                        appName = app.appName,
                        appDescription = app.appDescription,
                        supportsNonroot = app.supportsNonroot,
                        supportsRoot = app.supportsRoot,
                        onDetailsClick = {

                        }
                    )
                }
            }
        }
        if (repositoryApps.isNotEmpty()) {
            category(title = {
                Text(stringResource(id = R.string.browse_category_apps))
            }) {
                items(repositoryApps) { app ->
                    BrowseAppCardLoaded(
                        appName = app.appName,
                        appDescription = app.appDescription,
                        supportsNonroot = app.supportsNonroot,
                        supportsRoot = app.supportsRoot,
                        onDetailsClick = {

                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BrowseScreenLoading(
    modifier: Modifier = Modifier,
) {
    CardLazyVerticalGrid(
        modifier = modifier,
        scrollEnabled = false
    ) {
        items(10) {
            BrowseAppCardLoading()
        }
    }
}

@Composable
private fun AppBar(
    onSearchClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    searchButtonEnabled: Boolean = true,
) {
    SmallTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(
                onClick = onSearchClick,
                enabled = searchButtonEnabled
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            }
        }
    )
}