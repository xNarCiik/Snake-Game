package com.example.sampleapp.feature_game.presentation.game.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleapp.feature_game.domain.model.Snake
import com.example.sampleapp.feature_game.domain.util.Difficulty
import com.example.sampleapp.feature_game.domain.util.Orientation
import com.example.sampleapp.feature_game.presentation.game.GameViewModel
import kotlin.math.abs

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "difficulty : ${Difficulty.NORMAL.stringValue}", // TODO load in preference
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "score : ${gameViewModel.currentScore}",
                    style = MaterialTheme.typography.h6
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                var height: Dp
                var width: Dp

                with(LocalDensity.current) {
                    height = ((maxHeight.toPx() / Snake.SIZE_SNAKE).toInt() * Snake.SIZE_SNAKE).toDp()
                    width = ((maxWidth.toPx() / Snake.SIZE_SNAKE).toInt() * Snake.SIZE_SNAKE).toDp()
                }

                Surface(
                    border = BorderStroke(1.dp, Color.White),
                    modifier = Modifier
                        .height(height)
                        .width(width)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()

                                val (x, y) = dragAmount
                                val orientation = if (abs(x) > abs(y)) {
                                    when {
                                        x > 0 -> Orientation.RIGHT
                                        x < 0 -> Orientation.LEFT
                                        else -> null
                                    }
                                } else {
                                    when {
                                        y > 0 -> Orientation.DOWN
                                        y < 0 -> Orientation.UP
                                        else -> null
                                    }
                                }
                                orientation?.let { gameViewModel.updateOrientation(it) }
                            }
                        }
                ) {
                    Canvas(modifier = Modifier
                        .fillMaxSize()
                        .onGloballyPositioned {
                            gameViewModel.launchGame(it.size)
                        }) {
                        gameViewModel.apply {
                            snake.positionsList.forEachIndexed { index, point ->
                                drawRect(
                                    color = if (index == 0) Color.Blue else Color.Cyan,
                                    topLeft = Offset(x = point.x, y = point.y),
                                    size = Size(Snake.SIZE_SNAKE, Snake.SIZE_SNAKE)
                                )
                            }

                            food?.position?.let { point ->
                                drawCircle(
                                    color = Color.Red,
                                    center = Offset(x = point.x + Snake.HALF_SIZE_SNAKE, y = point.y + Snake.HALF_SIZE_SNAKE),
                                    radius = Snake.HALF_SIZE_SNAKE
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}