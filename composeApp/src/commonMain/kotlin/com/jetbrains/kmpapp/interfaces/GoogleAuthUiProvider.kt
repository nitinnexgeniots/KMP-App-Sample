package com.jetbrains.kmpapp.interfaces

import com.jetbrains.kmpapp.data.GoogleUser

interface GoogleAuthUiProvider {

    /**
     * Opens Sign In with Google UI,
     * @return returns GoogleUser
     */
    suspend fun signIn(): GoogleUser?
}