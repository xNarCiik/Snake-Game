package com.example.sampleapp.feature_game.presentation.game

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.sampleapp.feature_game.domain.util.Difficulty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(

) : ViewModel() {

    private val _difficulty = mutableStateOf(Difficulty.NORMAL)
    val difficulty: State<Difficulty> = _difficulty

    fun changeDifficulty(difficulty: Difficulty){
        _difficulty.value = difficulty
    }
}