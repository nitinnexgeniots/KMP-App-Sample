package com.jetbrains.kmpapp.viewmodel


import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.parcelize.Parcelable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch
private const val SAVED_UI_STATE_KEY = "savedUiStateKey"

