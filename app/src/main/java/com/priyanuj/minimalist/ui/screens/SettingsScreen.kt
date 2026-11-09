package com.priyanuj.minimalist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.data.AppTheme
import com.priyanuj.minimalist.data.FontStyle
import com.priyanuj.minimalist.data.TextTransform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    currentTheme: AppTheme,
    onThemeSelect: (AppTheme) -> Unit,
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("settings", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "atmosphere",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionRow(
                title = "oled pure black",
                isSelected = currentTheme == AppTheme.OLED_BLACK,
                onClick = { onThemeSelect(AppTheme.OLED_BLACK) }
            )

            OptionRow(
                title = "slate dark gray",
                isSelected = currentTheme == AppTheme.SLATE_DARK,
                onClick = { onThemeSelect(AppTheme.SLATE_DARK) }
            )

            OptionRow(
                title = "paper white",
                isSelected = currentTheme == AppTheme.PAPER_WHITE,
                onClick = { onThemeSelect(AppTheme.PAPER_WHITE) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "typography",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionRow(
                title = "sans-serif (clean modern)",
                isSelected = fontStyle == FontStyle.SANS_SERIF,
                onClick = { onFontSelect(FontStyle.SANS_SERIF) }
            )

            OptionRow(
                title = "monospace (minimal code)",
                isSelected = fontStyle == FontStyle.MONOSPACE,
                onClick = { onFontSelect(FontStyle.MONOSPACE) }
            )

            OptionRow(
                title = "serif (editorial aesthetic)",
                isSelected = fontStyle == FontStyle.SERIF,
                onClick = { onFontSelect(FontStyle.SERIF) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "payment scanner",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OptionRow(
                title = "google pay",
                isSelected = selectedPaymentApp == "com.google.android.apps.nbu.paisa.user",
                onClick = { onPaymentAppSelect("com.google.android.apps.nbu.paisa.user") }
            )

            OptionRow(
                title = "phonepe",
                isSelected = selectedPaymentApp == "com.phonepe.app",
                onClick = { onPaymentAppSelect("com.phonepe.app") }
            )

            OptionRow(
                title = "paytm",
                isSelected = selectedPaymentApp == "net.one97.paytm",
                onClick = { onPaymentAppSelect("net.one97.paytm") }
            )

            OptionRow(
                title = "bhim upi",
                isSelected = selectedPaymentApp == "in.org.npci.upiapp",
                onClick = { onPaymentAppSelect("in.org.npci.upiapp") }
            )

            OptionRow(
                title = "system upi (auto)",
                isSelected = selectedPaymentApp == "system",
                onClick = { onPaymentAppSelect("system") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "privacy",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 11.sp,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenHiddenApps() }
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.VisibilityOff,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "hidden apps",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun OptionRow(
    title: String,
    isSelected: Boolean,
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
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
        if (isSelected) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
