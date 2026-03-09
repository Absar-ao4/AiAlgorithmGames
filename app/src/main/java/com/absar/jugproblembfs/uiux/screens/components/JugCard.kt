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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JugCard(
    label: String,
    capacity: Int,
    current: Int,
    onFill: () -> Unit,
    onEmpty: () -> Unit
) {

    val fillRatio =
        if (capacity == 0) 0f else current.toFloat() / capacity.toFloat()

    val animatedFill = animateFloatAsState(fillRatio)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .width(70.dp)
                .height(150.dp)
                .background(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.BottomCenter
        ) {

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

            Text(
                text = "$current / $capacity",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onFill,
            modifier = Modifier
                .width(70.dp)
                .height(34.dp)
        ) {
            Text("Fill", fontSize = 11.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = onEmpty,
            modifier = Modifier
                .width(70.dp)
                .height(34.dp)
        ) {
            Text("Empty", fontSize = 11.sp)
        }
    }
}