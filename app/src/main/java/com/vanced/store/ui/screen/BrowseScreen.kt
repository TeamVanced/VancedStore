package com.vanced.store.ui.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vanced.store.R
import com.vanced.store.domain.manager.BrowseLayoutMode
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.ui.component.VSSwipeRefresh
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.widget.LoadedGridBrowseAppCard
import com.vanced.store.ui.widget.LoadedListBrowseAppCard
import com.vanced.store.ui.widget.LoadingGridBrowseAppCard
import com.vanced.store.ui.widget.LoadingListBrowseAppCard

@Composable
fun BrowseScreen(
    onSearchClick: () -> Unit,
    viewModel: BrowseViewModel,
    modifier: Modifier = Modifier,
) {
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    val state = viewModel.state
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppBar(
                searchButtonEnabled = state.isBrowse,
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
                is BrowseViewModel.State.Browse -> {
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
                BrowseAppLazyColumn {
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
                BrowseAppLazyVerticalGrid {
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
            BrowseAppLazyColumn(
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
            BrowseAppLazyVerticalGrid(
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
    LargeTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = { Text(stringResource(id = R.string.app_name)) },
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

@Composable
private fun BrowseAppLazyColumn(
    modifier: Modifier = Modifier,
    scrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerLarge),
        contentPadding = PaddingValues(VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
    )
}

@Composable
private fun BrowseAppLazyVerticalGrid(
    modifier: Modifier = Modifier,
    scrollEnabled: Boolean = true,
    content: LazyGridScope.() -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium),
        horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium),
        contentPadding = PaddingValues(VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
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