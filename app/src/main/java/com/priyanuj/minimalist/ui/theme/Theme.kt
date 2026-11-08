package com.priyanuj.minimalist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.priyanuj.minimalist.data.AppTheme

private val OledColorScheme = darkColorScheme(
    primary = TextPrimaryDark,
    secondary = TextSecondaryDark,
    background = BlackOled,
    surface = BlackOled,
    onPrimary = BlackOled,
    onSecondary = TextPrimaryDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = DarkSlateSurface
)

private val SlateColorScheme = darkColorScheme(
    primary = TextPrimaryDark,
    secondary = TextSecondaryDark,
    background = DarkSlateBg,
    surface = DarkSlateSurface,
    onPrimary = DarkSlateBg,
    onSecondary = TextPrimaryDark,
    onBackground = TextPrimaryDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = AccentSubtleDark
)

private val PaperLightColorScheme = lightColorScheme(
    primary = TextPrimaryLight,
    secondary = TextSecondaryLight,
    background = LightBg,
    surface = LightSurface,
    onPrimary = LightBg,
    onSecondary = TextPrimaryLight,
    onBackground = TextPrimaryLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = AccentSubtleLight
)

@Composable
fun MinimalistLauncherTheme(
    appTheme: AppTheme = AppTheme.OLED_BLACK,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.OLED_BLACK -> OledColorScheme
        AppTheme.SLATE_DARK -> SlateColorScheme
        AppTheme.PAPER_WHITE -> PaperLightColorScheme
        AppTheme.LIQUID_MORPHISM -> SlateColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MinimalTypography,
        content = content
    )
}

