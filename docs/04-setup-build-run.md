# Setup, Build, Run, Install

## Prerequisites

- Android Studio (latest stable recommended)
- Android SDK installed
- JDK available (Android Studio bundled JBR works)
- Device or emulator for testing

## Open Project

1. Open Android Studio.
2. Select existing project: `resQ`.
3. Wait for Gradle sync.

## Configure Google Maps Key

In `app/src/main/AndroidManifest.xml`, update:

- `com.google.android.geo.API_KEY`
- Replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` with real API key.

## Build Commands (Gradle Wrapper)

From project root:

- Build debug APK: `gradlew.bat assembleDebug`
- Install debug APK: `gradlew.bat installDebug`
- Build + install: `gradlew.bat assembleDebug installDebug`
- Unit tests: `gradlew.bat testDebugUnitTest`
- Lint: `gradlew.bat lintDebug`

## VS Code Tasks Available

- Build Debug APK
- Install Debug APK
- Build and Install
- Clean Build

## If JAVA_HOME Error Appears

Use Android Studio JBR in terminal session:

- `set JAVA_HOME=C:\Program Files\Android\Android Studio\jbr`
- prepend `%JAVA_HOME%\bin` to `PATH`

(PowerShell equivalent used in this project workflow.)

## Deploy to Physical Device

1. Enable developer mode + USB debugging on device.
2. Connect via USB.
3. Confirm with `adb devices`.
4. Run install command.

## Run-time Permission Onboarding

On first launch, grant all required permissions to avoid partial functionality:

- Call
- SMS
- Fine location
- Coarse location
- Contacts

## Typical Dev Loop

1. Edit code/resources.
2. `assembleDebug installDebug`.
3. Launch app.
4. Test feature path.
5. Check Logcat/terminal output.
