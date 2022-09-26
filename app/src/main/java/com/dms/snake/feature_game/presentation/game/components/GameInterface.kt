package com.dms.snake.feature_game.presentation.game.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dms.snake.feature_game.domain.util.SnakeOrientation
import com.dms.snake.feature_game.presentation.game.GameEvent
import com.dms.snake.feature_game.presentation.game.GameViewModel
import com.dms.snake.feature_game.presentation.game.SnakeState
import com.dms.snake.ui.theme.GreenSnake
import kotlin.math.abs

@Composable
fun GameInterface(gameViewModel: GameViewModel) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        var height: Dp
        var width: Dp

        with(LocalDensity.current) {
            height =
                ((maxHeight.toPx() / SnakeState.SIZE_SNAKE).toInt() * SnakeState.SIZE_SNAKE).toDp()
            width =
                ((maxWidth.toPx() / SnakeState.SIZE_SNAKE).toInt() * SnakeState.SIZE_SNAKE).toDp()
        }

        Surface(
            border = BorderStroke(2.dp, GreenSnake),
            modifier = Modifier
                .height(height)
                .width(width)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()

                        val (x, y) = dragAmount
                        val snakeOrientation = if (abs(x) > abs(y)) {
                            when {
                                x > 0 -> SnakeOrientation.RIGHT
                                x < 0 -> SnakeOrientation.LEFT
                                else -> null
                            }
                        } else {
                            when {
                                y > 0 -> SnakeOrientation.DOWN
                                y < 0 -> SnakeOrientation.UP
                                else -> null
                            }
                        }
                        snakeOrientation?.let {
                            gameViewModel.onEvent(
                                GameEvent.ChangeSnakeOrientation(
                                    snakeOrientation
                                )
                            )
                        }
                    }
                }
        ) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    gameViewModel.launchGame(it.size)
                }) {
                gameViewModel.apply {
                    snakeState.positionsList.forEachIndexed { index, point ->
                        drawRect(
                            color = if (index == 0) Color.Blue else GreenSnake,
                            topLeft = Offset(x = point.x, y = point.y),
                            size = Size(
                                SnakeState.SIZE_SNAKE - 5f,
                                SnakeState.SIZE_SNAKE - 5f
                            )
                        )
                    }

                    foodState?.position?.let { point ->
                        drawCircle(
                            color = Color.Red,
                            center = Offset(
                                x = point.x + SnakeState.HALF_SIZE_SNAKE,
                                y = point.y + SnakeState.HALF_SIZE_SNAKE
                            ),
                            radius = SnakeState.HALF_SIZE_SNAKE - 2.5f
                        )
                    }
                }
            }
        }
    }
}