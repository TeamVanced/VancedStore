package com.vanced.store.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.vanced.store.ui.component.VSCard
import com.vanced.store.ui.theme.VSTheme

@Composable
fun Label(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    containerColor: Color = VSTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(containerColor),
    content: @Composable RowScope.() -> Unit,
) {
    VSCard(
        modifier = modifier,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        ProvideTextStyle(VSTheme.typography.labelLarge) {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = VSTheme.spacing.innerLarge,
                        vertical = VSTheme.spacing.innerMedium
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}