# Meos: Minimalist Launcher — Samsung Galaxy Store Launch Kit

This guide contains everything you need to publish **Meos: Minimalist Launcher** on the **Samsung Galaxy Store**.

---

## 🌟 Why Samsung Galaxy Store is Ideal for Meos

| Feature | Samsung Galaxy Store | Google Play Store |
| :--- | :--- | :--- |
| **Registration Fee** | **$0 (100% Free)** | $25 USD one-time fee |
| **Mandatory 20 Testers** | **None! Direct to review** | Required: 20 testers for 14 continuous days |
| **Review Turnaround** | **2 – 4 business days** | 7+ days (often delayed by tester requirements) |
| **Target Audience** | **Hundreds of millions of Galaxy phones & tablets** | Android ecosystem |
| **Binary Formats Accepted** | **Both signed `.apk` and `.aab`** | `.aab` only |

---

## 1. Ready-to-Upload Production Binaries & Assets

All assets are already generated, signed, tested, and located in your project directory:

| Asset | Local File Link | Specs & Requirements |
| :--- | :--- | :--- |
| **Signed Release APK** | [app-release.apk](file:///d:/Projects/Mobile%20OS/app/build/outputs/apk/release/app-release.apk) | 808 KB, R8 minified, universal 64-bit/32-bit |
| **Signed Release Bundle** | [app-release.aab](file:///d:/Projects/Mobile%20OS/app/build/outputs/bundle/release/app-release.aab) | 1.04 MB, target SDK 35 (Android 15) |
| **Store App Icon** | [icon_512.png](file:///d:/Projects/Mobile%20OS/playstore_assets/icon_512.png) | 512 x 512 PNG, 32-bit color, no transparency |
| **Cover Graphic Banner** | [feature_graphic_1024x500.png](file:///d:/Projects/Mobile%20OS/playstore_assets/feature_graphic_1024x500.png) | 1024 x 500 PNG landscape header graphic |
| **Screenshot 1 (Home Screen)** | [screenshot_1_home.png](file:///d:/Projects/Mobile%20OS/playstore_assets/screenshot_1_home.png) | 1080 x 2400 Longitudinal Water-Drop Clock |
| **Screenshot 2 (App Drawer)** | [screenshot_2_drawer.png](file:///d:/Projects/Mobile%20OS/playstore_assets/screenshot_2_drawer.png) | 1080 x 2400 Minimalist App Drawer + Scrubber |
| **Screenshot 3 (Focus Tasks)** | [screenshot_3_tasks.png](file:///d:/Projects/Mobile%20OS/playstore_assets/screenshot_3_tasks.png) | 1080 x 2400 Daily Focus To-Do (1-Day TTL) |
| **Screenshot 4 (Custom Wallpaper)** | [screenshot_4_custom_wallpaper.png](file:///d:/Projects/Mobile%20OS/playstore_assets/screenshot_4_custom_wallpaper.png) | 1080 x 2400 Liquid Refraction with User Photo |
| **Screenshot 5 (Theme & Color)** | [screenshot_5_settings.png](file:///d:/Projects/Mobile%20OS/playstore_assets/screenshot_5_settings.png) | 1080 x 2400 Minimalist Color Harmony Settings |
| **Signing Keystore** | [release.keystore](file:///d:/Projects/Mobile%20OS/release.keystore) | RSA 2048, 10,000 days validity |

### Keystore Credentials (Keep Secure)
- **Keystore**: `release.keystore`
- **Key Alias**: `meos-launcher`
- **Store Password**: `meoslauncher123`
- **Key Password**: `meoslauncher123`

---

## 2. Store Listing Metadata (Copy & Paste)

### Application Title
```text
Meos: Minimalist Launcher
```

### Short Description / Tagline (Max 80 chars)
```text
Focus-driven liquid glass launcher. Zero distraction, optical water refraction.
```

### Full Description (Up to 4,000 characters)
```text
Meos is a minimalist Android home launcher designed to eliminate digital noise, reclaim your focus, and bring calm optical elegance to your daily routine.

Inspired by pure optical water refraction, Meos replaces cluttered grids of addictive, colorful badges with razor-sharp typography and an ultra-tall longitudinal clock that seamlessly refracts your wallpaper.

KEY FEATURES:

💧 Longitudinal Water Drop Clock
Ultra-tall, luxury-spaced numerals refracting your wallpaper with zero boundary lines and dynamic optical caustics.

⚡ Curated Essentials
Keep only your top 5 essentials on your home screen. Say goodbye to infinite home screen pages and notification distractions.

🔍 Unified Morphing App Drawer
A single fluid swipe up transitions your dock into an alphabetized drawer with a fast A-Z scrubber and instant search.

🧭 Intuitive Gestures
• Swipe Up: Open All Apps Drawer (swipe down to return home)
• Swipe Left: Instant Web Browser
• Swipe Right: Daily Focus To-Do List
• Double-Tap: Instant screen lock

⏱️ Daily Focus Tasks (1-Day TTL)
An intentional to-do list with a strict 24-hour Time-To-Live. Completed tasks expire naturally every day, encouraging presence without task backlog anxiety.

🧘 Mindful Delay
Add an optional 5s or 15s breathing pause before opening distracting apps to break unconscious habitual scrolling.

🎨 Dynamic Atmospheres & Custom Wallpaper
Select from curated tones (Alpine Sky, Obsidian Black, Alabaster White, Crimson Red, Amethyst Purple) or choose your personal photo from your gallery for real-time liquid refraction.

🔒 100% Private & Offline
Zero analytics. Zero tracking. Zero remote telemetry. All data and preferences remain strictly on your device.

Rediscover clarity and calm every time you unlock your phone.
```

---

## 3. Step-by-Step Samsung Galaxy Store Publishing Guide

### Step 1: Open Samsung Galaxy Store Seller Portal
1. Navigate to: **[https://seller.samsungapps.com](https://seller.samsungapps.com)**
2. Sign in with your Samsung Account (or create one for free).
3. Complete the Seller Profile as an **Individual** (100% free, no credit card or fee required).

### Step 2: Create New Application
1. In the Seller Portal dashboard, click **Add New Application**.
2. Select **Android**.
3. Choose your default language: **English (United States)**.

### Step 3: Enter App Information & Categorization
1. **Application Title**: `Meos: Minimalist Launcher`
2. **Category**:
   - Primary: **Personalization** (or **Themes / Utilities**)
3. **Age Rating**:
   - Select **3+** / **All Ages** (no violence, gambling, or adult content).
4. **Price & Country**:
   - Select **Free**.
   - Target Countries: Select **All** (or select specific regions).

### Step 4: Upload Store Graphics
In the **Images** section:
1. **App Icon**: Drag and drop `playstore_assets/icon_512.png` (512x512).
2. **Cover Image**: Drag and drop `playstore_assets/feature_graphic_1024x500.png` (1024x500).
3. **Screenshots (Phone)**: Drag and drop the 5 screenshots:
   - `screenshot_1_home.png`
   - `screenshot_2_drawer.png`
   - `screenshot_3_tasks.png`
   - `screenshot_4_custom_wallpaper.png`
   - `screenshot_5_settings.png`

### Step 5: Upload Binary
1. Go to the **Binary** tab.
2. Click **Add Binary**.
3. Upload `app-release.apk` (from `app/build/outputs/apk/release/app-release.apk` or artifact directory).
   *(Alternatively, Samsung also supports `app-release.aab`)*.
4. Samsung will automatically parse:
   - Package Name: `com.meos.launcher`
   - Version Code: `1`
   - Version: `1.0.0`
   - Min SDK: `26` (Android 8.0 Oreo)
   - Target SDK: `35` (Android 15)
5. Under Resolution / Device Support, select **Auto Detect** or check all supported Samsung Galaxy phones and tablets.

### Step 6: Privacy Policy & Support Contact
1. **Privacy Policy URL**:
   ```text
   https://github.com/ThePriyanuj/Meos/blob/master/PRIVACY_POLICY.md
   ```
2. **Support Email**: Enter your contact email.

### Step 7: Submit for Review
1. Review all sections (Green checkmarks will indicate completion).
2. Click the **Submit** button in the upper right.
3. Your app will enter **Under Review** status (typically approved within 2 to 4 business days).

