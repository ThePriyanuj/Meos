# Meos: Minimalist Launcher — Google Play Store Launch Kit

This guide contains everything prepared and required to launch **Meos: Minimalist Launcher** on the Google Play Store.

---

## 1. Production Release Assets Ready For Upload

| Asset | Location | Requirements Met |
| :--- | :--- | :--- |
| **Android App Bundle (.aab)** | `app-release.aab` ([Download / View](file:///d:/Projects/Mobile%20OS/app/build/outputs/bundle/release/app-release.aab)) | Minified with R8, target SDK 35, 1.04 MB |
| **App Icon** | `playstore_assets/icon_512.png` ([View](file:///d:/Projects/Mobile%20OS/playstore_assets/icon_512.png)) | 512 x 512 PNG, 32-bit color, no transparency |
| **Feature Graphic** | `playstore_assets/feature_graphic_1024x500.png` ([View](file:///d:/Projects/Mobile%20OS/playstore_assets/feature_graphic_1024x500.png)) | 1024 x 500 PNG, landscape promotional banner |
| **Screenshot 1 (Home)** | `playstore_assets/screenshot_1_home.png` | 1080 x 2400 Longitudinal Water Drop Clock |
| **Screenshot 2 (Drawer)** | `playstore_assets/screenshot_2_drawer.png` | 1080 x 2400 Minimalist App Drawer + Scrubber |
| **Screenshot 3 (Tasks)** | `playstore_assets/screenshot_3_tasks.png` | 1080 x 2400 Daily Focus To-Do (1-Day TTL) |
| **Screenshot 4 (Custom Wallpaper)** | `playstore_assets/screenshot_4_custom_wallpaper.png` | 1080 x 2400 Custom Gallery Wallpaper Refraction |
| **Screenshot 5 (Settings)** | `playstore_assets/screenshot_5_settings.png` | 1080 x 2400 Minimalist Theme & Color Harmony |
| **Release Keystore** | `release.keystore` ([View](file:///d:/Projects/Mobile%20OS/release.keystore)) | RSA 2048, 10,000 days validity |

### Signing Keystore Details
- **Keystore File**: `release.keystore`
- **Key Alias**: `meos-launcher`
- **Store Password**: `meoslauncher123`
- **Key Password**: `meoslauncher123`

---

## 2. Store Listing Metadata

### App Title (Max 30 characters)
```text
Meos: Minimalist Launcher
```

### Short Description (Max 80 characters)
```text
Focus-driven liquid glass launcher. Zero distraction, optical water refraction.
```

### Full Description (Up to 4,000 characters)
```text
Meos is a minimalist Android home launcher designed to eliminate digital noise, reclaim your focus, and bring calm optical elegance to your daily routine.

Inspired by pure optical water refraction, Meos replaces cluttered grids of addictive, colorful badges with razor-sharp typography and an ultra-tall longitudinal clock that seamlessly refracts your wallpaper.

FEATURES:

💧 Longitudinal Water Drop Clock
Ultra-tall, luxury-spaced numerals refracting your wallpaper with zero boundary lines and dynamic optical caustics.

⚡ Curated Essentials
Keep only your top 5 essentials on your home screen. Say goodbye to infinite home screen pages and notification distractions.

🔍 Unified Morphing App Drawer
A single fluid swipe up transitions your dock into an alphabetized drawer with a fast A-Z scrubber and instant search.

🧭 Intuitive Gestures
• Swipe Up: Open All Apps Drawer
• Swipe Left: Open Web Browser
• Swipe Right: Daily Focus To-Do List
• Double-Tap: Instant screen lock

⏱️ Daily Focus Tasks (1-Day TTL)
An intentional to-do list with a strict 24-hour Time-To-Live. Tasks expire naturally every day, encouraging presence without task backlog anxiety.

🧘 Mindful Delay
Add an optional 5s or 15s breathing pause before opening distracting apps to break unconscious habitual scrolling.

🎨 Dynamic Atmospheres & Custom Wallpaper
Select from curated tones (Alpine Sky, Obsidian Black, Alabaster White, Crimson Red, Amethyst Purple) or choose your personal photo from your gallery for real-time liquid refraction.

🔒 100% Private & Offline
Zero analytics. Zero tracking. Zero remote telemetry. All data and preferences remain strictly on your device.

Rediscover clarity and calm every time you unlock your phone.
```

---

## 3. Privacy Policy & Store Declarations

### Privacy Policy URL
Host `PRIVACY_POLICY.md` on your GitHub Pages or use:
```text
https://github.com/ThePriyanuj/Meos/blob/master/PRIVACY_POLICY.md
```

### Data Safety Form (in Google Play Console)
- **Does your app collect or share user data?**: Select **No**.
- **Data Collection**: No personal information, location, identifiers, or financial data collected.
- **Analytics / Ads**: No third-party SDKs or ads used.

### App Access & Sensitive Permissions Declaration
- **Permission**: `QUERY_ALL_PACKAGES`
- **Declaration Category**: **Launcher / Home screen replacement**
- **Justification Text**:
  ```text
  Meos is a full Android home screen replacement (launcher) with Intent CATEGORY_HOME. It requires QUERY_ALL_PACKAGES to index and display installed applications on the device in the user-facing App Drawer, enabling users to launch, search, hide, and manage their apps.
  ```

---

## 4. Step-by-Step Play Console Publishing Instructions

1. **Sign in to Google Play Console**:
   Go to [https://play.google.com/console](https://play.google.com/console) and sign in with your Google account.
   *(If you don't have a developer account yet, complete the one-time $25 registration).*

2. **Create New App**:
   - Click **Create app**.
   - **App name**: `Meos: Minimalist Launcher`
   - **Default language**: English (United States)
   - **App or Game**: App
   - **Free or Paid**: Free
   - Accept the Developer Program Policies and click **Create app**.

3. **Set Up Store Listing**:
   - Navigate to **Grow** > **Store presence** > **Main store listing**.
   - Paste the **Short description** and **Full description** from Section 2 above.
   - Upload `playstore_assets/icon_512.png` as the **App icon**.
   - Upload `playstore_assets/feature_graphic_1024x500.png` as the **Feature graphic**.
   - Upload the screenshots from `playstore_assets/` under **Phone screenshots**.
   - Click **Save**.

4. **Complete Policy & App Content Tasks**:
   Go to **Policy and programs** > **App content**:
   - **Privacy Policy**: Paste your privacy policy link.
   - **Ads**: Select "No, my app does not contain ads".
   - **App access**: Select "All functionality is available without special access".
   - **Content rating**: Complete the questionnaire (Ratings: All ages / Everyone).
   - **Target audience**: Select 18 and over (or 13+).
   - **Data Safety**: Complete questionnaire stating NO data is collected or shared.
   - **Sensitive Permissions**: Fill the launcher declaration for `QUERY_ALL_PACKAGES`.

5. **Upload Production Release**:
   - Navigate to **Release** > **Production** (or **Closed testing** for accounts subject to the 14-day tester requirement).
   - Click **Create new release**.
   - Drag and drop `app-release.aab` from `app/build/outputs/bundle/release/app-release.aab`.
   - **Release name**: `1.0.0`
   - **Release notes**: `Initial release of Meos: Minimalist Focus Launcher.`
   - Click **Next** > **Review release** > **Start rollout to Production**!
