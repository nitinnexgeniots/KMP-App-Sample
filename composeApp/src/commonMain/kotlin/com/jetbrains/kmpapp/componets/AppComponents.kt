package com.jetbrains.kmpapp.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.MR
import com.jetbrains.kmpapp.screens.diceroll.DiceRollScreen
import com.jetbrains.kmpapp.screens.feedBack.FeedBackScreen
import com.jetbrains.kmpapp.screens.forms.FeedbackForm
import com.jetbrains.kmpapp.screens.list.ListScreen
import com.jetbrains.kmpapp.screens.videoplayer.VideoPlayerScreen
import com.jetbrains.kmpapp.theme.BgSocial
import com.jetbrains.kmpapp.theme.BorderColor
import com.jetbrains.kmpapp.theme.BrandColor
import com.jetbrains.kmpapp.theme.Primary
import com.jetbrains.kmpapp.theme.Tertirary
import com.jetbrains.kmpapp.theme.feedbackHeadBackground
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ImageComponent(image: ImageResource) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
    )
}

@Composable
fun HeadingTextComponent(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 39.sp,
        color = Primary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ForgotPasswordHeadingTextComponent(action: String) {
    Column {
        Text(
            text = action,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 39.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(MR.strings.password),
            modifier = Modifier.fillMaxWidth().offset(y = (-18).dp),
            fontSize = 39.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun MyTextField(labelVal: String, icon: ImageResource) {
    var textVal by remember {
        mutableStateOf("")
    }
    val typeOfKeyboard: KeyboardType = when (labelVal) {
        stringResource(MR.strings.emailID)  -> KeyboardType.Email
        stringResource(MR.strings.phone) -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    OutlinedTextField(
        value = textVal,
        onValueChange = {
            textVal = it
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            textColor = Color.Black,
            leadingIconColor = BrandColor,
            disabledLeadingIconColor = Tertirary
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(MR.strings.atsymbol),
                modifier = Modifier.size(16.dp)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = typeOfKeyboard,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}


@Composable
fun PasswordInputComponent(labelVal: String,lock:ImageResource,pheyeclosedfill:ImageResource,eye_closed:ImageResource) {
    var password by remember {
        mutableStateOf("")
    }
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            textColor = Color.Black
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = Tertirary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(lock),
                contentDescription =stringResource( MR.strings.atsymbol),
                tint = Tertirary,
                modifier = Modifier.size(16.dp)
            )
        },
        trailingIcon = {
            val description = if (isShowPassword) MR.strings.showPassword else MR.strings.hidePassword
            val iconImage =
                if (isShowPassword) pheyeclosedfill else eye_closed
            IconButton(onClick = {
                isShowPassword = !isShowPassword
            }) {
                Icon(
                    painter = painterResource(iconImage),
                    contentDescription = stringResource(description),
                    tint = Tertirary,
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
    )
}

@Composable
fun ForgotPasswordTextComponent() {
    val navigator = LocalNavigator.currentOrThrow
    Text(
        text = stringResource(MR.strings.forgotPassword),
        color = BrandColor,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            navigator.push(FeedBackScreen)
        }
    )
}

@Composable
fun MyButton(labelVal: String) {
    val navigator = LocalNavigator.currentOrThrow
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BrandColor
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
       val submit= stringResource(MR.strings.submit)
        val continuee= stringResource(MR.strings.continuee)
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                if (labelVal == submit) {
                  //  navController.navigate("ResetPassword")
                }else if(labelVal==continuee)
                {
                    navigator.push(DiceRollScreen)
                }
            }
        )
    }
}

@Composable
fun BottomComponent(google:ImageResource) {
    Column {
        MyButton(labelVal =stringResource( MR.strings.continuee),)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = Tertirary
            )
            Text(
                text = stringResource(MR.strings.or),
                modifier = Modifier.padding(10.dp),
                color = Tertirary,
                fontSize = 20.sp
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = Tertirary
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        val navigator = LocalNavigator.currentOrThrow
        Button(
            onClick = {
                      navigator.push(VideoPlayerScreen)
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BgSocial
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(google),
                    contentDescription = stringResource(MR.strings.googleicon)
                )
                Text(
                    text = stringResource(MR.strings.loginWithGoogle),
                    fontSize = 18.sp,
                    color = Tertirary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BottomLoginTextComponent(initialText: String, action: String) {
    val joinourcoven=  stringResource(MR.strings.joinourcoven)
    val navigator = LocalNavigator.currentOrThrow
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = action, annotation = action)
            append(action)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
               println("BottomLoginTextComponent ${span.item} is Clicked")
                if (span.item == joinourcoven) {
                    navigator.push(ListScreen)

                    // navController.navigate("SignupScreen")
                }
            }
    })
}

@Composable
fun SignupTermsAndPrivacyText() {
    val initialText = "Join our coven and accept our "
    val termsNConditionText = "Terms & Conditions"
    val andText = " and "
    val privacyPolicyText = "Privacy Policy."
    val lastText = " Don't be afraid, we don't bite!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = termsNConditionText, annotation = termsNConditionText)
            append(termsNConditionText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
               println("SignupTermsAndPrivacyText ${span.item}")
            }
    })
}

@Composable
fun BottomSignupTextComponent() {
    val navigator = LocalNavigator.currentOrThrow
    val initialText = "Are you a familiar spirit? "
    val loginText = "Log In"
    val lastText = " again and join our Halloween party!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
        withStyle(style = SpanStyle(color = Tertirary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                if (span.item == "Log In") {
                    // navController.navigate("LoginScreen")
                }
            }
    })

}

@Composable
fun TextInfoComponent(textVal: String) {
    Text(text = textVal, color = Tertirary)
}


@Composable
fun CustomRadioButtonIndicator_WithIconToggleButton() {
    MaterialTheme {
        val scrollState = rememberScrollState()
        val selectedValue = remember { mutableStateOf("") }
        val items = listOf("Bug", "Suggestion", "Others")
        Row(Modifier.padding(4.dp).horizontalScroll(scrollState)) {
            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = (selectedValue.value == item),
                        onClick = { selectedValue.value = item },
                        role = Role.RadioButton
                    ).padding(8.dp)
                ) {
                    IconToggleButton(
                        checked = selectedValue.value == item,
                        onCheckedChange = { selectedValue.value = item },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                if (selectedValue.value == item) {
                                    MR.images.ic_circle_checked
                                } else {
                                    MR.images.ic_circle_outline
                                }
                            ),
                            contentDescription = null,
                            tint = feedbackHeadBackground
                        )
                    }
                    Text(
                        text = item,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}