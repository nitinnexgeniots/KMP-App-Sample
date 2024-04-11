package com.jetbrains.kmpapp.screens.forms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.componets.CustomRadioButtonIndicator_WithIconToggleButton
import com.jetbrains.kmpapp.theme.colorScheme
import com.jetbrains.kmpapp.theme.feedbackHeadBackground
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun FeedbackForm() {
    var showDialog by remember {
        mutableStateOf(false)
    }

    CustomDialog(
        showDialog = showDialog,
        onDismissRequest = { showDialog = false }
    ) {

//        ResetWarning(
//            onDismissRequest = { showDialog = false }
//        )
        RatingForm(
            onDismissRequest = { showDialog = false }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Button(onClick = {
            showDialog = true
        }) {
            Text(text = "Show Dialog")
        }

    }
}




@Composable
fun CustomDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {

    var showAnimatedDialog by remember { mutableStateOf(false) }

    LaunchedEffect(showDialog) {
        if (showDialog) showAnimatedDialog = true
    }
    if (showAnimatedDialog) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center,

            ) {
                var animateIn by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) { animateIn = true }
                AnimatedVisibility(
                    visible = animateIn && showDialog,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Box(
                        modifier = Modifier
                            .pointerInput(Unit) { detectTapGestures { onDismissRequest() } }
                            .background(Color.Black.copy(alpha = .56f))
                            .fillMaxSize().padding(12.dp)
                    )
                }
                AnimatedVisibility(
                    visible = animateIn && showDialog,
                    enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                        initialScale = .8f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        )
                    ),
                    exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)
                ) {
                    Box(
                        Modifier
                            .pointerInput(Unit) { detectTapGestures { } }
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
                            .fillMaxWidth()
                            //.clip(RoundedCornerShape(16.dp))
                            .background(
                                colorScheme
                            )
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        content()
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            showAnimatedDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResetWarning(
    onDismissRequest: () -> Unit,
) {
    Column(Modifier.background(colorScheme)
    ) {

        var graphicVisible by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) { graphicVisible = true }

        AnimatedVisibility(
            visible = graphicVisible,
            enter = expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.CenterVertically,
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xE9EE8888),
                                Color(0xFFE4BD79),
                            )
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(modifier = Modifier.height(8.dp))
            Text(
                text = "Reset Data",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Box(modifier = Modifier.height(8.dp))
            Text(text = "All your data will be permanently lost and phone will be listed for auction.")
        }
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "CANCEL", fontWeight = FontWeight.Bold)
            }

            Box(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(
                      colorScheme.copy(alpha = .08f),
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onDismissRequest() }
                    .weight(1f)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "RESET", fontWeight = FontWeight.Bold, color = Color(0xFFFF332C))
            }
        }
    }
}


@Composable
fun RatingForm(onDismissRequest: () -> Unit) {
    Column(Modifier.background(colorScheme)
    ) {
        var graphicVisible by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) { graphicVisible = true }
        AnimatedVisibility(
            visible = graphicVisible,
            enter = expandVertically(
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                expandFrom = Alignment.CenterVertically,
            )
        ) {
            Column(modifier = Modifier.background(feedbackHeadBackground)) {
                Box(modifier = Modifier.fillMaxWidth().padding(4.dp), contentAlignment = Alignment.TopEnd) {
                    Icon(
                        modifier = Modifier.size(32.dp).clickable {
                            onDismissRequest()
                        },
                        painter = painterResource(
                                MR.images.icon_close
                        ),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp).padding(horizontal = 12.dp, vertical = 8.dp),
                ) {
                    Column {
                        Text(
                            text = "Send us your feedback!",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Do you have a suggestion or found some bug?\nLet us know in the field below.",
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                    }
                }
            }
        }



        Column(modifier = Modifier.background(Color.White).fillMaxWidth().padding(2.dp)) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "How was your experience?",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            SmileyRatingBarSample()
            DescriptionInput(onDescriptionChanged = { description ->
                // Handle the address change here
                println("Description  changed: $description")
            })

            CustomRadioButtonIndicator_WithIconToggleButton()
            ButtonWithRectangleShape()


        }

    }

}

@Composable
fun ButtonWithRectangleShape() {
    Button(
        colors = ButtonDefaults.buttonColors(backgroundColor = feedbackHeadBackground),
        modifier = Modifier.fillMaxWidth().padding(8.dp).height(50.dp), onClick = {}, shape = RectangleShape,) {
        Text(text = "Send Feedback", color = Color.White)
    }
}



@Composable
fun DescriptionInput(onDescriptionChanged: (String) -> Unit) {
    // State to hold the value of the address text field
    val addressState = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.padding(12.dp)) {
        //Text(text = "Description")
        TextField(
            value = addressState.value,
            onValueChange = {
                // Update the address state when the text field value changes
                addressState.value = it
                // Pass the updated address string to the callback function
                onDescriptionChanged(it.text)
            },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .height(130.dp), // Set the fixed height here
            label = { Text("Describe your experience here") },
        )
    }
}

