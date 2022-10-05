package com.dms.snake.features.game.domain.use_case.game

data class GameUseCases(
    val isAllowToUpdateOrientation : IsAllowToUpdateOrientationUseCase,
    val generateFoodState: GenerateFoodStateUseCase,
    val calculateNextFirstPointSnake: CalculateNextFirstPointSnakeUseCase
)