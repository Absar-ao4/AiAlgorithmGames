package com.absar.jugproblembfs.uiux.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.ArrowForward
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
    onBackClick: () -> Unit
) {

    val state = viewModel.gameState ?: return

    var fromIndex by remember { mutableStateOf(0) }
    var toIndex by remember { mutableStateOf(1) }

    var pourDirection by remember { mutableStateOf(true) }

    val labels = listOf("Jug 1","Jug 2","Jug 3")

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
                .padding(24.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack,"", tint = Color.White)
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "Level ${state.level}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "Moves: ${state.moves}",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "🎯 Target: ${state.target}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { viewModel.showHint() },
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("💡 Hint")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            viewModel.hintMessage?.let {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF37474F)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = it,
                        modifier = Modifier.padding(12.dp),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                state.capacities.indices.forEach { index ->

                    JugCard(
                        label = labels[index],
                        capacity = state.capacities[index],
                        current = state.currentLevels[index],
                        onFill = { viewModel.fill(index) },
                        onEmpty = { viewModel.empty(index) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if(state.capacities.size == 2){

                val from = if(pourDirection) 0 else 1
                val to = if(pourDirection) 1 else 0

                Card(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text("Pour Direction")

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = { pourDirection = !pourDirection }
                        ) {
                            Text(
                                if(pourDirection)
                                    "Jug 1 → Jug 2"
                                else
                                    "Jug 2 → Jug 1"
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = { viewModel.pour(from,to) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("POUR")
                        }
                    }
                }
            }

            if(state.capacities.size == 3){

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp)
                ){

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ){

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ){

                            DropdownSelector(
                                label = "From",
                                options = state.capacities.indices.toList(),
                                selectedIndex = fromIndex,
                                onSelect = { fromIndex = it },
                                modifier = Modifier.weight(1f),
                                displayText = { "Jug ${it + 1}" }
                            )

                            DropdownSelector(
                                label = "To",
                                options = state.capacities.indices.toList(),
                                selectedIndex = toIndex,
                                onSelect = { toIndex = it },
                                modifier = Modifier.weight(1f),
                                displayText = { "Jug ${it + 1}" }
                            )
                        }

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = { viewModel.pour(fromIndex,toIndex) },
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text("POUR")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = { viewModel.previousLevel() },
                    enabled = state.level > 1
                ) {
                    Icon(Icons.Default.NavigateBefore,null)
                    Text("Prev")
                }

                Button(
                    onClick = { viewModel.nextLevel() }
                ) {
                    Text("Next")
                    Icon(Icons.Default.ArrowForward,null)
                }
            }

            if(state.isSolved){

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2E7D32)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier.padding(18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "🎉 Puzzle Solved!",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}