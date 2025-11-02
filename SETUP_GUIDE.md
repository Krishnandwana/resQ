# ResQHer - Complete Setup Guide

This guide will help you set up and run the ResQHer Women Safety Application on your development machine.

## Table of Contents
1. [System Requirements](#system-requirements)
2. [Software Installation](#software-installation)
3. [Project Setup](#project-setup)
4. [Google Maps API Configuration](#google-maps-api-configuration)
5. [Building the Project](#building-the-project)
6. [Running on Device](#running-on-device)
7. [Testing](#testing)
8. [Troubleshooting](#troubleshooting)

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10/11, macOS 10.14+, or Linux
- **RAM**: 8 GB (16 GB recommended)
- **Disk Space**: 10 GB free space
- **Processor**: Intel i5 or equivalent
- **Internet**: Required for initial setup and dependencies

### For Testing
- **Android Device**: Android 7.0 (API 24) or higher
- **OR Android Emulator**: With Google Play Services

## Software Installation

### 1. Install Java Development Kit (JDK)

**Download JDK 8 or higher:**
- Visit: https://www.oracle.com/java/technologies/downloads/
- Download JDK 11 or JDK 17 (recommended)

**Verify Installation:**
```bash
java -version
javac -version
```

### 2. Install Android Studio

**Download:**
- Visit: https://developer.android.com/studio
- Download latest stable version

**Installation Steps:**
1. Run the installer
2. Choose "Standard" installation
3. Select UI theme (optional)
4. Wait for SDK components to download
5. Click "Finish"

**Install Required SDK Components:**
1. Open Android Studio
2. Go to: Tools → SDK Manager
3. SDK Platforms tab:
   - Install Android 14.0 (API 34)
   - Install Android 7.0 (API 24)
4. SDK Tools tab:
   - Android SDK Build-Tools
   - Android SDK Platform-Tools
   - Android Emulator
   - Google Play Services

### 3. Configure Android Studio

**Set up Android SDK:**
1. File → Settings (Windows/Linux) or Preferences (Mac)
2. Appearance & Behavior → System Settings → Android SDK
3. Note the SDK Location path

**Configure Gradle:**
1. File → Settings → Build, Execution, Deployment → Gradle
2. Ensure "Use Gradle from" is set to 'gradle-wrapper.properties'

## Project Setup

### 1. Get the Project

**Option A: Clone from Git**
```bash
git clone <repository-url>
cd resQ
```

**Option B: Download ZIP**
1. Download project ZIP file
2. Extract to desired location
3. Navigate to extracted folder

### 2. Open Project in Android Studio

1. Launch Android Studio
2. Click "Open" or "Open an Existing Project"
3. Navigate to `resQ` folder
4. Click "OK"
5. Wait for Gradle sync to complete

**If Gradle Sync Fails:**
1. Check internet connection
2. File → Invalidate Caches and Restart
3. Try again

## Google Maps API Configuration

### 1. Create Google Cloud Project

1. Visit: https://console.cloud.google.com/
2. Click "Select a Project" → "New Project"
3. Enter project name: "ResQHer"
4. Click "Create"

### 2. Enable Maps SDK

1. In Google Cloud Console, go to "APIs & Services" → "Library"
2. Search for "Maps SDK for Android"
3. Click on it and press "Enable"
4. Also enable "Geocoding API"

### 3. Create API Key

1. Go to "APIs & Services" → "Credentials"
2. Click "Create Credentials" → "API Key"
3. Copy the generated API key
4. Click "Restrict Key" (recommended)
5. Under "API restrictions":
   - Select "Restrict key"
   - Check "Maps SDK for Android"
   - Check "Geocoding API"
6. Click "Save"

### 4. Add API Key to Project

1. Open `app/src/main/AndroidManifest.xml`
2. Find the line:
   ```xml
   android:value="YOUR_GOOGLE_MAPS_API_KEY_HERE"
   ```
3. Replace with your actual API key:
   ```xml
   android:value="AIzaSyXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
   ```
4. Save the file

## Building the Project

### 1. Sync Gradle

```bash
./gradlew --refresh-dependencies
```

Or in Android Studio:
- File → Sync Project with Gradle Files

### 2. Build APK

**Via Android Studio:**
1. Build → Build Bundle(s) / APK(s) → Build APK(s)
2. Wait for build to complete
3. Click "locate" in notification to find APK

**Via Command Line:**
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease
```

APK location: `app/build/outputs/apk/debug/app-debug.apk`

## Running on Device

### Option 1: Real Android Device

**Enable Developer Options:**
1. Go to Settings → About Phone
2. Tap "Build Number" 7 times
3. Go back to Settings → Developer Options
4. Enable "USB Debugging"

**Connect and Run:**
1. Connect device via USB
2. Allow USB debugging on device
3. In Android Studio, select device from dropdown
4. Click "Run" button (green play icon)

### Option 2: Android Emulator

**Create Virtual Device:**
1. Tools → Device Manager
2. Click "Create Device"
3. Select device (e.g., Pixel 6)
4. Select system image (API 34 recommended)
5. Download if needed
6. Click "Finish"

**Configure Emulator:**
1. Click "Edit" (pencil icon)
2. Advanced Settings:
   - RAM: 2048 MB or more
   - Enable "Use Host GPU"
3. Click "Finish"

**Run on Emulator:**
1. Start emulator from Device Manager
2. Wait for boot
3. In Android Studio, select emulator
4. Click "Run"

## Testing

### Grant Permissions (First Run)

When app launches first time:
1. Allow "Location" permission → "While using the app"
2. Allow "Phone" permission
3. Allow "SMS" permission
4. If prompted, allow background location

### Test Features

**1. Add Emergency Contact:**
- Open app → Emergency Contacts
- Tap "+" button
- Fill details (use your own number for testing)
- Check "Primary Contact"
- Save

**2. Test SOS (Carefully!):**
- Open app → Emergency SOS
- Press SOS button
- Countdown starts (5 seconds)
- **Press "Cancel" to stop** (avoid sending test SMS)

**3. Test Helpline:**
- Open app → Helpline Numbers
- Browse different categories
- **Don't actually call** unless necessary

**4. View Content:**
- Check Safety Tips
- Read Legal Rights

### Debug Logs

**View Logs:**
1. In Android Studio → Logcat (bottom panel)
2. Select your device
3. Filter by "ResQHer" or "com.resqher.safety"

**Common Tags:**
- `LocationHelper`: Location-related logs
- `SMSManager`: SMS sending logs
- `SOSActivity`: SOS functionality logs

## Troubleshooting

### Build Issues

**Problem: Gradle sync failed**
```
Solution:
1. Check internet connection
2. File → Invalidate Caches → Invalidate and Restart
3. Delete .gradle folder and sync again
```

**Problem: SDK not found**
```
Solution:
1. File → Project Structure → SDK Location
2. Set Android SDK location
3. Click OK and sync
```

### Runtime Issues

**Problem: App crashes on launch**
```
Solution:
1. Check Logcat for error
2. Uninstall app from device
3. Rebuild and reinstall
4. Ensure minimum Android 7.0
```

**Problem: Location not working**
```
Solution:
1. Enable location in device settings
2. Grant location permission in app settings
3. Enable GPS/Location services
4. Test in open area (for real device)
```

**Problem: SMS not sending**
```
Solution:
1. Grant SMS permission
2. Check network coverage
3. Verify contact phone numbers
4. Check SMS logs in Logcat
```

**Problem: Maps not loading**
```
Solution:
1. Check API key in AndroidManifest.xml
2. Verify Maps SDK is enabled in Google Cloud Console
3. Check internet connection
4. Wait 5-10 minutes after adding new API key
```

### Emulator Issues

**Problem: Emulator won't start**
```
Solution:
1. Enable virtualization in BIOS (VT-x/AMD-V)
2. Allocate more RAM to emulator
3. Try different system image
4. Restart Android Studio
```

**Problem: Emulator is slow**
```
Solution:
1. Enable "Use Host GPU" in AVD settings
2. Allocate more RAM (2048 MB+)
3. Close other applications
4. Use x86 system image instead of ARM
```

## Additional Configuration

### Changing Police Number

Default is 100 (India). To change:
1. Open `SOSActivity.java`
2. Find: `private static final String POLICE_NUMBER = "100";`
3. Change to your region's emergency number
4. Rebuild app

### Customizing Helplines

To add/modify helplines:
1. Open `HelplineActivity.java`
2. Find `loadHelplineNumbers()` method
3. Add new helpline:
```java
helplines.add(new Helpline("Name", "Number", "Description", "Category"));
```
4. Rebuild app

## Performance Optimization

### For Development
```gradle
// In app/build.gradle
android {
    buildTypes {
        debug {
            minifyEnabled false
            debuggable true
        }
    }
}
```

### For Release
```gradle
android {
    buildTypes {
        release {
            minifyEnabled true
            shrinkResources true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt')
        }
    }
}
```

## Next Steps

After successful setup:
1. ✅ Test all features thoroughly
2. ✅ Add your real emergency contacts
3. ✅ Familiarize yourself with UI
4. ✅ Read Safety Tips and Legal Rights
5. ✅ Practice SOS activation (with Cancel)
6. ✅ Show to friends and family

## Support Resources

- **Android Documentation**: https://developer.android.com/docs
- **Material Design**: https://material.io/develop/android
- **Room Database**: https://developer.android.com/training/data-storage/room
- **Google Maps**: https://developers.google.com/maps/documentation/android-sdk

## Security Reminder

- Never commit API keys to version control
- Use environment variables for sensitive data
- Test thoroughly before release
- Keep dependencies updated
- Follow Android security best practices

---

**Setup Complete!** You're ready to develop and test ResQHer.

For issues not covered here, check the main README.md or search Android documentation.
