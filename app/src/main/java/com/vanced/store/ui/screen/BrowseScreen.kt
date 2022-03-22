package com.vanced.store.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.with
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.domain.manager.BrowseLayoutMode
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.widget.*
import org.koin.androidx.compose.getViewModel

@Composable
fun BrowseScreen(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: BrowseViewModel = getViewModel()
    val state = viewModel.state
    ScreenScaffold(
        modifier = modifier,
        topBar = { scrollBehavior ->
            AppBar(
                searchButtonEnabled = state.isLoaded,
                onSearchClick = onSearchClick,
                scrollBehavior = scrollBehavior,
                layoutMode = viewModel.layoutMode,
                onChangeLayoutMode = { layoutMode ->
                    viewModel.switchLayoutMode(layoutMode)
                }
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
                        layoutMode = viewModel.layoutMode
                    )
                }
                is BrowseViewModel.State.Loaded -> {
                    BrowseScreenApps(
                        modifier = Modifier.fillMaxSize(),
                        apps = state.apps,
                        layoutMode = viewModel.layoutMode
                    )
                }
            }
        }
    }
}

@Composable
fun BrowseScreenApps(
    modifier: Modifier = Modifier,
    apps: List<BrowseAppModel>,
    layoutMode: BrowseLayoutMode,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = layoutMode,
        transitionSpec = { layoutModeAnimationSpec() }
    ) { animatedLayoutMode ->
        when (animatedLayoutMode) {
            BrowseLayoutMode.LIST -> {
                CardLazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(apps) { app ->
                        LoadedListBrowseAppCard(
                            modifier = Modifier.fillParentMaxWidth(),
                            appName = app.appName,
                            appDescription = app.appDescription,
                            supportsNonroot = app.supportsNonroot,
                            supportsRoot = app.supportsRoot
                        )
                    }
                }
            }
            BrowseLayoutMode.GRID -> {
                CardLazyVerticalGrid(modifier = Modifier.fillMaxSize()) {
                    items(apps) { app ->
                        LoadedGridBrowseAppCard(
                            appName = app.appName,
                            appDescription = app.appDescription,
                            supportsNonroot = app.supportsNonroot,
                            supportsRoot = app.supportsRoot
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BrowseScreenLoading(
    modifier: Modifier = Modifier,
    layoutMode: BrowseLayoutMode,
) {
    when (layoutMode) {
        BrowseLayoutMode.LIST -> {
            CardLazyColumn(
                modifier = modifier,
                scrollEnabled = false
            ) {
                items(10) {
                    LoadingListBrowseAppCard(
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
        BrowseLayoutMode.GRID -> {
            CardLazyVerticalGrid(
                modifier = modifier,
                scrollEnabled = false
            ) {
                items(10) {
                    LoadingGridBrowseAppCard()
                }
            }
        }
    }
}

@Composable
private fun AppBar(
    onSearchClick: () -> Unit,
    onChangeLayoutMode: (BrowseLayoutMode) -> Unit,
    layoutMode: BrowseLayoutMode,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    searchButtonEnabled: Boolean = true,
) {
    ScreenTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = stringResource(id = R.string.app_name),
        actions = {
            IconButton(onClick = {
                onChangeLayoutMode(layoutMode.opposite())
            }) {
                AnimatedContent(
                    targetState = layoutMode,
                    transitionSpec = { layoutModeAnimationSpec() }
                ) { animatedLayoutMode ->
                    when (animatedLayoutMode) {
                        BrowseLayoutMode.LIST -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_list),
                                contentDescription = null
                            )
                        }
                        BrowseLayoutMode.GRID -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_grid),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
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

private fun AnimatedContentScope<BrowseLayoutMode>.layoutModeAnimationSpec(): ContentTransform {
    return if (BrowseLayoutMode.LIST isTransitioningTo BrowseLayoutMode.GRID) {
        slideIntoContainer(
            towards = AnimatedContentScope.SlideDirection.Start
        ) with slideOutOfContainer(
            towards = AnimatedContentScope.SlideDirection.Start
        )
    } else {
        slideIntoContainer(
            towards = AnimatedContentScope.SlideDirection.End
        ) with slideOutOfContainer(
            towards = AnimatedContentScope.SlideDirection.End
        )
    }
}