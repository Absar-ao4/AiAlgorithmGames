package com.absar.jugproblembfs.data

import com.absar.jugproblembfs.model.PuzzlePreset

object PresetRepository {

    private val allPresets = listOf(

        // ----------------------
        // 2 JUG LEVELS
        // ----------------------

        PuzzlePreset(
            id = 1,
            capacities = listOf(5, 3),
            target = 2,
            difficulty = "Easy"
        ),

        PuzzlePreset(
            id = 2,
            capacities = listOf(5, 4),
            target = 2,
            difficulty = "Easy"
        ),

        PuzzlePreset(
            id = 3,
            capacities = listOf(9, 3),
            target = 6,
            difficulty = "Medium"
        ),

        PuzzlePreset(
            id = 4,
            capacities = listOf(7, 4),
            target = 5,
            difficulty = "Medium"
        ),

        PuzzlePreset(
            id = 5,
            capacities = listOf(6, 4),
            target = 2,
            difficulty = "Easy"
        ),

        PuzzlePreset(
            id = 6,
            capacities = listOf(8, 5),
            target = 3,
            difficulty = "Medium"
        ),

        PuzzlePreset(
            id = 7,
            capacities = listOf(10, 7),
            target = 3,
            difficulty = "Medium"
        ),

        PuzzlePreset(
            id = 8,
            capacities = listOf(11, 6),
            target = 5,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 9,
            capacities = listOf(12, 8),
            target = 4,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 10,
            capacities = listOf(13, 9),
            target = 7,
            difficulty = "Hard"
        )
    )

    // ✅ NEW VERSION
    fun getPresets(jugCount: Int): List<PuzzlePreset> {
        return allPresets.filter { it.capacities.size == jugCount }
    }
}