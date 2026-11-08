# Meos — Minimalist Focus Mobile OS & Launcher

A clean, distraction-free Android launcher and mobile environment built from the ground up with **Kotlin** and **Jetpack Compose (Material 3)**, featuring an innovative **Liquid Morphism** theme with 3D water-droplet optical refraction.

Designed to eliminate digital noise, reduce screen addiction, and encourage intentional phone usage through pure typography, calm atmospheres, and mindful breathing pauses.

---

## 🌊 Liquid Morphism & Design Philosophy

Minimalist Focus Launcher departs from conventional solid rectangular cards and heavy icon grids in favor of an organic, transparent **liquid lens**:

- **3D Water Droplet Refraction**: The clock and typography are completely transparent, magnifying and curving the background wallpaper directly through the letterforms like physical water drops on glass.
- **Zero Boundary (No Cards, No Borders)**: Strictly no stroke lines, borders, or card containers. Text shape is defined purely by optical refraction and razor-sharp liquid meniscus caustics.
- **Longitudinally Elongated Numerals**: Ultra-tall condensed clock typography stretched along the vertical axis with expanded latitudinal width for an understated luxury feel.
- **Harmonious Geometric Spacing**: Mathematically calibrated negative space ensuring every element (date, clock, colon droplets, curated essentials, and liquid dock) sits in visual harmony.

---

## ⚡ Core Features

- **Minimalist Home Screen**:
  - Longitudinal water droplet clock with live hours, colon spheres, and minutes.
  - Centered date header with luxury tracking.
  - Curated pinned essentials list with uniform touch targets (40px) and clean 14px cadence (zero arrow clutter).
  - Frosted liquid bottom dock with **`all apps`** trigger, quick phone dialer, and instant **UPI QR Scan to Pay** button.
- **UPI QR Scanner Action**:
  - Dedicated scan icon in home dock directly triggers payments via UPI (`upi://pay`, Google Pay, PhonePe, Paytm, BHIM) or system camera code scanner.
- **Mindful Delay Interceptor**:
  - Optional user-configured breathing pauses before opening distracting apps.
  - Completely unpolluted typography: no stars or timer badges are attached to app names.
- **All-Apps Drawer (Card-Free)**:
  - Clean typographic list with matching water droplet refraction and zero card wrappers.
  - Instant keystroke search filter.
  - Interactive A-Z alphabet scrubber for rapid navigation.
  - Relocated Launcher Settings button in drawer footer alongside `‹ home`.
- **Context Management (Long Press)**:
  - Pin to / Remove from Favorites.
  - Custom app renaming.
  - Hide apps into a private vault.
  - Configure Mindful Delay timer.
- **Atmosphere Switcher**:
  - 🏔️ **Alpine Sky**: Crisp snowy peaks & azure sky with refractive sunlight.
  - 👔 **Pinstripe**: Editorial cream canvas with dark navy pinstripes refracting through liquid numerals.
  - 🏛️ **Neoclassical**: Marble architecture refracting through clear water drops.
  - 🖤 **OLED Pure Black**: Absolute black (`#000000`) for maximum battery efficiency.
- **100% Privacy & Store Compliant**:
  - Operates completely offline with zero telemetry, trackers, or advertising SDKs.
  - Complies with Google Play Store User Data Policy and permissions guidelines.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin 2.0+
- **UI Toolkit**: Jetpack Compose + Material 3
- **Architecture**: MVVM with Unidirectional Data Flow (UDF)
- **State Management**: Kotlin Coroutines & `StateFlow`
- **Persistence**: Jetpack DataStore Preferences
- **Target SDK**: Android 15 (API 35) / **Minimum SDK**: Android 8.0 (API 26)

---

## 📦 Project Structure

```
Mobile OS/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/priyanuj/minimalist/
│   │   │   ├── MainActivity.kt
│   │   │   ├── model/AppModel.kt
│   │   │   ├── data/
│   │   │   │   ├── AppPreferences.kt
│   │   │   │   └── AppRepository.kt
│   │   │   ├── viewmodel/LauncherViewModel.kt
│   │   │   └── ui/
│   │   │       ├── components/
│   │   │       │   ├── AlphabetScroller.kt
│   │   │       │   ├── AppContextMenu.kt
│   │   │       │   ├── AppTextItem.kt
│   │   │       │   └── MindfulDelayDialog.kt
│   │   │       ├── screens/
│   │   │       │   ├── LiquidHomeScreen.kt
│   │   │       │   ├── AppDrawerScreen.kt
│   │   │       │   ├── SettingsScreen.kt
│   │   │       │   └── HiddenAppsScreen.kt
│   │   │       └── theme/
│   │   │           ├── Color.kt
│   │   │           ├── LiquidMorphism.kt
│   │   │           ├── Theme.kt
│   │   │           └── Type.kt
│   │   └── res/
│   │       ├── drawable/ic_launcher_foreground.xml
│   │       ├── mipmap-*/
│   │       └── values/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── simulator.html            # Standalone interactive mobile chassis simulator
├── build_liquid.py           # Simulator compiler script
├── PRIVACY_POLICY.md         # Play Store offline privacy policy
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew.bat
```

---

## 🚀 Building & Installing

### Prerequisites
- JDK 17+
- Android SDK (API 35, Build-Tools 34.0.0+)

### Compile Debug APK
```bash
./gradlew assembleDebug
```
The output APK is generated at:
`app/build/outputs/apk/debug/app-debug.apk`

### Install to Device via ADB
Connect your Android phone with **USB Debugging** enabled:
```bash
adb install -r "app/build/outputs/apk/debug/app-debug.apk"
```

### Preview in Browser
Open `simulator.html` directly in any web browser to interact with the mobile chassis preview, toggle atmosphere wallpapers, test search, and experience the mindful delay timer.
