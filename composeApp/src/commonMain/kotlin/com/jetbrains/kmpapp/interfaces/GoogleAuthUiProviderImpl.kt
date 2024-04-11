package com.jetbrains.kmpapp.interfaces

import com.jetbrains.kmpapp.data.GoogleAuthCredentials
import com.jetbrains.kmpapp.data.GoogleUser
//
//internal class GoogleAuthUiProviderImpl(
//    private val activityContext: Context,
//    private val credentialManager: CredentialManager,
//    private val credentials: GoogleAuthCredentials,
//) :
//    GoogleAuthUiProvider {
//    override suspend fun signIn(): GoogleUser? {
//        return try {
//            val credential = credentialManager.getCredential(
//                context = activityContext,
//                request = getCredentialRequest()
//            ).credential
//            getGoogleUserFromCredential(credential)
//        } catch (e: GetCredentialException) {
//            AppLogger.e("GoogleAuthUiProvider error: ${e.message}")
//            null
//        } catch (e: NullPointerException) {
//            null
//        }
//    }
//
//    private fun getGoogleUserFromCredential(credential: Credential): GoogleUser? {
//        return when {
//            credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
//                try {
//                    val googleIdTokenCredential =
//                        GoogleIdTokenCredential.createFrom(credential.data)
//                    GoogleUser(
//                        idToken = googleIdTokenCredential.idToken,
//                        displayName = googleIdTokenCredential.displayName ?: "",
//                        profilePicUrl = googleIdTokenCredential.profilePictureUri?.toString()
//                    )
//                } catch (e: GoogleIdTokenParsingException) {
//                    AppLogger.e("GoogleAuthUiProvider Received an invalid google id token response: ${e.message}")
//                    null
//                }
//            }
//
//            else -> null
//        }
//    }
//
//    private fun getCredentialRequest(): GetCredentialRequest {
//        return GetCredentialRequest.Builder()
//            .addCredentialOption(getGoogleIdOption(serverClientId = credentials.serverId))
//            .build()
//    }
//
//    private fun getGoogleIdOption(serverClientId: String): GetGoogleIdOption {
//        return GetGoogleIdOption.Builder()
//            .setFilterByAuthorizedAccounts(false)
//            .setAutoSelectEnabled(true)
//            .setServerClientId(serverClientId)
//            .build()
//    }
//}