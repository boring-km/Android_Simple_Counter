package com.boring.gangmin.counter.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun counterTypography() = Typography(
    headlineLarge = TextStyle(
        fontSize = 200.nonScaledSp,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    headlineMedium = TextStyle(
        fontSize = 60.nonScaledSp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    titleLarge = TextStyle(
        fontSize = 150.nonScaledSp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W400,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
)

val Int.nonScaledSp
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp