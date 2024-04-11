package com.jetbrains.kmpapp.interfaces

import androidx.compose.runtime.Composable

interface GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider

    suspend fun signOut()
}