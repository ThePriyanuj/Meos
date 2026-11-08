package com.priyanuj.minimalist.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONObject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "minimalist_prefs")

enum class AppTheme {
    OLED_BLACK,
    PAPER_WHITE,
    SLATE_DARK,
    LIQUID_MORPHISM
}

enum class FontStyle {
    SANS_SERIF,
    MONOSPACE,
    SERIF
}

enum class TextTransform {
    LOWERCASE,
    ORIGINAL,
    UPPERCASE
}

enum class IconStyle {
    TEXT_ONLY,
    DOT_INDICATOR,
    MONOCHROME_OUTLINE
}

class AppPreferences(private val context: Context) {

    companion object {
        private val KEY_FAVORITES = stringSetPreferencesKey("favorite_packages")
        private val KEY_HIDDEN = stringSetPreferencesKey("hidden_packages")
        private val KEY_CUSTOM_NAMES = stringPreferencesKey("custom_names_json")
        private val KEY_MINDFUL_DELAYS = stringPreferencesKey("mindful_delays_json")
        private val KEY_THEME = stringPreferencesKey("selected_theme")
        private val KEY_FONT_STYLE = stringPreferencesKey("font_style")
        private val KEY_TEXT_TRANSFORM = stringPreferencesKey("text_transform")
        private val KEY_ICON_STYLE = stringPreferencesKey("icon_style")
        private val KEY_SHOW_SCREEN_TIME = booleanPreferencesKey("show_screen_time")
        private val KEY_WALLPAPER_DIM = floatPreferencesKey("wallpaper_dim_level")
        private val KEY_SWIPE_RIGHT_PKG = stringPreferencesKey("swipe_right_package")
        private val KEY_SWIPE_LEFT_PKG = stringPreferencesKey("swipe_left_package")
        private val KEY_MAX_FAVORITES = intPreferencesKey("max_favorites_count")
        private val KEY_PAYMENT_APP = stringPreferencesKey("default_payment_app")
    }

    val favoritesFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[KEY_FAVORITES] ?: emptySet()
    }

    val hiddenFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[KEY_HIDDEN] ?: emptySet()
    }

    val customNamesFlow: Flow<Map<String, String>> = context.dataStore.data.map { preferences ->
        val jsonStr = preferences[KEY_CUSTOM_NAMES] ?: "{}"
        try {
            val json = JSONObject(jsonStr)
            val map = mutableMapOf<String, String>()
            json.keys().forEach { key ->
                map[key] = json.getString(key)
            }
            map
        } catch (_: Exception) {
            emptyMap()
        }
    }

    val mindfulDelaysFlow: Flow<Map<String, Int>> = context.dataStore.data.map { preferences ->
        val jsonStr = preferences[KEY_MINDFUL_DELAYS] ?: "{}"
        try {
            val json = JSONObject(jsonStr)
            val map = mutableMapOf<String, Int>()
            json.keys().forEach { key ->
                map[key] = json.getInt(key)
            }
            map
        } catch (_: Exception) {
            emptyMap()
        }
    }

    val themeFlow: Flow<AppTheme> = context.dataStore.data.map { preferences ->
        val raw = preferences[KEY_THEME] ?: AppTheme.LIQUID_MORPHISM.name
        try { AppTheme.valueOf(raw) } catch (_: Exception) { AppTheme.LIQUID_MORPHISM }
    }

    val fontStyleFlow: Flow<FontStyle> = context.dataStore.data.map { preferences ->
        val raw = preferences[KEY_FONT_STYLE] ?: FontStyle.SANS_SERIF.name
        try { FontStyle.valueOf(raw) } catch (_: Exception) { FontStyle.SANS_SERIF }
    }

    val textTransformFlow: Flow<TextTransform> = context.dataStore.data.map { preferences ->
        val raw = preferences[KEY_TEXT_TRANSFORM] ?: TextTransform.LOWERCASE.name
        try { TextTransform.valueOf(raw) } catch (_: Exception) { TextTransform.LOWERCASE }
    }

    val iconStyleFlow: Flow<IconStyle> = context.dataStore.data.map { preferences ->
        val raw = preferences[KEY_ICON_STYLE] ?: IconStyle.TEXT_ONLY.name
        try { IconStyle.valueOf(raw) } catch (_: Exception) { IconStyle.TEXT_ONLY }
    }

    val showScreenTimeFlow: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[KEY_SHOW_SCREEN_TIME] ?: true
    }

    val wallpaperDimFlow: Flow<Float> = context.dataStore.data.map { preferences ->
        preferences[KEY_WALLPAPER_DIM] ?: 1.0f
    }

    val swipeRightPackageFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[KEY_SWIPE_RIGHT_PKG]
    }

    val swipeLeftPackageFlow: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[KEY_SWIPE_LEFT_PKG]
    }

    val maxFavoritesFlow: Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[KEY_MAX_FAVORITES] ?: 6
    }

    val paymentAppFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[KEY_PAYMENT_APP] ?: "com.google.android.apps.nbu.paisa.user"
    }

    suspend fun setFavorite(packageName: String, isFav: Boolean) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_FAVORITES]?.toMutableSet() ?: mutableSetOf()
            if (isFav) current.add(packageName) else current.remove(packageName)
            prefs[KEY_FAVORITES] = current
        }
    }

    suspend fun setHidden(packageName: String, isHidden: Boolean) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_HIDDEN]?.toMutableSet() ?: mutableSetOf()
            if (isHidden) {
                current.add(packageName)
                val favs = prefs[KEY_FAVORITES]?.toMutableSet() ?: mutableSetOf()
                favs.remove(packageName)
                prefs[KEY_FAVORITES] = favs
            } else {
                current.remove(packageName)
            }
            prefs[KEY_HIDDEN] = current
        }
    }

    suspend fun setCustomName(packageName: String, customName: String?) {
        context.dataStore.edit { prefs ->
            val jsonStr = prefs[KEY_CUSTOM_NAMES] ?: "{}"
            val json = try { JSONObject(jsonStr) } catch (_: Exception) { JSONObject() }
            if (customName.isNullOrBlank()) {
                json.remove(packageName)
            } else {
                json.put(packageName, customName.trim())
            }
            prefs[KEY_CUSTOM_NAMES] = json.toString()
        }
    }

    suspend fun setMindfulDelay(packageName: String, seconds: Int) {
        context.dataStore.edit { prefs ->
            val jsonStr = prefs[KEY_MINDFUL_DELAYS] ?: "{}"
            val json = try { JSONObject(jsonStr) } catch (_: Exception) { JSONObject() }
            if (seconds <= 0) {
                json.remove(packageName)
            } else {
                json.put(packageName, seconds)
            }
            prefs[KEY_MINDFUL_DELAYS] = json.toString()
        }
    }

    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { prefs -> prefs[KEY_THEME] = theme.name }
    }

    suspend fun setFontStyle(fontStyle: FontStyle) {
        context.dataStore.edit { prefs -> prefs[KEY_FONT_STYLE] = fontStyle.name }
    }

    suspend fun setTextTransform(transform: TextTransform) {
        context.dataStore.edit { prefs -> prefs[KEY_TEXT_TRANSFORM] = transform.name }
    }

    suspend fun setIconStyle(style: IconStyle) {
        context.dataStore.edit { prefs -> prefs[KEY_ICON_STYLE] = style.name }
    }

    suspend fun setShowScreenTime(show: Boolean) {
        context.dataStore.edit { prefs -> prefs[KEY_SHOW_SCREEN_TIME] = show }
    }

    suspend fun setWallpaperDim(dimLevel: Float) {
        context.dataStore.edit { prefs -> prefs[KEY_WALLPAPER_DIM] = dimLevel }
    }

    suspend fun setSwipeRightPackage(packageName: String?) {
        context.dataStore.edit { prefs ->
            if (packageName == null) prefs.remove(KEY_SWIPE_RIGHT_PKG)
            else prefs[KEY_SWIPE_RIGHT_PKG] = packageName
        }
    }

    suspend fun setSwipeLeftPackage(packageName: String?) {
        context.dataStore.edit { prefs ->
            if (packageName == null) prefs.remove(KEY_SWIPE_LEFT_PKG)
            else prefs[KEY_SWIPE_LEFT_PKG] = packageName
        }
    }

    suspend fun setMaxFavorites(count: Int) {
        context.dataStore.edit { prefs -> prefs[KEY_MAX_FAVORITES] = count }
    }

    suspend fun setPaymentApp(packageName: String) {
        context.dataStore.edit { prefs -> prefs[KEY_PAYMENT_APP] = packageName }
    }
}
