package com.dms.snake.features.game.domain.use_case.game

import com.dms.snake.features.game.domain.model.Point
import com.dms.snake.features.game.domain.util.SnakeOrientation
import com.dms.snake.features.game.presentation.game.SnakeState

class CalculateNextFirstPointSnakeUseCase {

    operator fun invoke(
        currentFirstPoint: Point,
        snakeOrientation: SnakeOrientation
    ): Point {
        return when (snakeOrientation) {
            SnakeOrientation.UP -> currentFirstPoint.copy(y = currentFirstPoint.y - SnakeState.SIZE_SNAKE)
            SnakeOrientation.DOWN -> currentFirstPoint.copy(y = currentFirstPoint.y + SnakeState.SIZE_SNAKE)
            SnakeOrientation.LEFT -> currentFirstPoint.copy(x = currentFirstPoint.x - SnakeState.SIZE_SNAKE)
            SnakeOrientation.RIGHT -> currentFirstPoint.copy(x = currentFirstPoint.x + SnakeState.SIZE_SNAKE)
        }
    }
}