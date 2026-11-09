package com.priyanuj.minimalist.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.data.AppTheme
import com.priyanuj.minimalist.data.Atmosphere
import com.priyanuj.minimalist.data.FontColor
import com.priyanuj.minimalist.data.FontStyle
import com.priyanuj.minimalist.data.TextTransform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: AppTheme,
    onThemeSelect: (AppTheme) -> Unit,
    currentAtmosphere: Atmosphere = Atmosphere.ALPINE,
    onAtmosphereSelect: (Atmosphere) -> Unit = {},
    fontColor: FontColor = FontColor.WHITE,
    onFontColorSelect: (FontColor) -> Unit = {},
    fontStyle: FontStyle = FontStyle.SANS_SERIF,
    onFontSelect: (FontStyle) -> Unit = {},
    textTransform: TextTransform = TextTransform.LOWERCASE,
    onTextTransformSelect: (TextTransform) -> Unit = {},
    selectedPaymentApp: String = "com.google.android.apps.nbu.paisa.user",
    onPaymentAppSelect: (String) -> Unit = {},
    onOpenHiddenApps: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var currentSection by remember { mutableStateOf("main") }

    var autoOpenKeyboard by remember { mutableStateOf(true) }
    var matchLockScreen by remember { mutableStateOf(true) }
    var swipeLeftSearch by remember { mutableStateOf(true) }
    var swipeRightTodo by remember { mutableStateOf(true) }
    var doubleTapLock by remember { mutableStateOf(true) }
    var is24Hour by remember { mutableStateOf(false) }
    var dateFormatIdx by remember { mutableStateOf(0) }
    var fontSizeIdx by remember { mutableStateOf(0) }

    val dateFormats = listOf("Sun, Nov 8", "Sunday, 8 Nov", "08/11/2026")
    val fontSizes = listOf("regular", "small", "large")

    val isLightAtmo = currentAtmosphere.isLight
    val contentColor = if (fontColor == FontColor.BLACK || (isLightAtmo && fontColor != FontColor.WHITE)) {
        Color(0xFF0F172A)
    } else {
        Color(0xFFFAFAFA)
    }
    val subtextColor = contentColor.copy(alpha = 0.5f)
    val dividerColor = contentColor.copy(alpha = 0.12f)
    val veilColor = if (isLightAtmo) Color(0xC0F8FAFC) else Color(0x990A0E18)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(getAtmosphereBrush(currentAtmosphere))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(veilColor)
        )

        Scaffold(
            topBar = {
                val title = when (currentSection) {
                    "home" -> "home screen"
                    "display" -> "display"
                    "gestures" -> "gestures"
                    "theme" -> "color theme"
                    "scanner" -> "scanner shortcut"
                    else -> "launcher settings"
                }
                TopAppBar(
                    title = {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp),
                            color = contentColor
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            when (currentSection) {
                                "main" -> onBack()
                                "home", "display", "gestures" -> currentSection = "main"
                                "theme" -> currentSection = "display"
                                "scanner" -> currentSection = "home"
                                else -> currentSection = "main"
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "Back",
                                tint = contentColor
                            )
                        }
                    },
                    actions = {
                        Text(
                            text = "done",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp),
                            color = contentColor.copy(alpha = 0.75f),
                            modifier = Modifier
                                .clickable { onBack() }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        titleContentColor = contentColor
                    )
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                when (currentSection) {
                    "main" -> {
                        // 1. Home screen >
                        SettingsNavRow("home screen", null, contentColor, subtextColor) {
                            currentSection = "home"
                        }
                        // 2. Display >
                        SettingsNavRow("display", null, contentColor, subtextColor) {
                            currentSection = "display"
                        }
                        // 3. Gestures >
                        SettingsNavRow("gestures", null, contentColor, subtextColor) {
                            currentSection = "gestures"
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = dividerColor)
                        Spacer(modifier = Modifier.height(12.dp))

                        // Recommend to a friend
                        SettingsClickRow("recommend to a friend", contentColor) {
                            Toast.makeText(context, "link copied to clipboard", Toast.LENGTH_SHORT).show()
                        }
                        // Device settings
                        SettingsClickRow("device settings", contentColor) {
                            Toast.makeText(context, "opening device settings", Toast.LENGTH_SHORT).show()
                        }
                    }

                    "home" -> {
                        SettingsInfoRow("phone dialer shortcut", "phone", contentColor, subtextColor) {
                            Toast.makeText(context, "dialer shortcut: phone", Toast.LENGTH_SHORT).show()
                        }
                        SettingsNavRow("scanner shortcut", "google pay", contentColor, subtextColor) {
                            currentSection = "scanner"
                        }
                        SettingsInfoRow("clock shortcut", "clock", contentColor, subtextColor) {
                            Toast.makeText(context, "clock shortcut: clock", Toast.LENGTH_SHORT).show()
                        }
                        SettingsInfoRow("time format", if (is24Hour) "24-hour" else "12-hour", contentColor, subtextColor) {
                            is24Hour = !is24Hour
                            Toast.makeText(context, "time format: ${if (is24Hour) "24-hour" else "12-hour"}", Toast.LENGTH_SHORT).show()
                        }
                        SettingsInfoRow("date format", dateFormats[dateFormatIdx], contentColor, subtextColor) {
                            dateFormatIdx = (dateFormatIdx + 1) % dateFormats.size
                            Toast.makeText(context, "date format updated", Toast.LENGTH_SHORT).show()
                        }
                        SettingsInfoRow("hidden apps", "open", contentColor, subtextColor) {
                            onOpenHiddenApps()
                        }
                        SettingsToggleRow("automatically open keyboard", autoOpenKeyboard, contentColor) {
                            autoOpenKeyboard = it
                        }
                    }

                    "display" -> {
                        SettingsNavRow("color theme", currentAtmosphere.displayName, contentColor, subtextColor) {
                            currentSection = "theme"
                        }
                        SettingsInfoRow("font size", fontSizes[fontSizeIdx], contentColor, subtextColor) {
                            fontSizeIdx = (fontSizeIdx + 1) % fontSizes.size
                            Toast.makeText(context, "font size: ${fontSizes[fontSizeIdx]}", Toast.LENGTH_SHORT).show()
                        }
                        SettingsToggleRow("automatically set matching lock screen background", matchLockScreen, contentColor) {
                            matchLockScreen = it
                        }
                    }

                    "gestures" -> {
                        SettingsToggleRow("swipe left to open search", swipeLeftSearch, contentColor) {
                            swipeLeftSearch = it
                        }
                        SettingsToggleRow("swipe right to open to do list", swipeRightTodo, contentColor) {
                            swipeRightTodo = it
                        }
                        SettingsToggleRow("double-tap to lock", doubleTapLock, contentColor) {
                            doubleTapLock = it
                        }
                    }

                    "theme" -> {
                        Text(
                            text = "atmosphere",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = subtextColor,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Atmosphere.values().forEach { atmo ->
                            OptionRow(
                                title = atmo.displayName,
                                isSelected = currentAtmosphere == atmo,
                                contentColor = contentColor,
                                onClick = { onAtmosphereSelect(atmo) }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        HorizontalDivider(color = dividerColor)
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "font color",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = subtextColor,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OptionRow(
                            title = "white",
                            isSelected = fontColor == FontColor.WHITE,
                            contentColor = contentColor,
                            onClick = { onFontColorSelect(FontColor.WHITE) }
                        )
                        OptionRow(
                            title = "black",
                            isSelected = fontColor == FontColor.BLACK,
                            contentColor = contentColor,
                            onClick = { onFontColorSelect(FontColor.BLACK) }
                        )
                    }

                    "scanner" -> {
                        val paymentApps = listOf(
                            "com.google.android.apps.nbu.paisa.user" to "google pay",
                            "com.phonepe.app" to "phonepe",
                            "net.one97.paytm" to "paytm",
                            "in.org.npci.upiapp" to "bhim upi",
                            "system" to "system upi (auto)"
                        )
                        paymentApps.forEach { (pkg, name) ->
                            OptionRow(
                                title = name,
                                isSelected = selectedPaymentApp == pkg,
                                contentColor = contentColor,
                                onClick = { onPaymentAppSelect(pkg) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun SettingsNavRow(
    title: String,
    subtext: String?,
    contentColor: Color,
    subtextColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        if (subtext != null) {
            Text(
                text = subtext,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                color = subtextColor
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Text(
            text = ">",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
            color = subtextColor
        )
    }
}

@Composable
private fun SettingsInfoRow(
    title: String,
    value: String,
    contentColor: Color,
    subtextColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
            color = subtextColor
        )
    }
}

@Composable
private fun SettingsClickRow(
    title: String,
    contentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SettingsToggleRow(
    title: String,
    checked: Boolean,
    contentColor: Color,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = if (contentColor == Color(0xFF0F172A)) Color.White else Color.Black,
                checkedTrackColor = contentColor,
                uncheckedThumbColor = contentColor,
                uncheckedTrackColor = contentColor.copy(alpha = 0.2f),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Composable
private fun OptionRow(
    title: String,
    isSelected: Boolean,
    contentColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}
