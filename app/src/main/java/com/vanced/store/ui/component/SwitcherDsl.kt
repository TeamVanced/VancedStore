package com.vanced.store.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vanced.store.ui.theme.VSTheme

interface SwitcherListScope {

    fun item(
        content: @Composable SwitcherItemScope.() -> Unit
    )

    fun items(
        count: Int,
        itemContent: @Composable SwitcherItemScope.(index: Int) -> Unit
    )

}

class SwitcherListScopeImpl : SwitcherListScope {

    private val _intervals = mutableListOf<DividedItemIntervalContent>()
    val intervals: List<DividedItemIntervalContent> = _intervals

    override fun item(
        content: @Composable SwitcherItemScope.() -> Unit
    ) {
        _intervals.add(
            DividedItemIntervalContent(
                content = { @Composable { content() } }
            )
        )
    }

    override fun items(
        count: Int,
        itemContent: @Composable SwitcherItemScope.(index: Int) -> Unit
    ) {
        repeat(count) {
            _intervals.add(
                DividedItemIntervalContent(
                    content = { @Composable { itemContent(it) } }
                )
            )
        }
    }
}

inline fun <T> SwitcherListScope.items(
    items: Array<T>,
    crossinline itemContent: @Composable SwitcherItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

inline fun <T> SwitcherListScope.items(
    items: List<T>,
    crossinline itemContent: @Composable SwitcherItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

interface SwitcherItemScope
class SwitcherItemScopeImpl : SwitcherItemScope

class DividedItemIntervalContent(
    val content: SwitcherItemScope.() -> @Composable () -> Unit
)

private val SwitcherRowHeight = 48.dp
private val SwitcherItemMinWidth = 56.dp

@Composable
fun SwitcherRow(
    modifier: Modifier = Modifier,
    content: SwitcherListScope.() -> Unit
) {
    val itemScope = remember { SwitcherItemScopeImpl() }
    val latestContent = rememberUpdatedState(content)
    val items by remember {
        derivedStateOf {
            val listScope = SwitcherListScopeImpl()
            listScope.apply(latestContent.value)
            listScope.intervals
        }
    }
    Surface(
        modifier = modifier
            .height(SwitcherRowHeight)
            .widthIn(min = SwitcherItemMinWidth)
            .clip(RoundedCornerShape(12.dp)),
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.width(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, content ->
                if (index != 0) {
                    //Divider
                    Box(
                        modifier = Modifier
                            .background(color = VSTheme.colorScheme.surface)
                            .width(2.dp)
                            .height(SwitcherRowHeight)
                    )
                }
                content.content(itemScope)()
            }
        }
    }
}

@Suppress("unused")
@Composable
fun SwitcherItemScope.SwitcherItem(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    val color by animateColorAsState(
        if (selected) VSTheme.colorScheme.secondaryContainer else Color.Transparent
    )
    Box(
        modifier = modifier
            .widthIn(min = SwitcherItemMinWidth)
            .height(SwitcherRowHeight)
            .background(color)
            .clickable(onClick = onClick, enabled = enabled)
            .padding(horizontal = VSTheme.spacing.innerSmall),
        contentAlignment = Alignment.Center
    ) {
        icon()
    }
}