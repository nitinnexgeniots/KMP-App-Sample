package com.jetbrains.kmpapp.theme



import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.MR
import dev.icerock.moko.resources.compose.fontFamilyResource
//
//val loginFontFamily = FontFamily(
//    Font(MR.fonts.poppins.regular, FontWeight.Normal),
//    Font(MR.fonts.poppins.bold, FontWeight.Bold)
//)



//val loginFontFamilyBold: FontFamily = fontFamilyResource(MR.fonts.poppins.bold)
//val loginFontFamilyNormal: FontFamily = fontFamilyResource(MR.fonts.poppins.regular)


val titleLarge = Typography(
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    )
)

val Typography = Typography(
    body1 = TextStyle(
        //  fontFamily = loginFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
)

val Header = TextStyle(
    //fontFamily = loginFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val Description = TextStyle(
   //fontFamily = loginFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
)

val ButtonTextStyle = TextStyle(
   // fontFamily = loginFontFamily,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp
)