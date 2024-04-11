package com.jetbrains.kmpapp.screens.forms

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.data.SmileyData
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun Smiley(
    smileyData: SmileyData,
    isSelected: Boolean,
    index: Int,
    count: Int,
    modifier: Modifier = Modifier,
    animationDurationInMillis: Int = 300,
    onClick: () -> Unit,
) {
    val padding: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            0.dp
        } else {
            4.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val size: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            52.dp
        } else {
            44.dp
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val saturation: Float by animateFloatAsState(
        targetValue = if (isSelected) {
            1F
        } else {
            0F
        },
        animationSpec = tween(
            durationMillis = animationDurationInMillis,
            easing = LinearEasing,
        ),
    )
    val matrix = ColorMatrix().apply {
        setToSaturation(saturation)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        KamelImage(
            resource = asyncPainterResource(data = smileyData.url),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    top = padding,
                )
                .size(size)
                .clip(CircleShape)
                .clickable {
                    onClick()
                },
            colorFilter = ColorFilter.colorMatrix(matrix)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = smileyData.label,
            color = if (isSelected) {
                if (index < (count / 2)) {
                    Color.Red
                } else if (index > (count / 2)) {
                    Color(0xFF275A27)
                } else {
                    Color.Black
                }
            } else {
                Color.DarkGray
            },
            fontWeight = if (isSelected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
        )
    }
}





@Composable
fun SmileyRatingBar(
    data: List<SmileyData>,
    rating: Int,
    setRating: (rating: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
                    start = 44.dp,
                    end = 44.dp,
                ),
            thickness = 4.dp,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = modifier
                .height(80.dp)
                .fillMaxWidth(),
        ) {
            data.mapIndexed { index, smileyData ->
                Smiley(
                    smileyData = smileyData,
                    isSelected = index == rating,
                    index = index,
                    count = data.size,
                    modifier = Modifier.weight(1F),
                    onClick = {
                        setRating(index)
                    },
                )
            }
        }
    }
}

@Composable
fun SmileyRatingBarSample() {
    val data: List<SmileyData> = listOf(
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742905.png", "Terrible"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742761.png", "Bad"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742774.png", "Okay"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742940.png", "Good"),
        SmileyData("https://cdn-icons-png.flaticon.com/512/742/742869.png", "Awesome"),
    )

    val (rating, setRating) = remember {
        mutableStateOf(data.size / 2)
    }
    SmileyRatingBar(
        data = data,
        rating = rating,
        setRating = setRating,
    )
}
