package com.absar.jugproblembfs.uiux.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absar.jugproblembfs.viewmodel.WaterJugViewModel
import com.absar.jugproblembfs.uiux.screens.components.DropdownSelector
import com.absar.jugproblembfs.uiux.screens.components.JugCard

@Composable
fun GameScreen(
    viewModel: WaterJugViewModel,
    onBackClick: () -> Unit   // 🔥 added
) {

    val state = viewModel.gameState ?: return

    var fromIndex by remember(state.capacities.size) { mutableStateOf(0) }
    var toIndex by remember(state.capacities.size) {
        mutableStateOf(if (state.capacities.size > 1) 1 else 0)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            // 🔥 NEW TOP BAR
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Level ${state.level}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "Moves: ${state.moves}",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(12.dp))

            // ORIGINAL HEADER (unchanged)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "🎯 Target: ${state.target}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Jugs Row (unchanged)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                state.capacities.indices.forEach { index ->
                    JugCard(
                        capacity = state.capacities[index],
                        current = state.currentLevels[index],
                        onFill = { viewModel.fill(index) },
                        onEmpty = { viewModel.empty(index) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Pour Section (UNCHANGED)
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E3A5F)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        "Pour Water",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        DropdownSelector(
                            label = "From",
                            options = state.capacities.indices.toList(),
                            selectedIndex = fromIndex,
                            onSelect = { fromIndex = it },
                            modifier = Modifier.weight(1f)
                        )

                        DropdownSelector(
                            label = "To",
                            options = state.capacities.indices.toList(),
                            selectedIndex = toIndex,
                            onSelect = { toIndex = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { viewModel.pour(fromIndex, toIndex) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        enabled =
                            fromIndex != toIndex &&
                                    state.currentLevels[fromIndex] > 0 &&
                                    state.currentLevels[toIndex] < state.capacities[toIndex],
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "POUR",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // 🔥 NEW LEVEL NAVIGATION ROW
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = { viewModel.previousLevel() },
                    enabled = state.level > 1,
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.NavigateBefore, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Prev")
                }

                Button(
                    onClick = { viewModel.nextLevel() },
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Next")
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }

            if (state.isSolved) {
                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2E7D32)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(
                        modifier = Modifier.padding(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "🎉 Puzzle Solved!",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}