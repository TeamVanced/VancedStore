package com.vanced.store.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import com.vanced.store.ui.navigation.VSNavigator
import com.vanced.store.ui.theme.VSTheme
import com.vanced.store.ui.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(
    navigator: VSNavigator,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = getViewModel()
) {
    SearchScreen(
        modifier = modifier,
        onBackClick = { navigator.back() },
        searchValue = viewModel.searchValue,
        onSearchValueChange = viewModel::search
    )
}

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                value = searchValue,
                onValueChange = onSearchValueChange,
                onBackClick = onBackClick
            )
        }
    ) {
        it
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onBackClick: () -> Unit,
) {
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
            horizontalArrangement = Arrangement.spacedBy(VSTheme.spacing.extraSmall),
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