package com.absar.jugproblembfs.model

data class PuzzlePreset(
    val id: Int,
    val capacities: List<Int>,
    val target: Int,
    val difficulty: String
)