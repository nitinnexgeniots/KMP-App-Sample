package com.jetbrains.kmpapp.interfaces

import androidx.compose.runtime.Composable
import com.jetbrains.kmpapp.data.GoogleAuthCredentials
//
//internal class GoogleAuthProviderImpl(
//    private val credentials: GoogleAuthCredentials,
//    private val credentialManager: CredentialManager,
//) : GoogleAuthProvider {
//
//    @Composable
//    override fun getUiProvider(): GoogleAuthUiProvider {
//        val activityContext = LocalContext.current
//        return GoogleAuthUiProviderImpl(
//            activityContext = activityContext,
//            credentialManager = credentialManager,
//            credentials = credentials
//        )
//    }
//
//    override suspend fun signOut() {
//        credentialManager.clearCredentialState(ClearCredentialStateRequest())
//    }
//}