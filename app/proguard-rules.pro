# Proguard rules for Minimalist Launcher
-keepattributes *Annotation*
-keepclassmembers class * {
    @org.jetbrains.annotations.Nullable *;
    @org.jetbrains.annotations.NotNull *;
}

