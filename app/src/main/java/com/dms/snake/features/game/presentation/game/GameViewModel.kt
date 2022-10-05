package com.dms.snake.features.game.presentation.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.dms.snake.features.game.domain.model.Point
import com.dms.snake.features.game.domain.use_case.GameUseCases
import com.dms.snake.features.game.domain.util.GameState
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

    var gameState by mutableStateOf(GameState.NOT_STARTED)
        private set

    var currentScore by mutableStateOf(0)
        private set

    var snakeState by mutableStateOf(SnakeState())
        private set

    var foodState by mutableStateOf<FoodState?>(null)
        private set

    // Var updated only when snake move into this orientation
    private var currentSnakeOrientation = snakeState.orientation

    // Screen size where snake is allowed to move
    private var screenSize: IntSize? = null

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.Start -> launchGame(event.screenSize)
            is GameEvent.Restart -> restartGame()
            is GameEvent.Pause -> gameState =
                if (event.paused) GameState.PAUSED else GameState.STARTED
            is GameEvent.ChangeSnakeOrientation -> updateOrientation(event.orientation)
        }
    }

    private fun launchGame(screenSize: IntSize) {
        this.screenSize = screenSize
        if (this.gameState == GameState.NOT_STARTED) {
            this.gameState = GameState.STARTED

            CoroutineScope(Dispatchers.IO).launch {
                updateFoodPosition()

                while (gameState != GameState.FINISHED) {
                    if (gameState == GameState.STARTED) {
                        // Update the last orientation
                        currentSnakeOrientation = snakeState.orientation

                        val snakePositions = snakeState.positionsList
                        val newSnakePositions = arrayListOf<Point>()
                        var snakeHasEat: Boolean

                        snakePositions.first().let { currentFirstPoint ->
                            // Calculate the next first point
                            val newFirstPoint = gameUseCases.calculateNextFirstPointSnake(
                                currentFirstPoint,
                                currentSnakeOrientation
                            )

                            // Check if snake has eat food
                            snakeHasEat = currentFirstPoint == foodState?.position

                            // Check if snake eat itself or wall
                            if (snakePositions.contains(newFirstPoint) || newFirstPoint.x < 0 || newFirstPoint.y < 0 || newFirstPoint.y >= screenSize.height || newFirstPoint.x >= screenSize.width) {
                                gameState = GameState.FINISHED
                            }

                            // Add the new first point
                            newSnakePositions.add(newFirstPoint)
                        }

                        // Add others points, add the first point only if snake has eat
                        snakePositions.forEachIndexed { index, _ ->
                            if (snakeHasEat) {
                                newSnakePositions.add(snakePositions[index].copy())
                            } else if (index != 0) {
                                newSnakePositions.add(snakePositions[index - 1].copy())
                            }
                        }

                        // Update score & create new food if snake has eat
                        if (snakeHasEat) {
                            increaseCurrentScore()
                            updateFoodPosition()
                        }

                        // Update the snake
                        snakeState = snakeState.copy(positionsList = newSnakePositions)

                        // TODO convert into timer ?
                        withContext(Dispatchers.IO) {
                            Thread.sleep(100)
                        }
                    }
                }
            }
        }
    }

    private fun restartGame() {
        // Reinit var and launch game
        gameState = GameState.NOT_STARTED
        currentScore = 0
        snakeState = SnakeState()
        foodState = null
        currentSnakeOrientation = snakeState.orientation

        screenSize?.let {
            launchGame(it)
        }
    }

    private fun increaseCurrentScore() {
        currentScore++
    }

    private fun updateOrientation(snakeOrientation: SnakeOrientation) {
        // Update orientation only if game is started and snake are allowed to move into this orientation
        if (this.gameState == GameState.STARTED && this.gameUseCases.isAllowToUpdateOrientation(
                currentSnakeOrientation,
                snakeOrientation
            )
        ) {
            this.snakeState = this.snakeState.copy(orientation = snakeOrientation)
        }
    }

    private fun updateFoodPosition() {
        screenSize?.let {
            foodState = gameUseCases.generateFoodState(it, snakeState.positionsList)
        }
    }
}