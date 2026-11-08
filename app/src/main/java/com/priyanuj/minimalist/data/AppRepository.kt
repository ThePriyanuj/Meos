package com.priyanuj.minimalist.data

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.Settings
import com.priyanuj.minimalist.model.AppModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class AppRepository(
    private val context: Context,
    private val preferences: AppPreferences
) {
    private val packageManager: PackageManager = context.packageManager

    suspend fun queryInstalledApps(): List<AppModel> = withContext(Dispatchers.IO) {
        val launcherIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfos = packageManager.queryIntentActivities(launcherIntent, 0)
        val ownPackage = context.packageName

        resolveInfos
            .filter { it.activityInfo.packageName != ownPackage }
            .map { resolveInfo ->
                val pkgName = resolveInfo.activityInfo.packageName
                val actName = resolveInfo.activityInfo.name
                val label = resolveInfo.loadLabel(packageManager).toString()

                AppModel(
                    packageName = pkgName,
                    activityName = actName,
                    originalLabel = label
                )
            }
            .distinctBy { it.packageName }
    }

    fun observeApps(): Flow<List<AppModel>> {
        return combine(
            preferences.favoritesFlow,
            preferences.hiddenFlow,
            preferences.customNamesFlow,
            preferences.mindfulDelaysFlow
        ) { favorites, hidden, customNames, mindfulDelays ->
            val installed = queryInstalledApps()
            installed.map { app ->
                app.copy(
                    isFavorite = favorites.contains(app.packageName),
                    isHidden = hidden.contains(app.packageName),
                    customLabel = customNames[app.packageName],
                    mindfulDelaySeconds = mindfulDelays[app.packageName] ?: 0
                )
            }.sortedBy { it.displayName.lowercase() }
        }
    }

    fun launchApp(app: AppModel) {
        val launchIntent = packageManager.getLaunchIntentForPackage(app.packageName)
        if (launchIntent != null) {
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(launchIntent)
        }
    }

    fun openAppInfo(packageName: String) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun requestUninstall(packageName: String) {
        val intent = Intent(Intent.ACTION_DELETE).apply {
            data = Uri.fromParts("package", packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    fun openClock() {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun openCalendar() {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_APP_CALENDAR)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent)
        } else {
            val fallback = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("content://com.android.calendar/time")
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            if (fallback.resolveActivity(packageManager) != null) {
                context.startActivity(fallback)
            }
        }
    }

    fun openDialer() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent)
        }
    }

    fun openCamera() {
        val intent = Intent(android.provider.MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent)
        }
    }
        fun openPaymentScanner(targetPackage: String? = null) {
        val pkg = targetPackage ?: "com.google.android.apps.nbu.paisa.user"

        if (pkg == "system") {
            val upiIntent = Intent(Intent.ACTION_VIEW, Uri.parse("upi://pay")).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            if (upiIntent.resolveActivity(packageManager) != null) {
                context.startActivity(upiIntent)
                return
            }
        } else {
            // Direct scanner intents for popular apps
            val specificIntent = when (pkg) {
                "com.phonepe.app" -> Intent("com.phonepe.app.android.intent.action.QR_SCAN").apply {
                    setPackage("com.phonepe.app")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                "net.one97.paytm" -> Intent(Intent.ACTION_VIEW, Uri.parse("paytmmp://pay")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                else -> null
            }

            if (specificIntent != null && specificIntent.resolveActivity(packageManager) != null) {
                context.startActivity(specificIntent)
                return
            }

            // Direct package launch for chosen app
            val launch = packageManager.getLaunchIntentForPackage(pkg)
            if (launch != null) {
                launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(launch)
                return
            }
        }

        // Fallback: standard upi pay intent or camera
        val fallbackUpi = Intent(Intent.ACTION_VIEW, Uri.parse("upi://pay")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (fallbackUpi.resolveActivity(packageManager) != null) {
            context.startActivity(fallbackUpi)
        } else {
            openCamera()
        }
    }
}
