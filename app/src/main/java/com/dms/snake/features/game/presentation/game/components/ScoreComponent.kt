package com.dms.snake.features.game.presentation.game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dms.snake.R
import com.dms.snake.ui.theme.GreenSnake

@Composable
fun ScoreComponent(
    modifier: Modifier = Modifier,
    currentScore: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.score),
            style = MaterialTheme.typography.h3,
            color = GreenSnake
        )
        Text(
            text = currentScore,
            style = MaterialTheme.typography.h3,
            color = GreenSnake
        )
    }
}

@Preview
@Composable
fun ScoreComponentPreview() {
    ScoreComponent(currentScore = "0")
}