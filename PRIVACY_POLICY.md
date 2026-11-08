# Privacy Policy for Minimalist Focus Launcher

**Last Updated:** November 8, 2026

Welcome to **Minimalist Focus Launcher** ("the App", "we", "us", or "our"). We are committed to protecting your personal privacy. This Privacy Policy explains our practices regarding user data, permissions, and security.

---

## 1. Zero Data Collection Principle

**Minimalist Focus Launcher does not collect, transmit, store, or sell any personal data.**

- **No Personal Information:** We do not collect names, email addresses, phone numbers, contacts, locations, or hardware identifiers.
- **No Analytics or Trackers:** The App contains zero third-party telemetry, tracking SDKs, advertising networks, or analytics frameworks (e.g., no Firebase Analytics, no Facebook SDK, no Google AdMob).
- **No Remote Servers:** The App operates entirely offline. It does not communicate with external servers or transmit background telemetry.

---

## 2. On-Device Storage & Local Processing

All data managed by Minimalist Focus Launcher is stored strictly locally on your device using Android Jetpack DataStore:

- **Favorite / Pinned Apps:** Stored locally so your chosen home screen apps remain persistent.
- **Hidden Apps & Renamed Labels:** Stored on-device to support your personal organization preferences.
- **Mindful Delay Configurations:** Settings for breathing pause timers (5s, 15s) remain on your local storage.
- **Atmosphere Themes:** Selected visual themes and color settings are preserved strictly within device storage.

If you uninstall the App or clear its application data through Android system settings, all locally stored preferences are permanently deleted.

---

## 3. Permissions & Device Access Transparency

Minimalist Focus Launcher requests only the minimum permissions necessary to function as an Android home screen replacement:

| Permission | Purpose | Data Handling |
| :--- | :--- | :--- |
| `QUERY_ALL_PACKAGES` | Required to discover installed applications on your device to populate the minimalist App Drawer so you can launch and manage your apps. | Processed entirely in memory on your device. Never logged, exported, or transmitted. |
| `SET_WALLPAPER` | Allows you to apply launcher atmosphere backgrounds directly from launcher settings if desired. | Operates purely via Android's local WallpaperManager. |
| `PACKAGE_USAGE_STATS` (Optional) | Used solely to display optional on-device screen time or unlock counts if you explicitly enable mindful usage monitoring. | Never leaves your device. |

---

## 4. Google Play Store Compliance

This application strictly complies with:
- The **Google Play Developer Program Policies**.
- Google Play's **User Data Policy**.
- Google Play's **High Risk or Sensitive Permissions Policy** regarding launcher core functionality.

---

## 5. Children's Privacy (COPPA)

Because Minimalist Focus Launcher does not collect any personal data from any user, it is fully safe for all ages and complies with the Children's Online Privacy Protection Act (COPPA) and GDPR regulations.

---

## 6. Open Source & Clean-Room Integrity

Minimalist Focus Launcher is built from scratch as a 100% clean-room native Android application using Kotlin, Jetpack Compose, and Material 3 design libraries.

---

## 7. Contact Us

If you have any questions or feedback regarding this Privacy Policy, please open an issue in the project repository or reach out via your project administration contact.

