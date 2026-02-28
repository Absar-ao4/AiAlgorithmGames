package com.absar.jugproblembfs.model

data class GameState(
    val capacities: List<Int>,
    val currentLevels: List<Int>,
    val target: Int,
    val moves: Int = 0,
    val isSolved: Boolean = false,
    val level: Int
)