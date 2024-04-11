package com.jetbrains.kmpapp.viewmodel

import com.jetbrains.kmpapp.MR
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.ImageResource
import kotlinx.coroutines.delay

class RollDiceViewModel : ViewModel() {

    fun isOdd(number: Int): Boolean {
        return number % 2 != 0
    }

    fun getPlayerScore(diceResult: Int, i: Int): Int {
        return diceResult + i
    }


    suspend fun rollDiceAfterDelay(onDiceRolled: (Int) -> Unit) {
        delay(3000)
        val newDiceResult = (1..6).random()
        onDiceRolled(newDiceResult)
    }

    fun getDotPositions(number: Int): List<Pair<Float, Float>> {
        return when (number) {
            1 -> listOf(Pair(0.5f, 0.5f))
            2 -> listOf(Pair(0.2f, 0.2f), Pair(0.8f, 0.8f))
            3 -> listOf(Pair(0.2f, 0.2f), Pair(0.5f, 0.5f), Pair(0.8f, 0.8f))
            4 -> listOf(Pair(0.2f, 0.2f), Pair(0.8f, 0.2f), Pair(0.2f, 0.8f), Pair(0.8f, 0.8f))
            5 -> listOf(
                Pair(0.2f, 0.2f),
                Pair(0.8f, 0.2f),
                Pair(0.2f, 0.8f),
                Pair(0.8f, 0.8f),
                Pair(0.5f, 0.5f)
            )

            6 -> listOf(
                Pair(0.2f, 0.2f),
                Pair(0.8f, 0.2f),
                Pair(0.2f, 0.8f),
                Pair(0.8f, 0.8f),
                Pair(0.2f, 0.5f),
                Pair(0.8f, 0.5f)
            )

            else -> emptyList()
        }
    }

    fun imageUrls(diceResult: Int): ImageResource {

        return when (diceResult) {
            1 -> MR.images.img
            2 -> MR.images.img_6
            3 -> MR.images.img_2
            4 -> MR.images.img_3
            5 -> MR.images.img_4
            6 -> MR.images.img_5
            else -> MR.images.img // Default URL or handle other cases

        }

    }
}