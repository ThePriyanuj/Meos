package com.priyanuj.minimalist.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object LiquidMorphismColors {
    // Deep Ocean Glass
    val BlueGlassTop = Color(0xCC07599C)
    val BlueGlassMiddle = Color(0xD9043A6B)
    val BlueGlassBottom = Color(0xE6032C52)

    // Clear Droplet Glass
    val DropletTop = Color(0x59FFFFFF)
    val DropletMiddle = Color(0x1AFFFFFF)
    val DropletBottom = Color(0x2EFFFFFF)

    // Specular Edge Highlights
    val BorderHighlight = Color(0x8CFFFFFF)
    val BorderSubtle = Color(0x26FFFFFF)

    // Pure Clear Glass Typography
    val GlassTextRim = Color(0xCCFFFFFF)
    val GlassTextBody = Color(0x1AFFFFFF)
    val GlassTextGlint = Color(0xF2FFFFFF)
}

/**
 * Custom TextStyle for Clear 3D Glass Text that refracts its surroundings.
 * Features a transparent inner body with specular rim reflections and depth shadow.
 */
fun clearGlassTextStyle(
    fontSize: androidx.compose.ui.unit.TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Light
): TextStyle {
    return TextStyle(
        color = LiquidMorphismColors.GlassTextBody,
        fontSize = fontSize,
        fontWeight = fontWeight,
        shadow = Shadow(
            color = Color(0x80001432),
            offset = androidx.compose.ui.geometry.Offset(2f, 3f),
            blurRadius = 6f
        )
    )
}

fun Modifier.liquidBlueGlass(cornerRadius: Dp = 34.dp): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    return this
        .shadow(
            elevation = 16.dp,
            shape = shape,
            ambientColor = Color(0x40001432),
            spotColor = Color(0x80001432)
        )
        .clip(shape)
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    LiquidMorphismColors.BlueGlassTop,
                    LiquidMorphismColors.BlueGlassMiddle,
                    LiquidMorphismColors.BlueGlassBottom
                )
            )
        )
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    LiquidMorphismColors.BorderHighlight,
                    LiquidMorphismColors.BorderSubtle
                )
            ),
            shape = shape
        )
}

fun Modifier.liquidDropletGlass(cornerRadius: Dp = 22.dp): Modifier {
    val shape = RoundedCornerShape(cornerRadius)
    return this
        .shadow(
            elevation = 10.dp,
            shape = shape,
            ambientColor = Color(0x66000C1C),
            spotColor = Color(0x66000C1C)
        )
        .clip(shape)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.06f),
                    Color.White.copy(alpha = 0.02f)
                )
            )
        )
        .border(
            width = 0.5.dp,
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.45f), // Micro-sharp specular top edge
                    Color(0x33000C1C)                 // Bottom lens shadow (no white border)
                )
            ),
            shape = shape
        )
}
