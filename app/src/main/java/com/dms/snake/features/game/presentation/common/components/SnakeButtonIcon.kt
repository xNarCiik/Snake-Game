package com.dms.snake.features.game.presentation.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun SnakeButtonIcon(
    modifier: Modifier = Modifier,
    size: Dp = 50.dp,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(size)
            .border(1.dp, GreenSnake, shape = CircleShape)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = GreenSnake
        )
    }
}

@Preview
@Composable
fun SnakeButtonIconPreview() {
    SnakeButtonIcon(imageVector = Icons.Rounded.Home, contentDescription = "") {}
}