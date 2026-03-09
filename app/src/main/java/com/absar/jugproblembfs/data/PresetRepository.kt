package com.absar.jugproblembfs.data

import com.absar.jugproblembfs.model.PuzzlePreset

object PresetRepository {

    private val allPresets = listOf(

        // 2 JUG LEVELS
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
        ),

        // 3 JUG LEVELS

        PuzzlePreset(
            id = 11,
            capacities = listOf(8,5,3),
            target = 4,
            difficulty = "Medium"
        ),

        PuzzlePreset(
            id = 12,
            capacities = listOf(12,8,5),
            target = 6,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 13,
            capacities = listOf(10,7,3),
            target = 5,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 14,
            capacities = listOf(14,9,5),
            target = 7,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 15,
            capacities = listOf(15,11,6),
            target = 8,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 16,
            capacities = listOf(16,10,7),
            target = 9,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 17,
            capacities = listOf(18,11,7),
            target = 10,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 18,
            capacities = listOf(20,13,7),
            target = 11,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 19,
            capacities = listOf(21,14,9),
            target = 12,
            difficulty = "Hard"
        ),

        PuzzlePreset(
            id = 20,
            capacities = listOf(24,15,9),
            target = 13,
            difficulty = "Hard"
        )
    )

    fun getPresets(jugCount: Int): List<PuzzlePreset> {
        return allPresets.filter { it.capacities.size == jugCount }
    }
}