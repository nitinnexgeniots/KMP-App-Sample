package com.jetbrains.kmpapp.screens.feedBack

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.screens.VideoPlayerApp
import com.jetbrains.kmpapp.screens.forms.FeedbackForm
import com.jetbrains.kmpapp.screens.forms.RatingForm


data object  FeedBackScreen : Screen {
    @Composable
    override fun Content() {
        RatingForm({})
    }
}