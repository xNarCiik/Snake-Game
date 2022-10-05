package com.dms.snake.features.game.domain.use_case.game

import androidx.compose.ui.unit.IntSize
import com.dms.snake.features.game.domain.model.Point
import com.dms.snake.features.game.presentation.game.FoodState
import com.dms.snake.features.game.presentation.game.SnakeState

class GenerateFoodStateUseCase {

    operator fun invoke(
        screenSize: IntSize,
        snakePosition: List<Point>
    ): FoodState {
        var point: Point
        // Generate new point until its correct (we dont want a food generated in the snake)
        do {
            val x =
                SnakeState.SIZE_SNAKE * (1 until (screenSize.width / SnakeState.SIZE_SNAKE).toInt()).random()
            val y =
                SnakeState.SIZE_SNAKE * (1 until (screenSize.height / SnakeState.SIZE_SNAKE).toInt()).random()
            point = Point(x, y)
        } while (snakePosition.contains(point))

        return FoodState(point)
    }

}