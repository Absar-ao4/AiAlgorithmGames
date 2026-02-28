package com.absar.jugproblembfs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.absar.jugproblembfs.uiux.screens.GameScreen
import com.absar.jugproblembfs.uiux.screens.HomeScreen
import com.absar.jugproblembfs.viewmodel.WaterJugViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: WaterJugViewModel = viewModel()
            var currentScreen by remember { mutableStateOf("home") }

            when (currentScreen) {

                "home" -> HomeScreen(
                    viewModel = viewModel,
                    onStartGame = {
                        currentScreen = "game"
                    }
                )

                "game" -> GameScreen(
                    viewModel = viewModel,
                    onBackClick = {
                        viewModel.resetGame()
                        currentScreen = "home"
                    }
                )
            }
        }
    }
}