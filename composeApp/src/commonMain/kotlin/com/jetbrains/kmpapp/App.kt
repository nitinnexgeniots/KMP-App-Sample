package com.jetbrains.kmpapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.jetbrains.kmpapp.screens.detail.DetailScreen
import com.jetbrains.kmpapp.screens.diceroll.DiceRollScreen
import com.jetbrains.kmpapp.screens.feedBack.FeedBackScreen
import com.jetbrains.kmpapp.screens.list.ListScreen
import com.jetbrains.kmpapp.screens.logIn.LoginScreen
import com.jetbrains.kmpapp.screens.videoplayer.VideoPlayerScreen

@Composable
fun App() {
    MaterialTheme {
//        Navigator(ListScreen)                 // Fetch Data From Api and Display List using Ktor
          Navigator(LoginScreen)                // LogIn Screen UI
//        Navigator(DiceRollScreen)             // Dice Roll UI
//        Navigator(VideoPlayerScreen)          // Video Play Sample
//        Navigator(DetailScreen(436532))       // Fetch Data From Api and Display its Details using Ktor
//          Navigator(FeedBackScreen)             // UI of feedback form using radio Button and animation
    }
}
