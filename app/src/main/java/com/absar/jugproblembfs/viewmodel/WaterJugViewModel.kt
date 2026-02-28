package com.absar.jugproblembfs.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.absar.jugproblembfs.model.GameState
import com.absar.jugproblembfs.model.PuzzlePreset
import com.absar.jugproblembfs.data.PresetRepository

class WaterJugViewModel : ViewModel() {

    var selectedJugCount by mutableStateOf<Int?>(null)
        private set

    var presets by mutableStateOf<List<PuzzlePreset>>(emptyList())
        private set

    var currentLevelIndex by mutableStateOf(0)
        private set

    var gameState by mutableStateOf<GameState?>(null)
        private set

    // ----------------------
    // SETUP
    // ----------------------

    fun selectJugCount(count: Int) {
        selectedJugCount = count
        presets = PresetRepository.getPresets(count)
        currentLevelIndex = 0
    }

    fun startGame() {
        if (presets.isNotEmpty()) {
            loadLevel(currentLevelIndex)
        }
    }

    private fun loadLevel(index: Int) {
        val preset = presets[index]

        gameState = GameState(
            level = index + 1,
            capacities = preset.capacities,
            currentLevels = List(preset.capacities.size) { 0 },
            target = preset.target,
            moves = 0,
            isSolved = false
        )
    }

    fun nextLevel() {
        if (currentLevelIndex < presets.lastIndex) {
            currentLevelIndex++
            loadLevel(currentLevelIndex)
        }
    }

    fun previousLevel() {
        if (currentLevelIndex > 0) {
            currentLevelIndex--
            loadLevel(currentLevelIndex)
        }
    }

    fun resetGame() {
        gameState = null
        currentLevelIndex = 0
    }

    // ----------------------
    // GAME ACTIONS
    // ----------------------

    fun fill(index: Int) {
        gameState?.let { state ->

            if (state.currentLevels[index] == state.capacities[index]) return

            val newLevels = state.currentLevels.toMutableList()
            newLevels[index] = state.capacities[index]

            updateState(newLevels)
        }
    }

    fun empty(index: Int) {
        gameState?.let { state ->

            if (state.currentLevels[index] == 0) return

            val newLevels = state.currentLevels.toMutableList()
            newLevels[index] = 0

            updateState(newLevels)
        }
    }

    fun pour(from: Int, to: Int) {
        gameState?.let { state ->

            if (from == to) return

            val newLevels = state.currentLevels.toMutableList()

            val amountToPour = minOf(
                newLevels[from],
                state.capacities[to] - newLevels[to]
            )

            if (amountToPour <= 0) return

            newLevels[from] -= amountToPour
            newLevels[to] += amountToPour

            updateState(newLevels)
        }
    }

    private fun updateState(newLevels: List<Int>) {
        gameState?.let { state ->

            val solved = newLevels.any { it == state.target }

            gameState = state.copy(
                currentLevels = newLevels,
                moves = state.moves + 1,
                isSolved = solved
            )
        }
    }
}