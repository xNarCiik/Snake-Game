package com.dms.snake.features.game.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun SnakeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintValue: String? = null,
    singleLine: Boolean = true
) {
    val textStyle = MaterialTheme.typography.h4.copy(textAlign = TextAlign.Center)
    val isFocused = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .padding(vertical = 4.dp)
            .onFocusChanged {
                isFocused.value = it.isFocused
            },
        placeholder = {
            hintValue?.let {
                if(!isFocused.value){
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        style = textStyle
                    )
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = GreenSnake,
            placeholderColor = GreenSnake
        ),
        textStyle = textStyle,
        singleLine = singleLine
    )
}

@Preview
@Composable
fun SnakeTextFieldPreview() {
    SnakeTextField(value = "Value", onValueChange = {})
}