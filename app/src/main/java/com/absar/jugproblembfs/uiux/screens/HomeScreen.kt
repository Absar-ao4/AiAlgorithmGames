package com.absar.jugproblembfs.uiux.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.absar.jugproblembfs.viewmodel.WaterJugViewModel
import com.absar.jugproblembfs.uiux.screens.components.DropdownSelector

@Composable
fun HomeScreen(
    viewModel: WaterJugViewModel,
    onStartGame: () -> Unit
) {

    var selectedJugCountIndex by remember { mutableStateOf(0) }
    val jugOptions = listOf(2, 3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A1A2F)),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(0.85f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF112B45)
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "The Jugs Puzzle",
                    fontSize = 28.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Jug Count Selector
                DropdownSelector(
                    label = "Select Jug Mode",
                    options = jugOptions,
                    selectedIndex = selectedJugCountIndex,
                    onSelect = { selectedJugCountIndex = it },
                    displayText = { "$it Jugs" }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val selectedCount = jugOptions[selectedJugCountIndex]
                        viewModel.selectJugCount(selectedCount)
                        viewModel.startGame()
                        onStartGame()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Start Game")
                }
            }
        }
    }
}