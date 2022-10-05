package com.dms.snake.features.game.presentation.game.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.dms.snake.features.game.presentation.game.GameEvent
import com.dms.snake.features.game.presentation.game.GameViewModel

@Composable
fun HeaderGame(
    gameViewModel: GameViewModel
) {
    ConstraintLayout(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        val (scoreView, btnPause) = createRefs()

        ScoreComponent(
            modifier = Modifier.constrainAs(scoreView) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            currentScore = gameViewModel.currentScore.toString()
        )

        PauseButton(
            modifier = Modifier.constrainAs(btnPause) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        ) {
            gameViewModel.onEvent(GameEvent.Pause(true))
        }
    }
}

@Composable
@Preview
fun HeaderGamePreview() {
    HeaderGame(
        gameViewModel = hiltViewModel()
    )
}