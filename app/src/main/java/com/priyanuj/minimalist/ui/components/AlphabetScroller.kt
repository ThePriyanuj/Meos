package com.priyanuj.minimalist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlphabetScroller(
    availableLetters: Set<Char>,
    onLetterSelected: (Char) -> Unit,
    modifier: Modifier = Modifier
) {
    val alphabet = ('A'..'Z').toList()

    Column(
        modifier = modifier.padding(end = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        alphabet.forEach { letter ->
            val isPresent = availableLetters.contains(letter)
            Text(
                text = letter.toString(),
                fontSize = 10.sp,
                color = if (isPresent) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                },
                modifier = Modifier
                    .clickable(enabled = isPresent) {
                        onLetterSelected(letter)
                    }
                    .padding(vertical = 1.dp)
            )
        }
    }
}

