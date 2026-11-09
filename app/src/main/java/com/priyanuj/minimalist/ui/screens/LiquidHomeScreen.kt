package com.priyanuj.minimalist.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.model.AppModel
import com.priyanuj.minimalist.ui.theme.liquidDropletGlass
import kotlin.math.abs

/**
 * Minimalist Launcher with Clear Refractive 3D Glass Typography.
 * Replaces solid cards with transparent sculptural glass that refracts the surrounding wallpaper.
 */
@Composable
fun LiquidHomeScreen(
    favoriteApps: List<AppModel>,
    onAppClick: (AppModel) -> Unit,
    onAppLongClick: (AppModel) -> Unit,
    onWeatherClick: () -> Unit = {},
    onCalendarClick: () -> Unit = {},
    onOpenDrawer: () -> Unit = {},
    onUpiScanClick: () -> Unit = {},
    onQuickDial: () -> Unit = {},
    onSwipeRight: () -> Unit = {},
    onSwipeLeft: () -> Unit = {},
    fontColor: com.priyanuj.minimalist.data.FontColor = com.priyanuj.minimalist.data.FontColor.WHITE,
    atmosphere: com.priyanuj.minimalist.data.Atmosphere = com.priyanuj.minimalist.data.Atmosphere.ALPINE,
    timeHours: String = "21",
    timeMinutes: String = "41",
    dateString: String = "Sun, Nov 8",
    screenTimeString: String = "",
    modifier: Modifier = Modifier
) {
    val isBlack = fontColor == com.priyanuj.minimalist.data.FontColor.BLACK
    var totalDragX by remember { mutableFloatStateOf(0f) }
    var totalDragY by remember { mutableFloatStateOf(0f) }

    var currentTime by remember { mutableStateOf(Date()) }
    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Date()
            delay(1000)
        }
    }

    val hourFormat = remember { SimpleDateFormat("H", Locale.getDefault()) }
    val minFormat = remember { SimpleDateFormat("mm", Locale.getDefault()) }
    val fullDateFormat = remember { SimpleDateFormat("EEE, MMM d", Locale.getDefault()) }

    val liveHours = remember(currentTime) { hourFormat.format(currentTime) }
    val liveMinutes = remember(currentTime) { minFormat.format(currentTime) }
    val liveDate = remember(currentTime) { fullDateFormat.format(currentTime).uppercase() }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // If scrolling up on the home screen
                if (available.y < -25f) {
                    onOpenDrawer()
                }
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (available.y < -20f) {
                    onOpenDrawer()
                }
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(getAtmosphereBrush(atmosphere))
            .nestedScroll(nestedScrollConnection)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        totalDragX = 0f
                        totalDragY = 0f
                    },
                    onDragEnd = {
                        val swipeThreshold = 40f
                        if (abs(totalDragY) >= abs(totalDragX)) {
                            if (totalDragY < -swipeThreshold) {
                                onOpenDrawer() // Slide up -> Open All Apps
                            }
                        } else {
                            if (totalDragX < -swipeThreshold) {
                                onSwipeLeft()  // Swipe Left
                            } else if (totalDragX > swipeThreshold) {
                                onSwipeRight() // Swipe Right
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        totalDragX += dragAmount.x
                        totalDragY += dragAmount.y
                    }
                )
            }
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. Top Section: Clean Minimalist Date & Longitudinal Water Drop Clock
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Date Header: Crisp, Sharp, High-Definition
                Row(
                    modifier = Modifier
                        .clickable { onCalendarClick() }
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = liveDate,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isBlack) Color.Black else Color.White.copy(alpha = 0.95f),
                        letterSpacing = 2.8.sp,
                        style = if (isBlack) androidx.compose.ui.text.TextStyle() else androidx.compose.ui.text.TextStyle(
                            shadow = Shadow(
                                color = Color(0x80000000),
                                offset = Offset(0f, 1f),
                                blurRadius = 2f
                            )
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // 2. GIANT LONGITUDINAL WATER DROP REFRACTION CLOCK (ZERO BOUNDARY, CRISP EDGES)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clickable { onWeatherClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        // Hours
                        ClearGlassNumeral(text = liveHours, isBlack = isBlack)

                        // 3D Clear Glass Colon Droplets (Harmonious vertical spacing & sharp caustics)
                        Column(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(36.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GlassColonSphere(isBlack = isBlack)
                            GlassColonSphere(isBlack = isBlack)
                        }

                        // Minutes
                        ClearGlassNumeral(text = liveMinutes, isBlack = isBlack)
                    }
                }
            }

            // 2. Middle Section: Pinned Essentials with Harmonious, Even Vertical Spacing
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (favoriteApps.isEmpty()) {
                    Text(
                        text = "no favorites pinned.\nopen all apps and hold to pin.",
                        fontSize = 13.sp,
                        color = Color.White.copy(alpha = 0.5f),
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        favoriteApps.forEach { app ->
                            ClearGlassAppRow(
                                app = app,
                                onClick = { onAppClick(app) },
                                onLongClick = { onAppLongClick(app) },
                                isBlack = isBlack
                            )
                        }
                    }
                }
            }

            // 3. Bottom Clear Glass Banner: Sharp Edge Caustics (Light blue tint on mostly white atmospheres)
            val isLightAtmo = atmosphere.isLight
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = if (isLightAtmo) {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0x55BAE6FD), // Light ice-blue tint for light/white atmospheres
                                    Color(0x35E0F2FE)
                                )
                            )
                        } else {
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0x0AFFFFFF),
                                    Color(0x05FFFFFF)
                                )
                            )
                        },
                        shape = RoundedCornerShape(26.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = if (isLightAtmo) Color(0x6638BDF8) else Color(0x25FFFFFF),
                        shape = RoundedCornerShape(26.dp)
                    )
                    .liquidDropletGlass(26.dp)
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left: Minimalist Sharp Phone Icon (Replaces 'all apps')
                    IconButton(
                        onClick = onQuickDial,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Call,
                            contentDescription = "Phone",
                            tint = if (isBlack) Color.Black else if (isLightAtmo) Color(0xFF0284C7) else Color.White.copy(alpha = 0.95f),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Right: Minimalist Sharp UPI QR Scan Icon
                    IconButton(
                        onClick = onUpiScanClick,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.QrCodeScanner,
                            contentDescription = "Scan to Pay (UPI)",
                            tint = if (isBlack) Color.Black else if (isLightAtmo) Color(0xFF0284C7) else Color.White.copy(alpha = 0.95f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

        }
    }
}

/**
 * Giant Longitudinal Water Drop Numeral (Zero boundary, 3D Liquid Meniscus Caustics).
 * Stretched vertically along the longitudinal axis with 3D optical liquid meniscus gradient matching the ':' colon droplet.
 * No stroke, no borders—refracts the background image like a clear pool of water with sharp edges.
 */
@Composable
fun ClearGlassNumeral(text: String, isBlack: Boolean = false) {
    Text(
        text = text,
        fontSize = 136.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (-1).sp,
        fontFamily = FontFamily.SansSerif,
        color = if (isBlack) Color.Black else Color.Unspecified,
        modifier = Modifier.graphicsLayer {
            scaleY = 1.36f
            scaleX = 1.214f // Expanded latitudinal length
        },
        style = if (isBlack) {
            androidx.compose.ui.text.TextStyle()
        } else {
            androidx.compose.ui.text.TextStyle(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.65f), // Top specular liquid meniscus crest (matches ':' colon drop)
                        Color.White.copy(alpha = 0.12f), // Crystal clear liquid water interior
                        Color(0x33001026)                // Bottom refractive optical shadow
                    )
                ),
                shadow = Shadow(
                    color = Color(0x99001026),
                    offset = Offset(0f, 1.5f),
                    blurRadius = 2f
                )
            )
        }
    )
}

/**
 * Water Drop Spherical Colon (White in White mode, Black in Black mode).
 */
@Composable
fun GlassColonSphere(isBlack: Boolean = false) {
    Canvas(modifier = Modifier.size(12.dp)) {
        if (isBlack) {
            drawCircle(
                color = Color.Black,
                radius = size.minDimension / 2f,
                center = center
            )
        } else {
            // Subtle drop shadow matching text shadow
            drawCircle(
                color = Color(0x66000000),
                radius = size.minDimension / 2f,
                center = Offset(center.x, center.y + 1f)
            )
            // Crisp solid white colon dot in white mode
            drawCircle(
                color = Color.White,
                radius = size.minDimension / 2f,
                center = center
            )
        }
    }
}

/**
 * Pure Clear Refractive Glass App Row (No card box, Razor-Sharp Typography).
 */
@Composable
fun ClearGlassAppRow(
    app: AppModel,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isBlack: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .clickable { onClick() }
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Clear Refractive Glass Word with 3D liquid caustics matching ':' colon
        Text(
            text = app.displayName.lowercase(),
            fontSize = 23.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 3.2.sp, // Expanded latitudinal tracking
            color = if (isBlack) Color.Black else Color.Unspecified,
            style = if (isBlack) {
                androidx.compose.ui.text.TextStyle()
            } else {
                androidx.compose.ui.text.TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.55f), // Top specular crest
                            Color.White.copy(alpha = 0.14f), // Crystal clear liquid body
                            Color(0x26001026)                // Bottom lens shade
                        )
                    ),
                    shadow = Shadow(
                        color = Color(0xCC001026),
                        offset = Offset(0f, 1.2f),
                        blurRadius = 1.5f
                    )
                )
            }
        )

        if (app.mindfulDelaySeconds > 0) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .liquidDropletGlass(99.dp)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "⏱️ ${app.mindfulDelaySeconds}s delay",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFD54F)
                )
            }
        }
    }
}

/**
 * Returns dynamic atmosphere brush for home screen matching the chosen color/neutral theme.
 */
fun getAtmosphereBrush(atmosphere: com.priyanuj.minimalist.data.Atmosphere): Brush {
    return when (atmosphere) {
        com.priyanuj.minimalist.data.Atmosphere.ALPINE -> Brush.verticalGradient(
            colors = listOf(Color(0xFF0D253F), Color(0xFF14365D), Color(0xFF091829))
        )
        com.priyanuj.minimalist.data.Atmosphere.RED -> Brush.verticalGradient(
            colors = listOf(Color(0xFF7F1D1D), Color(0xFF450A0A), Color(0xFF200407), Color(0xFF0A0102))
        )
        com.priyanuj.minimalist.data.Atmosphere.GREEN -> Brush.verticalGradient(
            colors = listOf(Color(0xFF064E3B), Color(0xFF032B21), Color(0xFF011B14), Color(0xFF010C08))
        )
        com.priyanuj.minimalist.data.Atmosphere.YELLOW -> Brush.verticalGradient(
            colors = listOf(Color(0xFF92400E), Color(0xFF712B08), Color(0xFF431702), Color(0xFF1C0A00))
        )
        com.priyanuj.minimalist.data.Atmosphere.PURPLE -> Brush.verticalGradient(
            colors = listOf(Color(0xFF581C87), Color(0xFF3B0764), Color(0xFF20023A), Color(0xFF0C0018))
        )
        com.priyanuj.minimalist.data.Atmosphere.BLACK -> Brush.verticalGradient(
            colors = listOf(Color(0xFF18181B), Color(0xFF101013), Color(0xFF08080A), Color(0xFF000000))
        )
        com.priyanuj.minimalist.data.Atmosphere.WHITE -> Brush.verticalGradient(
            colors = listOf(Color(0xFFF8FAFC), Color(0xFFE2E8F0), Color(0xFFCBD5E1), Color(0xFF94A3B8))
        )
        com.priyanuj.minimalist.data.Atmosphere.SLATE -> Brush.verticalGradient(
            colors = listOf(Color(0xFF334155), Color(0xFF1E293B), Color(0xFF0F172A), Color(0xFF020617))
        )
        com.priyanuj.minimalist.data.Atmosphere.STONE -> Brush.verticalGradient(
            colors = listOf(Color(0xFF44403C), Color(0xFF292524), Color(0xFF1C1917), Color(0xFF0C0A09))
        )
    }
}
