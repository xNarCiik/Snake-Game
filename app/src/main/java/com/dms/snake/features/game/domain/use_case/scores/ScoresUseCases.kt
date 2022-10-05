package com.dms.snake.features.game.domain.use_case.scores

data class ScoresUseCases(
    val getScores: GetScoresUseCase,
    val insertScore: InsertScoreUseCase
)