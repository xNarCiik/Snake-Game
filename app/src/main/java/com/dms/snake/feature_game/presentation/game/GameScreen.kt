package com.dms.snake.feature_game.presentation.game

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dms.snake.feature_game.domain.util.SnakeOrientation
import com.dms.snake.ui.theme.GreenSnake
import kotlin.math.abs

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 22.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if(gameViewModel.paused){
                Icon(modifier = Modifier.clickable {
                    gameViewModel.onEvent(GameEvent.Pause(false))
                }, imageVector = Icons.Filled.PlayArrow, contentDescription = "resume", tint = GreenSnake)
            }else{
                Icon(modifier = Modifier.clickable {
                    gameViewModel.onEvent(GameEvent.Pause(true))
                }, imageVector = Icons.Filled.Pause, contentDescription = "pause", tint = GreenSnake)

            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Score",
                    style = MaterialTheme.typography.h3,
                    color = GreenSnake
                )
                Text(
                    text = gameViewModel.currentScore.toString(),
                    style = MaterialTheme.typography.h3,
                    color = GreenSnake
                )
            }



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
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    GameScreen(navController = navController)
}