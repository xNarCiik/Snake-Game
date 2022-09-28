package com.dms.snake.features.game.presentation.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.dms.snake.features.game.domain.model.Point
import com.dms.snake.features.game.domain.use_case.GameUseCases
import com.dms.snake.features.game.domain.util.SnakeOrientation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameUseCases: GameUseCases
) : ViewModel() {

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

    private fun increaseCurrentScore() {
        currentScore++
    }

    private fun updateOrientation(snakeOrientation: SnakeOrientation) {
        if (this.paused) {
            return
        }
        if (gameUseCases.isAllowToUpdateOrientation(lastSnakeOrientation, snakeOrientation)) {
            this.snakeOrientation = snakeOrientation
        }
    }

    private fun updateFoodPosition() {
        screenSize?.let {
            foodState = gameUseCases.generateFoodState(it)
        }
    }
}