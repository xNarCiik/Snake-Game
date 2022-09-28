package com.dms.snake.features.game.domain.use_case

data class GameUseCases(
    val isAllowToUpdateOrientation : IsAllowToUpdateOrientationUseCase,
    val generateFoodState: GenerateFoodStateUseCase
)