package com.jetbrains.kmpapp.screens.diceroll

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.screens.VideoPlayerApp
import dev.icerock.moko.resources.compose.painterResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.jetbrains.kmpapp.viewmodel.RollDiceViewModel
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

var isGamefinished = false
data object DiceRollScreen : Screen {
    @Composable
    override fun Content() {
         val viewmodel= getViewModel("DiceRollScreen", factory = viewModelFactory { RollDiceViewModel() })
         DiceRollScreenUI(viewmodel)
    }
}



@Composable
fun DiceRollScreenUI(viewModel: RollDiceViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var count by remember { mutableIntStateOf(1) }
    var playerOneScore by remember { mutableStateOf(0) }
    var playerTwoScore by remember { mutableStateOf(0) }
    var isRotating by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        DisplayScoreCard(playerOneScore, playerTwoScore)
        if(isRotating)
        {
            ShowImage(viewModel)
        }
        var diceResult by remember { mutableIntStateOf(0) }
        val instaColors = listOf(Color.White, Color.White, Color.White)
        val dotsColors = listOf(Color.Black, Color.Black, Color.Black)
        val custommodifier: Modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(8.dp)
            .size(80.dp)
        if(!isRotating) {

            DisplayDiceResult(viewModel,diceResult)
//            Canvas(
//                modifier = custommodifier
//            ) {
//                drawRoundRect(
//                    brush = Brush.linearGradient(colors = instaColors),
//                    cornerRadius = CornerRadius(60f, 60f),
//                    style = Stroke(width = 15f, cap = StrokeCap.Round)
//                )
//                val dotPositions = viewModel.getDotPositions(diceResult)
//                dotPositions.forEach { (x, y) ->
//                    drawCircle(
//                        brush = Brush.linearGradient(colors = dotsColors),
//                        radius = 25f,
//                        center = Offset(this.size.width * x, this.size.height * y),
//                    )
//
//                }
//            }
        }

        if (!isGamefinished) {
            Button(
                enabled = !isRotating,
                onClick = {
                    isRotating = true
                    coroutineScope.launch {
                        viewModel.rollDiceAfterDelay { newDiceResult ->
                            diceResult = newDiceResult
                            isRotating = false
                        }
                        if (viewModel.isOdd(count)) {
                            playerOneScore = viewModel.getPlayerScore(diceResult, playerOneScore)
                        } else {
                            playerTwoScore = viewModel.getPlayerScore(diceResult, playerTwoScore)
                        }
                        if (!isGamefinished) {
                            count++
                        }
                    }
                },
            ) {
                val player = if (viewModel.isOdd(count)) "Player 1" else "Player 2"
                Text(text = "$player Roll The Dice")
            }


        }
    }


}







@Composable
fun DisplayScoreCard(playerOneScore: Int, playerTwoScore: Int) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight()
    ) {
        if (playerOneScore >= 100 || playerTwoScore >= 100) {
            isGamefinished = true
            val winnerPlayerName = if (playerOneScore >= 100) "Player 1" else "Player 2"
            Text(text = "Game Over  $winnerPlayerName win the game")
        } else {
            Text(text = "Player one score -> $playerOneScore")

            Text(text = "Player Two score -> $playerTwoScore")
        }
    }
}

@Composable
fun ShowImage(viewModel: RollDiceViewModel) {

    var animate by remember { mutableStateOf(false) }
LaunchedEffect(animate)
{
    delay(100)
    animate=!animate
}

    Image(
        painter=painterResource(viewModel.imageUrls((1..6).random())),
        contentDescription="dice",
    )

}


@Composable
fun DisplayDiceResult(viewModel: RollDiceViewModel, result: Int) {
    Image(
        painter=painterResource(viewModel.imageUrls(result)),
        contentDescription="dice",
    )

}










