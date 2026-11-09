package com.priyanuj.minimalist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.priyanuj.minimalist.data.AppTheme
import com.priyanuj.minimalist.ui.components.AppActionBottomSheet
import com.priyanuj.minimalist.ui.components.MindfulDelayDialog
import com.priyanuj.minimalist.ui.screens.AppDrawerScreen
import com.priyanuj.minimalist.ui.screens.HiddenAppsScreen
import com.priyanuj.minimalist.ui.screens.HomeScreen
import com.priyanuj.minimalist.ui.screens.LiquidHomeScreen
import com.priyanuj.minimalist.ui.screens.SettingsScreen
import com.priyanuj.minimalist.ui.theme.MinimalistLauncherTheme
import com.priyanuj.minimalist.viewmodel.LauncherViewModel
import com.priyanuj.minimalist.viewmodel.Screen

class MainActivity : ComponentActivity() {

    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val theme by viewModel.themeState.collectAsState()
            val fontColor by viewModel.fontColor.collectAsState()
            val paymentApp by viewModel.paymentApp.collectAsState()
            val currentScreen by viewModel.currentScreen.collectAsState()
            val favoriteApps by viewModel.favoriteApps.collectAsState()
            val visibleApps by viewModel.visibleApps.collectAsState()
            val hiddenApps by viewModel.hiddenApps.collectAsState()
            val searchQuery by viewModel.searchQuery.collectAsState()
            val activeMindfulApp by viewModel.activeMindfulApp.collectAsState()
            val selectedAppForActions by viewModel.selectedAppForActions.collectAsState()

            // Handle Back button for Launcher navigation
            BackHandler(enabled = currentScreen != Screen.HOME) {
                if (currentScreen == Screen.HIDDEN_APPS) {
                    viewModel.currentScreen.value = Screen.SETTINGS
                } else {
                    viewModel.searchQuery.value = ""
                    viewModel.currentScreen.value = Screen.HOME
                }
            }

            MinimalistLauncherTheme(appTheme = theme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AnimatedContent(
                        targetState = currentScreen,
                        label = "ScreenTransition",
                        transitionSpec = {
                            if (initialState == Screen.HOME && targetState == Screen.DRAWER) {
                                // Unified Upward Momentum: One continuous movement ascending into drawer
                                (slideInVertically(animationSpec = tween(400, easing = FastOutSlowInEasing)) { (it * 0.70f).toInt() } +
                                        fadeIn(animationSpec = tween(280)))
                                    .togetherWith(
                                        slideOutVertically(animationSpec = tween(380, easing = FastOutSlowInEasing)) { -(it * 0.25f).toInt() } +
                                                fadeOut(animationSpec = tween(220))
                                    )
                            } else if (initialState == Screen.DRAWER && targetState == Screen.HOME) {
                                // Unified Downward Momentum: One continuous movement descending into home dock
                                (slideInVertically(animationSpec = tween(400, easing = FastOutSlowInEasing)) { -(it * 0.25f).toInt() } +
                                        fadeIn(animationSpec = tween(280)))
                                    .togetherWith(
                                        slideOutVertically(animationSpec = tween(380, easing = FastOutSlowInEasing)) { (it * 0.70f).toInt() } +
                                                fadeOut(animationSpec = tween(220))
                                    )
                            } else {
                                fadeIn(animationSpec = tween(220))
                                    .togetherWith(fadeOut(animationSpec = tween(180)))
                            }
                        }
                    ) { target ->
                        when (target) {
                            Screen.HOME -> {
                                if (theme == AppTheme.LIQUID_MORPHISM) {
                                    LiquidHomeScreen(
                                        favoriteApps = favoriteApps,
                                        onAppClick = { viewModel.onAppClick(it) },
                                        onAppLongClick = { viewModel.selectedAppForActions.value = it },
                                        onWeatherClick = { viewModel.repository.openClock() },
                                        onCalendarClick = { viewModel.repository.openCalendar() },
                                        onOpenDrawer = { viewModel.currentScreen.value = Screen.DRAWER },
                                        onUpiScanClick = { viewModel.repository.openPaymentScanner(paymentApp) },
                                        onQuickDial = { viewModel.repository.openDialer() },
                                        fontColor = fontColor
                                    )
                                } else {
                                    HomeScreen(
                                        favoriteApps = favoriteApps,
                                        onAppClick = { viewModel.onAppClick(it) },
                                        onAppLongClick = { viewModel.selectedAppForActions.value = it },
                                        onTimeClick = { viewModel.repository.openClock() },
                                        onDateClick = { viewModel.repository.openCalendar() },
                                        onOpenDrawer = { viewModel.currentScreen.value = Screen.DRAWER },
                                        onOpenSettings = { viewModel.currentScreen.value = Screen.SETTINGS },
                                        onSwipeRight = { viewModel.repository.openDialer() },
                                        onSwipeLeft = { viewModel.repository.openCamera() }
                                    )
                                }
                            }

                            Screen.DRAWER -> AppDrawerScreen(
                                apps = visibleApps,
                                searchQuery = searchQuery,
                                onSearchQueryChange = { viewModel.searchQuery.value = it },
                                onAppClick = { viewModel.onAppClick(it) },
                                onAppLongClick = { viewModel.selectedAppForActions.value = it },
                                onBack = {
                                    viewModel.searchQuery.value = ""
                                    viewModel.currentScreen.value = Screen.HOME
                                },
                                onOpenSettings = { viewModel.currentScreen.value = Screen.SETTINGS }
                            )

                            Screen.SETTINGS -> SettingsScreen(
                                currentTheme = theme,
                                onThemeSelect = { viewModel.setTheme(it) },
                                fontColor = fontColor,
                                onFontColorSelect = { viewModel.setFontColor(it) },
                                selectedPaymentApp = paymentApp,
                                onPaymentAppSelect = { viewModel.setPaymentApp(it) },
                                onOpenHiddenApps = { viewModel.currentScreen.value = Screen.HIDDEN_APPS },
                                onBack = { viewModel.currentScreen.value = Screen.HOME }
                            )

                            Screen.HIDDEN_APPS -> HiddenAppsScreen(
                                hiddenApps = hiddenApps,
                                onUnhideApp = { viewModel.unhideApp(it) },
                                onBack = { viewModel.currentScreen.value = Screen.SETTINGS }
                            )
                        }
                    }

                    // Mindful Delay Interceptor Dialog
                    activeMindfulApp?.let { app ->
                        MindfulDelayDialog(
                            app = app,
                            onConfirmOpen = { viewModel.confirmMindfulOpen() },
                            onCancel = { viewModel.cancelMindfulOpen() }
                        )
                    }

                    // Long-Press Action Sheet
                    selectedAppForActions?.let { app ->
                        AppActionBottomSheet(
                            app = app,
                            onDismiss = { viewModel.selectedAppForActions.value = null },
                            onToggleFavorite = { viewModel.toggleFavorite(app) },
                            onHideApp = { viewModel.hideApp(app) },
                            onRenameApp = { newName -> viewModel.renameApp(app, newName) },
                            onSetMindfulDelay = { delaySec -> viewModel.setMindfulDelay(app, delaySec) },
                            onOpenAppInfo = { viewModel.repository.openAppInfo(app.packageName) },
                            onRequestUninstall = { viewModel.repository.requestUninstall(app.packageName) }
                        )
                    }
                }
            }
        }
    }
}
