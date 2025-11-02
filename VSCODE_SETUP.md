# Running ResQHer in VS Code

This guide shows how to run the ResQHer Android project using Visual Studio Code instead of Android Studio.

## Why VS Code for Android?

**Pros:**
- Lighter and faster than Android Studio
- Familiar if you already use VS Code
- Good for editing and building

**Cons:**
- No visual layout editor
- No built-in Android emulator manager
- More manual setup required
- **Recommended: Use Android Studio for Android development**

---

## Complete Setup Guide

### Step 1: Install Prerequisites

#### 1.1 Install Java JDK

**Download:**
- Go to: https://www.oracle.com/java/technologies/downloads/
- Download JDK 11 or JDK 17

**Install:**
- Run installer with default settings
- Note installation path (e.g., `C:\Program Files\Java\jdk-17`)

**Verify:**
```bash
java -version
```

#### 1.2 Install Android Command Line Tools

**Download:**
- Go to: https://developer.android.com/studio#command-tools
- Scroll to "Command line tools only"
- Download for Windows

**Install:**
1. Create folder: `C:\Android\SDK`
2. Extract downloaded ZIP to: `C:\Android\SDK\cmdline-tools\latest`
3. Open Command Prompt and run:
```bash
cd C:\Android\SDK\cmdline-tools\latest\bin
sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"
```

#### 1.3 Set Environment Variables

**Windows:**
1. Right-click "This PC" → Properties → Advanced System Settings
2. Click "Environment Variables"
3. Under "System Variables", click "New":

```
Variable: ANDROID_HOME
Value: C:\Android\SDK

Variable: JAVA_HOME
Value: C:\Program Files\Java\jdk-17
```

4. Edit "Path" variable, add:
```
%ANDROID_HOME%\platform-tools
%ANDROID_HOME%\cmdline-tools\latest\bin
%JAVA_HOME%\bin
```

5. Click OK and restart computer

**Verify:**
```bash
# Open new Command Prompt
adb version
java -version
```

### Step 2: Install VS Code Extensions

Open VS Code and install these extensions:

1. **Extension Pack for Java** (Microsoft)
   - Press `Ctrl+Shift+X`
   - Search: "Extension Pack for Java"
   - Click Install

2. **Gradle for Java** (Microsoft)
   - Search: "Gradle for Java"
   - Click Install

3. **Android iOS Emulator** (Optional)
   - Search: "Android iOS Emulator"
   - Click Install

### Step 3: Open Project in VS Code

1. Open VS Code
2. File → Open Folder
3. Select `d:\resQ` folder
4. Click "Select Folder"

### Step 4: Configure Google Maps API

Before running, add your Google Maps API key:

1. In VS Code, open: `app\src\main\AndroidManifest.xml`
2. Find line: `android:value="YOUR_GOOGLE_MAPS_API_KEY_HERE"`
3. Replace with your API key (get from https://console.cloud.google.com/)
4. Save file

### Step 5: Build the Project

#### Method 1: Using VS Code Tasks (Recommended)

1. Press `Ctrl+Shift+P`
2. Type: "Tasks: Run Task"
3. Select: "Build Debug APK"
4. Wait for build to complete

#### Method 2: Using Terminal

1. In VS Code, open Terminal: `Ctrl+` ` (backtick)
2. Run:
```bash
.\gradlew.bat assembleDebug
```

**First Build:**
- First build will download Gradle and dependencies (5-10 minutes)
- Requires internet connection
- Subsequent builds will be faster

### Step 6: Connect Android Device

#### Option A: Real Android Device

1. **Enable Developer Options:**
   - Settings → About Phone
   - Tap "Build Number" 7 times

2. **Enable USB Debugging:**
   - Settings → Developer Options
   - Turn on "USB Debugging"

3. **Connect:**
   - Connect phone via USB
   - Allow USB debugging on phone
   - Verify connection:
   ```bash
   adb devices
   ```
   You should see your device listed

#### Option B: Android Emulator

You need Android Studio or Genymotion for emulator.

**Using Android Studio Emulator:**
1. Install Android Studio (just for emulator)
2. Create virtual device in Android Studio
3. Start emulator
4. In VS Code terminal, run:
```bash
adb devices
```
You should see emulator listed

### Step 7: Install App on Device

#### Method 1: Using VS Code Task

1. Press `Ctrl+Shift+P`
2. Type: "Tasks: Run Task"
3. Select: "Build and Install"
4. Wait for installation

#### Method 2: Using Terminal

```bash
# Build and install in one command
.\gradlew.bat installDebug

# Or build first, then install
.\gradlew.bat assembleDebug
adb install -r app\build\outputs\apk\debug\app-debug.apk
```

### Step 8: Launch the App

**Automatic Launch:**
```bash
adb shell am start -n com.resqher.safety/.MainActivity
```

**Manual Launch:**
- Open app drawer on device
- Find "ResQHer" app
- Tap to launch

---

## Quick Commands Reference

### Build Commands

```bash
# Clean build
.\gradlew.bat clean

# Build debug APK
.\gradlew.bat assembleDebug

# Build release APK
.\gradlew.bat assembleRelease

# Install on connected device
.\gradlew.bat installDebug

# Uninstall from device
.\gradlew.bat uninstallDebug

# Build and install
.\gradlew.bat installDebug
```

### ADB Commands

```bash
# List connected devices
adb devices

# Install APK
adb install -r app\build\outputs\apk\debug\app-debug.apk

# Uninstall app
adb uninstall com.resqher.safety

# Launch app
adb shell am start -n com.resqher.safety/.MainActivity

# View logs
adb logcat | findstr ResQHer

# Clear app data
adb shell pm clear com.resqher.safety
```

### Useful Gradle Commands

```bash
# List all tasks
.\gradlew.bat tasks

# Check dependencies
.\gradlew.bat dependencies

# Show project info
.\gradlew.bat projects
```

---

## VS Code Workflow

### Daily Development Workflow

1. **Edit Code:**
   - Make changes in VS Code
   - Save files (`Ctrl+S`)

2. **Build:**
   - Press `Ctrl+Shift+B` (default build task)
   - Or: `Ctrl+Shift+P` → "Tasks: Run Task" → "Build Debug APK"

3. **Install:**
   - `Ctrl+Shift+P` → "Tasks: Run Task" → "Install Debug APK"

4. **Test:**
   - Launch app on device manually
   - Or use: `adb shell am start -n com.resqher.safety/.MainActivity`

5. **View Logs:**
   - Terminal: `adb logcat | findstr ResQHer`

### Keyboard Shortcuts

```
Ctrl+Shift+P     - Command Palette
Ctrl+`           - Toggle Terminal
Ctrl+Shift+B     - Build (default task)
Ctrl+P           - Quick Open File
Ctrl+Shift+F     - Search in Files
F2               - Rename Symbol
Ctrl+Space       - IntelliSense
```

---

## Troubleshooting

### Issue 1: "gradlew.bat is not recognized"

**Solution:**
```bash
# Make sure you're in project directory
cd d:\resQ

# Use .\ prefix
.\gradlew.bat assembleDebug
```

### Issue 2: "JAVA_HOME is not set"

**Solution:**
```bash
# Set temporarily in terminal
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%

# Or add to System Environment Variables permanently
```

### Issue 3: "SDK not found"

**Solution:**
Create `local.properties` file:
```bash
# In VS Code, create file: local.properties
# Add this line (adjust path if different):
sdk.dir=C:\\Android\\SDK
```

### Issue 4: "adb: command not found"

**Solution:**
```bash
# Add to PATH temporarily
set PATH=%PATH%;C:\Android\SDK\platform-tools

# Verify
adb version
```

### Issue 5: Build fails with "Unable to find target"

**Solution:**
```bash
# Install required SDK
cd C:\Android\SDK\cmdline-tools\latest\bin
sdkmanager "platforms;android-34"
sdkmanager "build-tools;34.0.0"
```

### Issue 6: Device not detected

**Solution:**
```bash
# Check USB debugging is enabled
# Install device drivers (Windows)
# Try different USB cable/port
# Restart adb
adb kill-server
adb start-server
adb devices
```

---

## VS Code Settings Explained

I've created three configuration files in `.vscode/` folder:

### 1. settings.json
- Java configuration
- Gradle settings
- File exclusions

### 2. tasks.json
Contains build tasks:
- Build Debug APK
- Install Debug APK
- Build and Install
- Clean Build

Use with: `Ctrl+Shift+P` → "Tasks: Run Task"

### 3. launch.json
- Debug configuration
- Launch settings

---

## Limitations of VS Code for Android

### What You'll Miss from Android Studio:

1. **Visual Layout Editor**
   - No drag-and-drop UI design
   - Edit XML manually

2. **AVD Manager**
   - No built-in emulator manager
   - Need Android Studio or Genymotion

3. **Android Profiler**
   - No performance monitoring
   - No memory/CPU profiling

4. **Instant Run**
   - Slower iteration
   - Full rebuild each time

5. **Logcat Integration**
   - Must use `adb logcat` in terminal
   - No filtered log viewer

### When to Use Android Studio Instead:

- Creating/editing layouts visually
- Managing emulators
- Performance profiling
- Debugging complex issues
- First time Android development

---

## Hybrid Approach (Best of Both Worlds)

**Recommended Setup:**

1. **Install both VS Code AND Android Studio**

2. **Use Android Studio for:**
   - Creating emulators
   - Visual layout editing
   - Initial project setup
   - Performance profiling

3. **Use VS Code for:**
   - Coding Java files
   - Editing configuration files
   - Quick edits
   - Lightweight development

4. **Switch between them:**
   - Both can open same project
   - Just close in one before opening in other
   - Files sync automatically

---

## Alternative: Android Studio Lightweight

If you find Android Studio too heavy:

**Download Android Studio but use it minimally:**
1. Install Android Studio
2. Use it only to:
   - Create/manage emulators
   - Edit layouts visually
3. Do actual coding in VS Code
4. Keep Android Studio closed when not needed

---

## Quick Start (TL;DR)

```bash
# 1. Install Java JDK 17
# 2. Install Android SDK to C:\Android\SDK
# 3. Set ANDROID_HOME and JAVA_HOME environment variables
# 4. Restart computer

# 5. Open VS Code
# 6. Install "Extension Pack for Java"
# 7. Open folder: d:\resQ

# 8. Add Google Maps API key to AndroidManifest.xml

# 9. Connect phone (USB debugging enabled)

# 10. Build and install
cd d:\resQ
.\gradlew.bat installDebug

# 11. Launch app on phone manually
```

---

## Still Too Complex?

**Honest Recommendation:**

For Android development, **Android Studio is the standard tool** for a reason. It's designed specifically for Android and includes everything you need.

**Just use Android Studio** - it will save you hours of configuration and troubleshooting.

Download here: https://developer.android.com/studio

The setup is much simpler:
1. Install Android Studio
2. Open project
3. Click Run
4. Done!

---

## Summary

✅ **VS Code CAN run Android projects**
⚠️ **But requires more setup**
💡 **Android Studio is recommended for beginners**

Choose what works best for you!
