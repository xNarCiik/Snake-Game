package com.dms.snake.feature_game.presentation.common.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun SnakeButtonIcon(imageVector: ImageVector, contentDescription: String, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, GreenSnake, shape = CircleShape)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = GreenSnake
        )
    }
}