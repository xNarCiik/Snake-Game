package com.dms.snake.feature_game.presentation.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.dms.snake.feature_game.domain.model.Point
import com.dms.snake.feature_game.domain.util.SnakeOrientation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    var paused by mutableStateOf(false)
        private set

    var currentScore by mutableStateOf(0)
        private set

    var snakeState by mutableStateOf(SnakeState())
        private set

    var foodState by mutableStateOf<FoodState?>(null)
        private set

    private var snakeOrientation = SnakeOrientation.RIGHT
    private var lastSnakeOrientation = snakeOrientation

    private var screenSize: IntSize? = null
    var gameIsLaunched = false // TODO remove this check ?

    fun launchGame(screenSize: IntSize) {
        if (gameIsLaunched) return
        gameIsLaunched = true
        this.screenSize = screenSize

        CoroutineScope(Dispatchers.IO).launch {
            updateFoodPosition()
            var loose = false
            while (!loose) {
                if (!paused) {
                    CoroutineScope(Dispatchers.Main).launch {
                        lastSnakeOrientation = snakeOrientation
                        val snakePositions = snakeState.positionsList
                        val newSnakePositions = arrayListOf<Point>()
                        var snakeHasEat: Boolean
                        // Calculate next position of first point
                        snakePositions.first().let { point ->
                            val firstPoint = when (snakeOrientation) {
                                SnakeOrientation.UP -> point.copy(y = point.y - SnakeState.SIZE_SNAKE)
                                SnakeOrientation.DOWN -> point.copy(y = point.y + SnakeState.SIZE_SNAKE)
                                SnakeOrientation.LEFT -> point.copy(x = point.x - SnakeState.SIZE_SNAKE)
                                SnakeOrientation.RIGHT -> point.copy(x = point.x + SnakeState.SIZE_SNAKE)
                            }

                            // Check if snake eat itself or wall
                            loose =
                                snakePositions.contains(firstPoint) || firstPoint.x < 0 || firstPoint.y < 0 || firstPoint.y >= screenSize.height || firstPoint.x >= screenSize.width
                            snakeHasEat = point == foodState?.position
                            newSnakePositions.add(firstPoint)
                        }

                        snakePositions.forEachIndexed { index, _ ->
                            if (snakeHasEat) {
                                newSnakePositions.add(snakePositions[index].copy())
                            } else if (index != 0) {
                                newSnakePositions.add(snakePositions[index - 1].copy())
                            }
                        }
                        if (snakeHasEat) {
                            increaseCurrentScore()
                            updateFoodPosition()
                        }

                        snakeState = snakeState.copy(positionsList = newSnakePositions)
                    }
                    withContext(Dispatchers.IO) {
                        Thread.sleep(100)
                    }
                }
            }
        }
    }

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.Pause -> this.paused = event.paused
            is GameEvent.ChangeSnakeOrientation -> updateOrientation(event.orientation)

        }
    }

    private fun updateOrientation(snakeOrientation: SnakeOrientation) {
        if (this.paused) {
            return
        }
        var allowUpdate = true
        when (lastSnakeOrientation) {
            SnakeOrientation.UP -> if (snakeOrientation == SnakeOrientation.DOWN) allowUpdate =
                false
            SnakeOrientation.DOWN -> if (snakeOrientation == SnakeOrientation.UP) allowUpdate =
                false
            SnakeOrientation.LEFT -> if (snakeOrientation == SnakeOrientation.RIGHT) allowUpdate =
                false
            SnakeOrientation.RIGHT -> if (snakeOrientation == SnakeOrientation.LEFT) allowUpdate =
                false
        }
        if (allowUpdate) {
            this.snakeOrientation = snakeOrientation
        }
    }

    private fun increaseCurrentScore() {
        currentScore++
    }

    private fun updateFoodPosition() {
        screenSize?.let {
            val x =
                SnakeState.SIZE_SNAKE * (1 until (it.width / SnakeState.SIZE_SNAKE).toInt()).random()
            val y =
                SnakeState.SIZE_SNAKE * (1 until (it.height / SnakeState.SIZE_SNAKE).toInt()).random()
            foodState = FoodState(Point(x, y))
        }
    }
}