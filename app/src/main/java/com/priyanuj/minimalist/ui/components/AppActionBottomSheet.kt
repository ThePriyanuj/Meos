package com.priyanuj.minimalist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.model.AppModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppActionBottomSheet(
    app: AppModel,
    onDismiss: () -> Unit,
    onToggleFavorite: () -> Unit,
    onHideApp: () -> Unit,
    onRenameApp: (String) -> Unit,
    onSetMindfulDelay: (Int) -> Unit,
    onOpenAppInfo: () -> Unit,
    onRequestUninstall: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var isRenaming by remember { mutableStateOf(false) }
    var renameText by remember { mutableStateOf(app.displayName) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 8.dp)
        ) {
            Text(
                text = app.displayName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = app.packageName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
            Spacer(modifier = Modifier.height(8.dp))

            if (isRenaming) {
                OutlinedTextField(
                    value = renameText,
                    onValueChange = { renameText = it },
                    label = { Text("App Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { isRenaming = false }) {
                        Text("Cancel", color = MaterialTheme.colorScheme.secondary)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = {
                        onRenameApp(renameText)
                        isRenaming = false
                    }) {
                        Text("Save", color = MaterialTheme.colorScheme.primary)
                    }
                }
            } else {
                ActionRow(
                    icon = if (app.isFavorite) Icons.Outlined.Star else Icons.Outlined.StarBorder,
                    title = if (app.isFavorite) "Remove from Favorites" else "Add to Favorites",
                    onClick = {
                        onToggleFavorite()
                        onDismiss()
                    }
                )

                ActionRow(
                    icon = Icons.Outlined.Edit,
                    title = "Rename",
                    onClick = { isRenaming = true }
                )

                ActionRow(
                    icon = Icons.Outlined.Timer,
                    title = if (app.mindfulDelaySeconds > 0) "Mindful Delay: ${app.mindfulDelaySeconds}s (Change)" else "Set Mindful Delay",
                    onClick = {
                        val nextDelay = when (app.mindfulDelaySeconds) {
                            0 -> 5
                            5 -> 15
                            15 -> 30
                            else -> 0
                        }
                        onSetMindfulDelay(nextDelay)
                        onDismiss()
                    }
                )

                ActionRow(
                    icon = Icons.Outlined.VisibilityOff,
                    title = "Hide App",
                    onClick = {
                        onHideApp()
                        onDismiss()
                    }
                )

                ActionRow(
                    icon = Icons.Outlined.Info,
                    title = "App Info",
                    onClick = {
                        onOpenAppInfo()
                        onDismiss()
                    }
                )

                ActionRow(
                    icon = Icons.Outlined.Delete,
                    title = "Uninstall",
                    onClick = {
                        onRequestUninstall()
                        onDismiss()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ActionRow(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

