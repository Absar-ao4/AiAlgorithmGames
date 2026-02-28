package com.absar.jugproblembfs.uiux.screens.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JugCard(
    capacity: Int,
    current: Int,
    onFill: () -> Unit,
    onEmpty: () -> Unit
) {

    val fillRatio = if (capacity == 0) 0f else current.toFloat() / capacity.toFloat()

    val animatedFill = animateFloatAsState(targetValue = fillRatio)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Jug Container
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(160.dp)
                .background(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.BottomCenter
        ) {

            // Water Fill
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(animatedFill.value)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFF4FC3F7),
                                Color(0xFF0288D1)
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            )

            // Level Text inside jug
            Text(
                text = "$current / $capacity",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = onFill,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF29B6F6),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Fill", fontSize = 12.sp)
            }

            Button(
                onClick = onEmpty,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE53935),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
                modifier = Modifier.height(36.dp)
            ) {
                Text("Empty", fontSize = 12.sp)
            }
        }
    }
}