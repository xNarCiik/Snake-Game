package com.dms.snake.features.game.presentation.scores

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dms.snake.features.game.domain.model.Score
import com.dms.snake.features.game.domain.use_case.scores.ScoresUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScoresViewModel @Inject constructor(
    private val scoresUseCases: ScoresUseCases
) : ViewModel() {

    var scores = mutableStateListOf<Score>()
        private set

    private var getScoresJob: Job? = null

    init {
        getScores()
    }

    private fun getScores() {
        getScoresJob?.cancel()
        getScoresJob = scoresUseCases.getScoresUseCase()
            .onEach { scores ->
                this.scores.clear()
                this.scores.addAll(scores)
            }
            .launchIn(viewModelScope)
    }

}