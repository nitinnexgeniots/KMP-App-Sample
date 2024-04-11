package com.jetbrains.kmpapp.data

data class GoogleUser(
    val idToken: String,
    val displayName: String = "",
    val profilePicUrl: String? = null,
)