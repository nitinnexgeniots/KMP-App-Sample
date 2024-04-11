package com.jetbrains.kmpapp.screens.videoplayer

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.screens.VideoPlayerApp
import com.jetbrains.kmpapp.screens.diceroll.DiceRollScreenUI
import com.jetbrains.kmpapp.viewmodel.RollDiceViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

data object  VideoPlayerScreen : Screen {
    @Composable
    override fun Content() {
        VideoPlayerApp("Android")
    }
}