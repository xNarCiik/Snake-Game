package com.example.sampleapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.sampleapp.R

val SnakeFont = FontFamily(
    Font(R.font.snake_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = SnakeFont
)