# 🎈 BalloonPop — Build Instructions

## Project Structure

```
balloonpop/
├── index.html          ← The game
├── manifest.json       ← PWA manifest
├── sw.js               ← Service worker (offline support)
├── www/                ← Web assets copied here for Capacitor
├── icons/              ← App icons (SVG, all sizes)
├── android/            ← Android project (open in Android Studio)
├── ios/                ← iOS project (open in Xcode)
└── capacitor.config.json
```

---

## Option 1 — PWA (Easiest, works on Android & iOS today)

No app store needed. Users install directly from their browser.

### How to host it
1. Upload the contents of the `balloonpop/` folder to any web host:
   - **Free options:** GitHub Pages, Netlify, Vercel
   - Example (Netlify): drag the folder to [app.netlify.com/drop](https://app.netlify.com/drop)

2. Open the URL in **Chrome (Android)** or **Safari (iOS)**

3. **Android** → tap the browser menu → "Add to Home Screen" → "Install"

4. **iOS** → tap the Share button → "Add to Home Screen"

The game will appear as a full-screen app icon. It also works offline.

---

## Option 2 — Native Android APK

### Requirements
- [Android Studio](https://developer.android.com/studio) (free)
- Android SDK (installed via Android Studio)
- Java 17+

### Steps

1. Open Android Studio → **File → Open** → select the `balloonpop/android/` folder

2. Wait for Gradle sync to complete (first time takes a few minutes)

3. **Run on a device/emulator:**
   - Plug in an Android device with USB debugging enabled, OR
   - Use AVD Manager to create an emulator
   - Click the ▶ **Run** button

4. **Build a release APK:**
   ```
   Build → Generate Signed Bundle / APK → APK
   ```
   - Create a keystore (first time) or use an existing one
   - Choose `release` build variant
   - The APK will be in `android/app/release/app-release.apk`

5. **Install directly on a device:**
   ```bash
   adb install android/app/release/app-release.apk
   ```

### Publish to Google Play
- Sign up at [play.google.com/console](https://play.google.com/console) ($25 one-time fee)
- Upload the signed APK or AAB (Android App Bundle)

---

## Option 3 — Native iOS IPA

### Requirements
- A **Mac** running macOS 13 or later
- [Xcode](https://apps.apple.com/app/xcode/id497799835) 15+ (free from App Store)
- Apple Developer account — [developer.apple.com](https://developer.apple.com) ($99/year for App Store distribution; free for personal device testing)

### Steps

1. Copy the entire `balloonpop/` folder to your Mac

2. Open Terminal on the Mac and run:
   ```bash
   cd balloonpop
   npm install
   npx cap sync ios
   ```

3. Open the Xcode project:
   ```bash
   npx cap open ios
   ```
   Or manually: open `ios/App/App.xcworkspace` in Xcode

4. In Xcode:
   - Select your Apple account in **Signing & Capabilities**
   - Set **Bundle Identifier** to `com.balloonpop.app`
   - Select your device or simulator at the top
   - Click ▶ **Run**

5. **Build for App Store:**
   - `Product → Archive`
   - Then **Distribute App → App Store Connect**

### Publish to Apple App Store
- Enrol at [developer.apple.com/programs](https://developer.apple.com/programs)
- Upload via Xcode or [Transporter](https://apps.apple.com/app/transporter/id1450874784)

---

## Updating the game

After editing `index.html`:

```bash
# Copy updated files to www/
cp index.html www/

# Sync to Android
npx cap sync android

# Sync to iOS (on Mac)
npx cap sync ios
```

---

## App Details

| Field | Value |
|---|---|
| App Name | BalloonPop |
| Bundle ID | com.balloonpop.app |
| Version | 1.0.0 |
| Min Android | API 22 (Android 5.0) |
| Min iOS | iOS 13 |
| Orientation | Portrait |

---

## Quick Test Checklist

- [ ] Settings screen shows on launch
- [ ] Capital letters mode: A→Z pops in order
- [ ] Small letters mode: a→z pops in order
- [ ] Numbers mode: custom range pops in order
- [ ] Wrong tap loses a life
- [ ] Escaped balloon loses a life
- [ ] Perfect batch plays clap + kids cheer
- [ ] Game over when 3 lives lost
- [ ] Win screen only when all balloons popped
- [ ] Game works offline (PWA)
