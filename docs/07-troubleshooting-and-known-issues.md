# Troubleshooting & Known Issues

## 1) Build fails with JAVA_HOME not set

### Symptom

- Gradle task fails with `JAVA_HOME is not set`.

### Fix

Use Android Studio bundled JDK:

- Set `JAVA_HOME` to `C:\Program Files\Android\Android Studio\jbr`
- Prepend `%JAVA_HOME%\bin` to `PATH`

## 2) SOS sends message but location missing

### Likely causes

- Location permission denied.
- GPS unavailable / no recent fix.
- Geocoder/network issues.

### Current implementation behavior

- App requests current high-accuracy location.
- Falls back to last known location.
- If still unavailable, sends `Location unavailable`.

## 3) SOS state unexpectedly resets

### Previous behavior

- Auto-reset timer triggered after send.

### Current behavior

- SOS remains active until user explicitly cancels/resets.

## 4) SMS not delivered to contacts

### Checks

- SMS permission granted.
- Contact marked as primary.
- Phone number includes country code.
- Device SIM/network supports SMS.

### Implementation note

- Multipart SMS is used for long messages to reduce truncation risks.

## 5) WhatsApp share not opening

### Cause

- WhatsApp not installed.

### Current handling

- Catches `ActivityNotFoundException` and shows toast.

## 6) Quick actions not responding

### Confirm

- IDs exist in home layout.
- `MainActivity` click listeners are bound.
- App rebuilt/installed after layout edits.

## 7) Lint failures

Common currently observed categories:

- `android:tint` vs `app:tint` in some layouts
- implied hardware permission warnings
- dependency/version update recommendations

## 8) Theme changes not visible

### Causes

- Change applied only in tokens but not in layout usage.
- App not reinstalled after resource edits.
- Cached activity not refreshed.

### Fix

- Rebuild + reinstall debug APK.
- Force-stop and relaunch app if needed.

## Known Gaps

- Limited automated test coverage.
- Static helpline/tips/legal content stored in activity code.
- Region-specific emergency number assumptions (`100`).
