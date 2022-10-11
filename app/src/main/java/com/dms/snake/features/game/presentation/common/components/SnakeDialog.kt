package com.dms.snake.features.game.presentation.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun SnakeDialog(
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable (() -> Unit) = { }
) {
    // Use small trick : the dialog will not disappear directly when we do a popBackStack
    val showDialog = remember { mutableStateOf(true) }
    if (showDialog.value) {
        AlertDialog(
            modifier = Modifier.border(BorderStroke(2.dp, GreenSnake)),
            title = title,
            text = text,
            onDismissRequest = { },
            confirmButton = confirmButton,
            containerColor = Color.Black
        )
    }
}

@Preview
@Composable
fun SnakeDialogPreview() {
    SnakeDialog()
}