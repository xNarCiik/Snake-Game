package com.dms.snake.features.game.presentation.game.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropUp
import androidx.compose.material.icons.rounded.ArrowLeft
import androidx.compose.material.icons.rounded.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.dms.snake.features.game.domain.util.SnakeOrientation
import com.dms.snake.features.game.presentation.common.components.SnakeButtonIcon
import com.dms.snake.features.game.presentation.game.GameEvent
import com.dms.snake.features.game.presentation.game.GameViewModel

@Composable
fun Gamepad(
    gameViewModel: GameViewModel
) {
    ConstraintLayout(
        modifier = Modifier.padding(16.dp)
    ) {

        val (btnDown, btnUp, btnLeft, btnRight) = createRefs()

        SnakeButtonIcon(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .constrainAs(btnUp) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            size = 90.dp,
            imageVector = Icons.Rounded.ArrowDropUp, contentDescription = "up_button"
        )
        {
            gameViewModel.onEvent(
                GameEvent.ChangeSnakeOrientation(
                    SnakeOrientation.UP
                )
            )
        }

        SnakeButtonIcon(
            modifier = Modifier
                .constrainAs(btnDown) {
                    top.linkTo(btnUp.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            size = 90.dp,
            imageVector = Icons.Rounded.ArrowDropDown, contentDescription = "down_button"
        )
        {
            gameViewModel.onEvent(
                GameEvent.ChangeSnakeOrientation(
                    SnakeOrientation.DOWN
                )
            )
        }

        SnakeButtonIcon(
            modifier = Modifier
                .constrainAs(btnLeft) {
                    top.linkTo(btnUp.top)
                    end.linkTo(btnUp.start)
                    bottom.linkTo(btnDown.bottom)
                },
            size = 90.dp,
            imageVector = Icons.Rounded.ArrowLeft, contentDescription = "left_button"
        )
        {
            gameViewModel.onEvent(
                GameEvent.ChangeSnakeOrientation(
                    SnakeOrientation.LEFT
                )
            )
        }

        SnakeButtonIcon(
            modifier = Modifier
                .constrainAs(btnRight) {
                    top.linkTo(btnUp.top)
                    start.linkTo(btnUp.end)
                    bottom.linkTo(btnDown.bottom)
                },
            size = 90.dp,
            imageVector = Icons.Rounded.ArrowRight, contentDescription = "right_button"
        )
        {
            gameViewModel.onEvent(
                GameEvent.ChangeSnakeOrientation(
                    SnakeOrientation.RIGHT
                )
            )
        }
    }
}

@Composable
@Preview
fun GamepadPreview() {
    Gamepad(gameViewModel = hiltViewModel())
}