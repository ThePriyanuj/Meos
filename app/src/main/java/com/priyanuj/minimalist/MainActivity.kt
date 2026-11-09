package com.priyanuj.minimalist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.Settings
import android.view.KeyEvent
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    private lateinit var webView: WebView
    private var filePathCallback: ValueCallback<Array<Uri>>? = null

    private val filePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            filePathCallback?.onReceiveValue(arrayOf(uri))
            try {
                contentResolver.openInputStream(uri)?.use { stream ->
                    val bytes = stream.readBytes()
                    val b64 = android.util.Base64.encodeToString(bytes, android.util.Base64.NO_WRAP)
                    val mimeType = contentResolver.getType(uri) ?: "image/jpeg"
                    val dataUri = "data:$mimeType;base64,$b64"
                    webView.post {
                        webView.evaluateJavascript("applyCustomWallpaper('$dataUri')", null)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            filePathCallback?.onReceiveValue(null)
        }
        filePathCallback = null
    }

    fun openGalleryPicker() {
        filePickerLauncher.launch("image/*")
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Make edge-to-edge transparent
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isStatusBarContrastEnforced = false
            window.isNavigationBarContrastEnforced = false
        }

        webView = WebView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.TRANSPARENT)
            setLayerType(View.LAYER_TYPE_HARDWARE, null)

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                allowFileAccess = true
                allowContentAccess = true
                cacheMode = WebSettings.LOAD_DEFAULT
                useWideViewPort = true
                loadWithOverviewMode = true
                mediaPlaybackRequiresUserGesture = false
            }

            addJavascriptInterface(AndroidBridge(this@MainActivity, this), "Android")

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    applyInsets()
                    view?.evaluateJavascript("if (typeof initNativeApps === 'function') initNativeApps();", null)
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    wv: WebView?,
                    callback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {
                    filePathCallback?.onReceiveValue(null)
                    filePathCallback = callback
                    openGalleryPicker()
                    return true
                }
            }
        }

        setContentView(webView)

        // Listen for window insets to deliver exact status bar and navigation bar padding
        ViewCompat.setOnApplyWindowInsetsListener(webView) { _, insets ->
            applyInsets(insets)
            insets
        }

        // Intercept Android Back button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                webView.evaluateJavascript("if (typeof handleBackButton === 'function') handleBackButton(); else false;") { _ ->
                    // Launcher stays on home screen
                }
            }
        })

        // Load the local launcher web app
        webView.loadUrl("file:///android_asset/index.html")
    }

    private fun applyInsets(insets: WindowInsetsCompat? = null) {
        val currentInsets = insets ?: ViewCompat.getRootWindowInsets(webView) ?: return
        val statusBars = currentInsets.getInsets(WindowInsetsCompat.Type.statusBars())
        val navBars = currentInsets.getInsets(WindowInsetsCompat.Type.navigationBars())

        val density = resources.displayMetrics.density
        val topDp = (statusBars.top / density).toInt()
        val bottomDp = (navBars.bottom / density).toInt()

        val safeTop = Math.max(topDp, 38)
        val safeBottomBanner = Math.max(bottomDp + 16, 24)
        val safeBottomDock = Math.max(bottomDp + 76, 84)

        val js = """
            document.documentElement.style.setProperty('--safe-top', '${safeTop}px');
            document.documentElement.style.setProperty('--safe-bottom-banner', '${safeBottomBanner}px');
            document.documentElement.style.setProperty('--safe-bottom-dock', '${safeBottomDock}px');
        """.trimIndent()
        webView.evaluateJavascript(js, null)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            webView.evaluateJavascript("if (typeof handleBackButton === 'function') handleBackButton(); else false;") { _ -> }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onResume() {
        super.onResume()
        if (::webView.isInitialized) {
            webView.evaluateJavascript("if (typeof updateClock === 'function') updateClock();", null)
        }
    }

    inner class AndroidBridge(private val context: Context, private val webView: WebView) {

        @JavascriptInterface
        fun launchApp(packageName: String) {
            try {
                val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
                if (launchIntent != null) {
                    launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(launchIntent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JavascriptInterface
        fun getInstalledApps(): String {
            return try {
                val pm = context.packageManager
                val intent = Intent(Intent.ACTION_MAIN, null).apply {
                    addCategory(Intent.CATEGORY_LAUNCHER)
                }
                val activities = pm.queryIntentActivities(intent, 0)
                val ownPackage = context.packageName
                val jsonArray = JSONArray()

                activities
                    .filter { it.activityInfo.packageName != ownPackage }
                    .distinctBy { it.activityInfo.packageName }
                    .sortedBy { it.loadLabel(pm).toString().lowercase() }
                    .forEach { resolveInfo ->
                        val obj = JSONObject().apply {
                            put("id", resolveInfo.activityInfo.packageName)
                            put("name", resolveInfo.loadLabel(pm).toString())
                            put("fav", false)
                            put("hidden", false)
                            put("delay", 0)
                        }
                        jsonArray.put(obj)
                    }
                jsonArray.toString()
            } catch (e: Exception) {
                "[]"
            }
        }

        @JavascriptInterface
        fun openDialer() {
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JavascriptInterface
        fun openClock() {
            try {
                val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JavascriptInterface
        fun openDeviceSettings() {
            try {
                val intent = Intent(Settings.ACTION_SETTINGS).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JavascriptInterface
        fun openUpiScanner(target: String?) {
            try {
                val pkg = target ?: "gpay"
                val targetPackage = when (pkg) {
                    "gpay" -> "com.google.android.apps.nbu.paisa.user"
                    "phonepe" -> "com.phonepe.app"
                    "paytm" -> "net.one97.paytm"
                    "bhim" -> "in.org.npci.upiapp"
                    else -> null
                }

                if (targetPackage == "com.phonepe.app") {
                    val specific = Intent("com.phonepe.app.android.intent.action.QR_SCAN").apply {
                        setPackage("com.phonepe.app")
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    if (specific.resolveActivity(context.packageManager) != null) {
                        context.startActivity(specific)
                        return
                    }
                } else if (targetPackage == "net.one97.paytm") {
                    val specific = Intent(Intent.ACTION_VIEW, Uri.parse("paytmmp://pay")).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    if (specific.resolveActivity(context.packageManager) != null) {
                        context.startActivity(specific)
                        return
                    }
                }

                if (targetPackage != null) {
                    val launch = context.packageManager.getLaunchIntentForPackage(targetPackage)
                    if (launch != null) {
                        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(launch)
                        return
                    }
                }

                val fallback = Intent(Intent.ACTION_VIEW, Uri.parse("upi://pay")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                if (fallback.resolveActivity(context.packageManager) != null) {
                    context.startActivity(fallback)
                } else {
                    val camera = Intent(android.provider.MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    if (camera.resolveActivity(context.packageManager) != null) {
                        context.startActivity(camera)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JavascriptInterface
        fun openGallery() {
            (context as? MainActivity)?.openGalleryPicker()
        }

        @JavascriptInterface
        fun openBrowser() {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com")).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
                return
            } catch (e: Exception) {
                val browsers = listOf(
                    "com.android.chrome",
                    "com.google.android.apps.chrome",
                    "com.brave.browser",
                    "org.mozilla.firefox",
                    "com.sec.android.app.sbrowser",
                    "com.heytap.browser",
                    "com.coloros.browser"
                )
                for (b in browsers) {
                    try {
                        val launch = context.packageManager.getLaunchIntentForPackage(b)
                        if (launch != null) {
                            launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(launch)
                            return
                        }
                    } catch (_: Exception) {}
                }
                e.printStackTrace()
            }
        }
    }
}
