# Proguard rules for Minimalist Launcher
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.jetbrains.annotations.Nullable *;
    @org.jetbrains.annotations.NotNull *;
}

# Keep JavascriptInterface methods for Android WebView bridge
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keep class com.priyanuj.minimalist.MainActivity$AndroidBridge {
    *;
}


