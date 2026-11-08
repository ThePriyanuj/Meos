package com.priyanuj.minimalist.model

data class AppModel(
    val packageName: String,
    val activityName: String,
    val originalLabel: String,
    val customLabel: String? = null,
    val isFavorite: Boolean = false,
    val favoriteOrder: Int = 0,
    val isHidden: Boolean = false,
    val mindfulDelaySeconds: Int = 0
) {
    val displayName: String
        get() = customLabel?.takeIf { it.isNotBlank() } ?: originalLabel

    val sectionHeader: Char
        get() = displayName.firstOrNull()?.uppercaseChar()?.let {
            if (it in 'A'..'Z') it else '#'
        } ?: '#'
}

