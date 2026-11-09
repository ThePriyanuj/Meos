package com.priyanuj.minimalist.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.model.AppModel
import com.priyanuj.minimalist.ui.components.AlphabetScroller
import com.priyanuj.minimalist.ui.components.AppTextItem
import com.priyanuj.minimalist.ui.theme.liquidDropletGlass
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppDrawerScreen(
    apps: List<AppModel>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onAppClick: (AppModel) -> Unit,
    onAppLongClick: (AppModel) -> Unit,
    onBack: () -> Unit,
    onOpenSettings: () -> Unit = {},
    atmosphere: com.priyanuj.minimalist.data.Atmosphere = com.priyanuj.minimalist.data.Atmosphere.ALPINE,
    fontColor: com.priyanuj.minimalist.data.FontColor = com.priyanuj.minimalist.data.FontColor.WHITE,
    modifier: Modifier = Modifier
) {
    val isLight = atmosphere.isLight
    val isFontBlack = (fontColor == com.priyanuj.minimalist.data.FontColor.BLACK) || (isLight && fontColor != com.priyanuj.minimalist.data.FontColor.WHITE)

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val availableLetters = remember(apps) {
        apps.map { it.sectionHeader }.toSet()
    }

    val letterIndices = remember(apps) {
        val map = mutableMapOf<Char, Int>()
        apps.forEachIndexed { index, app ->
            val char = app.sectionHeader
            if (!map.containsKey(char)) {
                map[char] = index
            }
        }
        map
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // If scrolling downwards at the top of the list, dismiss and return to home
                if (available.y > 50f && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                    onBack()
                }
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (available.y > 35f && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                    onBack()
                }
                return Offset.Zero
            }
        }
    }

    var totalDragY by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(getAtmosphereBrush(atmosphere))
            .nestedScroll(nestedScrollConnection)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { totalDragY = 0f },
                    onDragEnd = {
                        if (totalDragY > 80f && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                            onBack()
                        }
                    },
                    onDrag = { change, dragAmount ->
                        if (dragAmount.y > 0) {
                            totalDragY += dragAmount.y
                        }
                    }
                )
            }
            .padding(horizontal = 24.dp)
            .padding(top = 42.dp, bottom = 16.dp)
    ) {
        // Unified Liquid Glass Search Banner (Light blue tint on mostly white atmospheres, original clear on others)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = if (isLight) {
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
                    color = if (isLight) Color(0x6638BDF8) else Color(0x25FFFFFF),
                    shape = RoundedCornerShape(26.dp)
                )
                .liquidDropletGlass(26.dp)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Search",
                        tint = if (isLight) Color(0xFF0284C7) else Color.White.copy(alpha = 0.85f),
                        modifier = Modifier.size(18.dp)
                    )
                }

                BasicTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 6.dp),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = if (isFontBlack) Color(0xFF0F172A) else Color.White,
                        fontSize = 14.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "search apps...",
                                color = if (isLight) Color(0x990E7490) else Color.White.copy(alpha = 0.45f),
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )

                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchQueryChange("") },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = "Clear",
                            tint = if (isLight) Color(0xFF0284C7) else Color.White.copy(alpha = 0.75f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = onOpenSettings,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = "Settings",
                            tint = if (isLight) Color(0xFF0284C7) else Color.White.copy(alpha = 0.85f),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        // App list + Alphabet Scroller
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(apps, key = { it.packageName }) { app ->
                    AppTextItem(
                        app = app,
                        onClick = { onAppClick(app) },
                        onLongClick = { onAppLongClick(app) },
                        isBlack = isFontBlack
                    )
                }
            }

            if (searchQuery.isEmpty() && apps.isNotEmpty()) {
                AlphabetScroller(
                    availableLetters = availableLetters,
                    onLetterSelected = { letter ->
                        val targetIndex = letterIndices[letter]
                        if (targetIndex != null) {
                            coroutineScope.launch {
                                listState.scrollToItem(targetIndex)
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .fillMaxHeight()
                )
            }
        }
    }
}

