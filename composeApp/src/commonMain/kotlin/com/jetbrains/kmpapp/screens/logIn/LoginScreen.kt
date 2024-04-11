package com.jetbrains.kmpapp.screens.logIn

import androidx.compose.material.Surface
import com.jetbrains.kmpapp.componets.BottomComponent
import com.jetbrains.kmpapp.componets.BottomLoginTextComponent
import com.jetbrains.kmpapp.componets.ForgotPasswordTextComponent
import com.jetbrains.kmpapp.componets.HeadingTextComponent
import com.jetbrains.kmpapp.componets.ImageComponent
import com.jetbrains.kmpapp.componets.MyTextField
import com.jetbrains.kmpapp.componets.PasswordInputComponent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.screens.forms.FeedbackForm


data object LoginScreen : Screen {
    @Composable
    override fun Content() {
        LoginScreenUI()
    }
}
@Composable
fun LoginScreenUI() {
    Surface(
        modifier = Modifier.fillMaxSize().padding(20.dp).verticalScroll(rememberScrollState()),
        color = Color.White
    ) {
        Column() {
            ImageComponent(image = MR.images.sweet_franky)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Login")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                MyTextField(labelVal = "email ID", MR.images.at_symbol)
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInputComponent(labelVal = "Password",MR.images.lock,MR.images.pheyeclosedfill__1_,MR.images.eye_closed)
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ForgotPasswordTextComponent()
                }
                Box(
                    modifier = Modifier.fillMaxSize().padding(top = 20.dp),
                    //contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        BottomComponent(MR.images.google)
                        Spacer(modifier = Modifier.height(12.dp))
                        BottomLoginTextComponent(
                            initialText = "Haven't we seen you around here before? ",
                            action = "Join our coven!"
                        )

                    }
                }
            }
        }
    }
}