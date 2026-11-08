package com.priyanuj.minimalist.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.model.AppModel
import com.priyanuj.minimalist.ui.components.AppTextItem
import com.priyanuj.minimalist.ui.components.TimeWidget
import kotlin.math.abs

@Composable
fun HomeScreen(
    favoriteApps: List<AppModel>,
    onAppClick: (AppModel) -> Unit,
    onAppLongClick: (AppModel) -> Unit,
    onTimeClick: () -> Unit,
    onDateClick: () -> Unit,
    onOpenDrawer: () -> Unit,
    onOpenSettings: () -> Unit,
    onSwipeRight: () -> Unit = {},
    onSwipeLeft: () -> Unit = {},
    screenTimeString: String = "1h 42m screen time • 38 unlocks",
    modifier: Modifier = Modifier
) {
    var totalDragX by remember { mutableFloatStateOf(0f) }
    var totalDragY by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        totalDragX = 0f
                        totalDragY = 0f
                    },
                    onDragEnd = {
                        val swipeThreshold = 140f
                        if (abs(totalDragX) > abs(totalDragY)) {
                            if (totalDragX > swipeThreshold) {
                                onSwipeRight() // Swipe Right -> Phone
                            } else if (totalDragX < -swipeThreshold) {
                                onSwipeLeft()  // Swipe Left -> Camera
                            }
                        } else {
                            if (totalDragY < -swipeThreshold) {
                                onOpenDrawer() // Swipe Up -> App Drawer
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        totalDragX += dragAmount.x
                        totalDragY += dragAmount.y
                    }
                )
            }
            .padding(horizontal = 28.dp, vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(28.dp))
                TimeWidget(
                    onTimeClick = onTimeClick,
                    onDateClick = onDateClick
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Screen Time & Unlocks counter
                Text(
                    text = screenTimeString,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                        letterSpacing = 0.3.sp
                    )
                )

                Spacer(modifier = Modifier.height(48.dp))

                if (favoriteApps.isEmpty()) {
                    Text(
                        text = "no favorites yet.\nopen all apps and hold to pin.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            lineHeight = 22.sp
                        ),
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                } else {
                    LazyColumn {
                        items(favoriteApps, key = { it.packageName }) { app ->
                            AppTextItem(
                                app = app,
                                onClick = { onAppClick(app) },
                                onLongClick = { onAppLongClick(app) },
                                isHomeFavorite = true
                            )
                        }
                    }
                }
            }

            // Bottom Navigation Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .clickable { onOpenDrawer() }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "all apps",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Normal
                        )
                    )
                }

                IconButton(onClick = onOpenSettings) {
                    Icon(
                        imageVector = Icons.Outlined.Settings,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}
