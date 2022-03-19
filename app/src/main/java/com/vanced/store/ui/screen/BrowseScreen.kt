package com.vanced.store.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.vanced.store.R
import com.vanced.store.domain.model.BrowseAppModel
import com.vanced.store.ui.component.SwitcherItem
import com.vanced.store.ui.component.SwitcherRow
import com.vanced.store.ui.component.items
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.BrowseViewModel
import com.vanced.store.ui.widget.LoadingBrowseAppCard
import org.koin.androidx.compose.getViewModel

@Composable
fun BrowseScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BrowseViewModel = getViewModel()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TwoStateToolbar(
                appBar = {
                    AppBar(
//                        searchButtonEnabled = !viewModel.screen.isLoading,
                        onSearchClick = {
                            viewModel.enterSearchScreen()
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
                searchBar = {
                    SearchBar(
                        value = viewModel.searchText,
                        onValueChange = {
                            viewModel.search(it)
                        },
                        onBackClick = {
                            viewModel.exitSearchScreen()
                        }
                    )
                },
                showSearch = viewModel.screen.isSearch
            )
        }
    ) {
        when (viewModel.screen) {
            is BrowseViewModel.Screen.Loading -> {
                BrowseScreenLoading(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    layoutMode = viewModel.layoutMode
                )
            }
            is BrowseViewModel.Screen.Browse -> {
                BrowseScreenApps(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    apps = viewModel.browseApps,
                    layoutMode = viewModel.layoutMode,
                    onChangeLayoutMode = { layoutMode ->
                        viewModel.switchLayoutMode(layoutMode)
                    }
                )
            }
            is BrowseViewModel.Screen.Search -> {

            }
        }
    }
}

@Composable
fun BrowseScreenApps(
    modifier: Modifier = Modifier,
    apps: List<BrowseAppModel>,
    layoutMode: BrowseViewModel.LayoutMode,
    onChangeLayoutMode: (BrowseViewModel.LayoutMode) -> Unit,
) {
    Column(
        modifier = modifier.padding(VSTheme.spacing.outerEdge),
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium)
    ) {
        LayoutModeIndicators(
            modifier = Modifier.align(Alignment.End),
            selected = layoutMode,
            onSelect = onChangeLayoutMode
        )
        AnimatedContent(
            modifier = Modifier.weight(1f),
            targetState = layoutMode,
            transitionSpec = {
                if (BrowseViewModel.LayoutMode.LIST isTransitioningTo BrowseViewModel.LayoutMode.GRID) {
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
        ) { animatedLayoutMode ->
            when (animatedLayoutMode) {
                BrowseViewModel.LayoutMode.LIST -> {
                    BrowseAppLazyColumn {
                        items(apps) { app ->

                        }
                    }
                }
                BrowseViewModel.LayoutMode.GRID -> {
                    BrowseAppLazyVerticalGrid {
                        items(apps) { app ->

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BrowseScreenLoading(
    modifier: Modifier = Modifier,
    layoutMode: BrowseViewModel.LayoutMode,
) {
    when (layoutMode) {
        BrowseViewModel.LayoutMode.LIST -> {
            BrowseAppLazyColumn(
                modifier = modifier,
                scrollEnabled = false
            ) {
                items(10) {
                    LoadingBrowseAppCard(
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }
        BrowseViewModel.LayoutMode.GRID -> {
            BrowseAppLazyVerticalGrid(
                modifier = modifier,
                scrollEnabled = false
            ) {
                items(10) {
                    LoadingBrowseAppCard()
                }
            }
        }
    }
}

@Composable
private fun TwoStateToolbar(
    modifier: Modifier = Modifier,
    appBar: @Composable () -> Unit,
    searchBar: @Composable () -> Unit,
    showSearch: Boolean
) {
    AnimatedContent(
        modifier = modifier,
        targetState = showSearch,
        transitionSpec = {
            if (false isTransitioningTo true) {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Up
                ) with slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Up
                )
            } else {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Down
                ) with slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Down
                )
            }
        }
    ) { animatedShowSearch ->
        if (animatedShowSearch) {
            searchBar()
        } else {
            appBar()
        }
    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier,
    searchButtonEnabled: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onSearchClick: () -> Unit
) {
    LargeTopAppBar(
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
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

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    BackHandler(onBack = onBackClick)
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = VSTheme.colorScheme.onSurface
        ),
        cursorBrush = SolidColor(VSTheme.colorScheme.primary)
    ) { field ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.innerSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
            Box {
                field()
                if (value.isEmpty()) {
                    val colorWithAlpha = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
                    CompositionLocalProvider(
                        LocalContentColor provides colorWithAlpha
                    ) {
                        Text("Search for apps...")
                    }
                }
            }
        }
    }
}

@Composable
private fun LayoutModeIndicators(
    modifier: Modifier = Modifier,
    selected: BrowseViewModel.LayoutMode,
    onSelect: (BrowseViewModel.LayoutMode) -> Unit,
) {
    SwitcherRow(
        modifier = modifier,
    ) {
        val modes = enumValues<BrowseViewModel.LayoutMode>()
        items(modes) { mode ->
            SwitcherItem(
                selected = selected == mode,
                onClick = { onSelect(mode) }
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = when (mode) {
                        BrowseViewModel.LayoutMode.LIST -> R.drawable.ic_list
                        BrowseViewModel.LayoutMode.GRID -> R.drawable.ic_grid
                    }),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun BrowseAppLazyColumn(
    modifier: Modifier = Modifier,
    scrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(VSTheme.spacing.outerMedium),
        contentPadding = PaddingValues(horizontal = VSTheme.spacing.outerEdge),
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
        contentPadding = PaddingValues(horizontal = VSTheme.spacing.outerEdge),
        userScrollEnabled = scrollEnabled,
        content = content
    )
}