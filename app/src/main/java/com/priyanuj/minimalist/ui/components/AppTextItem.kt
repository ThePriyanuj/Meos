package com.priyanuj.minimalist.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.priyanuj.minimalist.model.AppModel
import com.priyanuj.minimalist.ui.theme.liquidDropletGlass

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppTextItem(
    app: AppModel,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    isHomeFavorite: Boolean = false,
    isBlack: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Clear Refractive Glass Typography matching Home Screen (No card/border)
        Text(
            text = app.displayName.lowercase(),
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 2.8.sp,
            color = if (isBlack) Color.Black else Color.Unspecified,
            style = if (isBlack) {
                androidx.compose.ui.text.TextStyle()
            } else {
                androidx.compose.ui.text.TextStyle(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.55f),
                            Color.White.copy(alpha = 0.14f),
                            Color(0x26001026)
                        )
                    ),
                    shadow = Shadow(
                        color = Color(0xCC001026),
                        offset = Offset(0f, 1.2f),
                        blurRadius = 1.5f
                    )
                )
            },
            maxLines = 1
        )
    }
}

