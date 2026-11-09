package com.priyanuj.minimalist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.priyanuj.minimalist.data.AppPreferences
import com.priyanuj.minimalist.data.AppRepository
import com.priyanuj.minimalist.data.AppTheme
import com.priyanuj.minimalist.model.AppModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
    HOME,
    DRAWER,
    SETTINGS,
    HIDDEN_APPS
}

class LauncherViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = AppPreferences(application)
    val repository = AppRepository(application, preferences)

    val currentScreen = MutableStateFlow(Screen.HOME)
    val searchQuery = MutableStateFlow("")

    val activeMindfulApp = MutableStateFlow<AppModel?>(null)
    val selectedAppForActions = MutableStateFlow<AppModel?>(null)

    val themeState: StateFlow<AppTheme> = preferences.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AppTheme.OLED_BLACK
    )

    val paymentApp: StateFlow<String> = preferences.paymentAppFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "com.google.android.apps.nbu.paisa.user"
    )

    val fontColor: StateFlow<com.priyanuj.minimalist.data.FontColor> = preferences.fontColorFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = com.priyanuj.minimalist.data.FontColor.WHITE
    )

    private val allApps: StateFlow<List<AppModel>> = repository.observeApps().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val visibleApps: StateFlow<List<AppModel>> = allApps.combine(searchQuery) { apps, query ->
        val notHidden = apps.filter { !it.isHidden }
        if (query.isBlank()) {
            notHidden
        } else {
            val q = query.trim().lowercase()
            notHidden.filter { it.displayName.lowercase().contains(q) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val favoriteApps: StateFlow<List<AppModel>> = allApps.combine(searchQuery) { apps, _ ->
        apps.filter { it.isFavorite && !it.isHidden }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val hiddenApps: StateFlow<List<AppModel>> = allApps.combine(searchQuery) { apps, _ ->
        apps.filter { it.isHidden }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onAppClick(app: AppModel) {
        if (app.mindfulDelaySeconds > 0) {
            activeMindfulApp.value = app
        } else {
            repository.launchApp(app)
        }
    }

    fun confirmMindfulOpen() {
        val app = activeMindfulApp.value
        if (app != null) {
            activeMindfulApp.value = null
            repository.launchApp(app)
        }
    }

    fun cancelMindfulOpen() {
        activeMindfulApp.value = null
    }

    fun toggleFavorite(app: AppModel) {
        viewModelScope.launch {
            preferences.setFavorite(app.packageName, !app.isFavorite)
        }
    }

    fun hideApp(app: AppModel) {
        viewModelScope.launch {
            preferences.setHidden(app.packageName, true)
        }
    }

    fun unhideApp(app: AppModel) {
        viewModelScope.launch {
            preferences.setHidden(app.packageName, false)
        }
    }

    fun renameApp(app: AppModel, newName: String) {
        viewModelScope.launch {
            preferences.setCustomName(app.packageName, newName)
        }
    }

    fun setMindfulDelay(app: AppModel, seconds: Int) {
        viewModelScope.launch {
            preferences.setMindfulDelay(app.packageName, seconds)
        }
    }

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            preferences.setTheme(theme)
        }
    }

    fun setPaymentApp(packageName: String) {
        viewModelScope.launch {
            preferences.setPaymentApp(packageName)
        }
    }

    fun setFontColor(color: com.priyanuj.minimalist.data.FontColor) {
        viewModelScope.launch {
            preferences.setFontColor(color)
        }
    }
}

