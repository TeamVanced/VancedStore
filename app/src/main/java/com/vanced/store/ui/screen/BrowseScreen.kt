package com.vanced.store.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.domain.model.DomainBrowseApp
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.navigation.VSNavigationScreen
import com.vanced.store.ui.navigation.VSNavigator
import com.vanced.store.ui.util.category
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Stable
sealed interface BrowseState {
    @Stable
    object Loading : BrowseState

    @Stable
    data class Success(
        val pinnedApps: List<DomainBrowseApp>,
        val repoApps: List<DomainBrowseApp>
    ) : BrowseState

    @Stable
    object Error : BrowseState
}

@Composable
fun BrowseScreen(
    navigator: VSNavigator,
    modifier: Modifier = Modifier,
    viewModel: BrowseViewModel = getViewModel(),
) {
    BrowseScreen(
        modifier = modifier,
        onSearchClick = {
            navigator.navigate(VSNavigationScreen.Search)
        },
        onRefresh = viewModel::loadApps,
        state = viewModel.state
    )
}

@Composable
fun BrowseScreen(
    onSearchClick: () -> Unit,
    onRefresh: () -> Unit,
    state: BrowseState,
    modifier: Modifier = Modifier,
) {
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(
                searchButtonEnabled = state is BrowseState.Success,
                onSearchClick = onSearchClick,
                scrollBehavior = scrollBehavior,
            )
        }
    ) {
        VSSwipeRefresh(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            state = rememberSwipeRefreshState(state is BrowseState.Loading),
            onRefresh = onRefresh
        ) {
            when (state) {
                is BrowseState.Loading -> {
                    BrowseScreenLoading(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                is BrowseState.Success -> {
                    BrowseScreenSuccess(
                        pinnedApps = state.pinnedApps,
                        repositoryApps = state.repoApps,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is BrowseState.Error -> {

                }
            }
        }
    }
}

@Composable
private fun BrowseScreenSuccess(
    pinnedApps: List<DomainBrowseApp>,
    repositoryApps: List<DomainBrowseApp>,
    modifier: Modifier = Modifier,
) {
    CardLazyVerticalGrid(modifier = modifier) {
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
private fun BrowseScreenLoading(
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