# 🚀 Complete Guide: Running ResQHer in Android Studio

**Total Time Required: 30-40 minutes** (including downloads)

---

## ✅ PRE-FLIGHT CHECKLIST

Before you start, you'll need:
- [ ] Windows 10/11, macOS 10.14+, or Linux
- [ ] 8GB RAM minimum (16GB recommended)
- [ ] 10GB free disk space
- [ ] Internet connection (for downloads)
- [ ] Android phone with USB cable **OR** willingness to use emulator

---

## 📥 STEP 1: Download & Install Android Studio

### 1.1 Download Android Studio

1. Open your web browser
2. Go to: **https://developer.android.com/studio**
3. Click the green **"Download Android Studio"** button
4. Accept the Terms and Conditions
5. Click **"Download Android Studio"** again
6. Wait for download (file size: ~1 GB, takes 5-15 minutes depending on internet)

**File you'll download:**
- Windows: `android-studio-2023.x.x.x-windows.exe`
- macOS: `android-studio-2023.x.x.x-mac.dmg`
- Linux: `android-studio-2023.x.x.x-linux.tar.gz`

### 1.2 Install Android Studio

**For Windows:**
1. Double-click the downloaded `.exe` file
2. Click **"Next"** on the welcome screen
3. **Choose components:**
   - ✅ Android Studio
   - ✅ Android Virtual Device
   - Click **"Next"**
4. **Choose install location:**
   - Keep default: `C:\Program Files\Android\Android Studio`
   - Click **"Next"**
5. Click **"Install"**
6. Wait for installation (5-10 minutes)
7. Click **"Finish"**

**For macOS:**
1. Double-click the downloaded `.dmg` file
2. Drag Android Studio to Applications folder
3. Open Applications → Android Studio

**For Linux:**
1. Extract the tar.gz file
2. Navigate to `android-studio/bin/`
3. Run `./studio.sh`

### 1.3 First Launch Setup

1. **Welcome Screen** appears
   - Click **"Next"**

2. **Install Type**
   - Select **"Standard"**
   - Click **"Next"**

3. **Select UI Theme**
   - Choose **"Light"** or **"Darcula"** (your preference)
   - Click **"Next"**

4. **Verify Settings**
   - Review the settings
   - Click **"Next"**

5. **License Agreement**
   - Click each license
   - Click **"Accept"**
   - Click **"Finish"**

6. **Downloading Components** (IMPORTANT!)
   - Android Studio will now download SDK components
   - This takes 10-20 minutes
   - Shows download progress:
     - Android SDK Platform
     - Android SDK Build-Tools
     - Android Emulator
     - System Images
   - **DO NOT CLOSE** - let it complete!

7. When finished, click **"Finish"**

**🎉 Android Studio is now installed!**

---

## 📂 STEP 2: Open ResQHer Project

### 2.1 Launch Android Studio

1. Open Android Studio from:
   - **Windows:** Start Menu → Android Studio
   - **macOS:** Applications → Android Studio
   - **Linux:** Terminal → run `studio.sh`

### 2.2 Open the Project

1. You'll see the **"Welcome to Android Studio"** window

2. Click **"Open"** button
   - It's on the main welcome screen
   - Alternatively: **File → Open** if Android Studio is already open

3. **Navigate to project folder:**
   - Browse to: `d:\resQ`
   - Click on the `resQ` folder (not inside it)
   - Click **"OK"**

4. **Trust Project** dialog appears:
   - Click **"Trust Project"**

5. **First-time project opening:**
   - You'll see "Gradle Sync" starting
   - Bottom right shows progress: "Gradle Build Running"
   - **This is NORMAL and takes 5-10 minutes first time**
   - Android Studio is:
     - Downloading Gradle wrapper
     - Downloading project dependencies
     - Indexing files
     - Building project structure

6. **Wait for Gradle Sync to finish:**
   - Watch the bottom status bar
   - When done, it says: "Gradle sync finished"
   - **If errors occur, see Troubleshooting section below**

**✅ Project is now open!**

---

## 🗺️ STEP 3: Add Google Maps API Key

**Why needed:** The app uses Google Maps to show location. You need a free API key.

### 3.1 Get Google Maps API Key

1. **Go to Google Cloud Console:**
   - Open browser: https://console.cloud.google.com/

2. **Sign in:**
   - Use your Google account (Gmail)
   - Accept terms if first time

3. **Create a new project:**
   - Click dropdown at top: **"Select a project"**
   - Click **"New Project"**
   - Project name: **"ResQHer"**
   - Click **"Create"**
   - Wait 30 seconds for project creation

4. **Select your project:**
   - Click dropdown again
   - Select **"ResQHer"**

5. **Enable Maps SDK:**
   - In search bar at top, type: **"Maps SDK for Android"**
   - Click on **"Maps SDK for Android"** result
   - Click **"Enable"** button
   - Wait for it to enable

6. **Enable Geocoding API:**
   - Search: **"Geocoding API"**
   - Click result
   - Click **"Enable"**

7. **Create API Key:**
   - Click **"Credentials"** in left menu
   - Click **"+ Create Credentials"** at top
   - Select **"API Key"**
   - A popup shows your API key: `AIzaSyXXXXXXXXXXXXXXXXXX`
   - Click **"Copy"** button
   - Save this key somewhere (Notepad)

8. **Restrict API Key (Recommended):**
   - Click **"Restrict Key"** in the popup
   - Under "API restrictions":
     - Select **"Restrict key"**
     - Check: ✅ Maps SDK for Android
     - Check: ✅ Geocoding API
   - Click **"Save"**

**🔑 You now have your API Key!**

### 3.2 Add API Key to Project

1. **Back in Android Studio:**

2. **Open AndroidManifest.xml:**
   - In left panel (Project view), navigate:
     ```
     app → src → main → AndroidManifest.xml
     ```
   - Double-click to open

3. **Find the API key line:**
   - Press `Ctrl+F` (Windows/Linux) or `Cmd+F` (Mac)
   - Search for: `YOUR_GOOGLE_MAPS_API_KEY_HERE`
   - You'll find it around line 57

4. **Replace with your key:**
   - Select `YOUR_GOOGLE_MAPS_API_KEY_HERE`
   - Paste your actual API key
   - Should look like:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="AIzaSyXXXXXXXXXXXXXXXXXX" />
   ```

5. **Save the file:**
   - Press `Ctrl+S` or `Cmd+S`

**✅ API Key configured!**

---

## 📱 STEP 4: Set Up a Device (Choose One)

You need either a **real phone** OR an **emulator**.

### OPTION A: Use Your Real Android Phone (Recommended - Faster!)

#### 4A.1 Enable Developer Options

1. **On your Android phone:**
   - Go to **Settings**
   - Scroll to **"About phone"** (or "About device")
   - Find **"Build number"**
   - **Tap "Build number" 7 times rapidly**
   - You'll see: "You are now a developer!"

#### 4A.2 Enable USB Debugging

1. **Still on your phone:**
   - Go back to **Settings**
   - Find **"Developer options"** (usually under System or Additional settings)
   - Toggle **ON** at the top
   - Scroll to find **"USB debugging"**
   - Toggle **"USB debugging" ON**
   - Popup appears: **"Allow USB debugging?"**
   - Tap **"OK"**

#### 4A.3 Connect Phone to Computer

1. **Use USB cable:**
   - Connect phone to computer via USB cable
   - **Use data cable, not just charging cable!**

2. **On phone, unlock screen:**
   - A popup appears: **"Allow USB debugging?"**
   - Check: ✅ **"Always allow from this computer"**
   - Tap **"Allow"**

3. **Select USB mode:**
   - Swipe down notification
   - Tap USB notification
   - Select **"File Transfer"** or **"MTP"**

4. **Verify in Android Studio:**
   - Look at top toolbar
   - You should see your phone name in device dropdown
   - Example: "Samsung Galaxy S21" or "Pixel 6"

**✅ Phone connected!** → Skip to STEP 5

---

### OPTION B: Use Android Emulator (If No Phone)

#### 4B.1 Open Device Manager

1. **In Android Studio:**
   - Click **"Tools"** menu at top
   - Select **"Device Manager"**
   - Side panel opens on right

2. **Or use the icon:**
   - Look for phone icon in top-right toolbar
   - Click it

#### 4B.2 Create Virtual Device

1. **In Device Manager:**
   - Click **"Create Device"** button (or **"+"** icon)

2. **Select Hardware:**
   - Category: **"Phone"**
   - Select: **"Pixel 6"** (recommended)
   - Click **"Next"**

3. **Select System Image:**
   - **Release Name:** Select **"Tiramisu"** (API 33)
   - If you see **"Download"** next to it:
     - Click **"Download"**
     - Accept license
     - Wait for download (500 MB - 1 GB, takes 5-15 minutes)
     - Shows progress bar
   - After download, it's selected automatically
   - Click **"Next"**

4. **Verify Configuration:**
   - **AVD Name:** Keep default "Pixel 6 API 33"
   - **Startup orientation:** Portrait
   - **Show Advanced Settings** (optional):
     - RAM: 2048 MB (recommended)
     - VM heap: 256 MB
     - Internal Storage: 2048 MB
   - Click **"Finish"**

**✅ Emulator created!**

#### 4B.3 Start the Emulator

1. **In Device Manager:**
   - Find your "Pixel 6 API 33"
   - Click **▶ (Play/Run)** button next to it

2. **Emulator window opens:**
   - Takes 1-3 minutes to boot first time
   - You'll see Android logo
   - Then home screen appears

3. **Emulator is ready when:**
   - You see the Android home screen
   - Status bar shows time and battery

**✅ Emulator ready!** → Continue to STEP 5

---

## ▶️ STEP 5: Run the App!

**THE MOMENT YOU'VE BEEN WAITING FOR!**

### 5.1 Select Your Device

1. **Look at top toolbar** in Android Studio
2. **Device dropdown:**
   - Click the dropdown showing device names
   - You should see:
     - Your connected phone name, OR
     - "Pixel 6 API 33" emulator
   - Select your device

### 5.2 Run the App

1. **Click the green Run button (▶)**
   - It's in the top toolbar
   - Big green play button
   - Or press **Shift + F10** (Windows/Linux) or **Ctrl + R** (Mac)

2. **Build process starts:**
   - Bottom panel shows "Build" progress
   - Messages like:
     - "Executing tasks..."
     - "Build successful"
   - First build takes 2-5 minutes

3. **Installation happens:**
   - Shows "Installing APKs"
   - App installs on device/emulator

4. **App launches automatically!**

### 5.3 First Run - Grant Permissions

**The app will ask for permissions:**

1. **Location Permission:**
   - Popup: "Allow ResQHer to access this device's location?"
   - Select: **"While using the app"**
   - Or: **"Allow"**

2. **Phone Permission:**
   - Popup: "Allow ResQHer to make and manage phone calls?"
   - Tap: **"Allow"**

3. **SMS Permission:**
   - Popup: "Allow ResQHer to send and view SMS messages?"
   - Tap: **"Allow"**

4. **Contacts Permission:**
   - Popup: "Allow ResQHer to access your contacts?"
   - Tap: **"Allow"** (or "Deny" if you prefer to type manually)

**All permissions granted! ✅**

### 5.4 Explore the App!

**You should now see the ResQHer home screen with 5 cards:**

1. 🚨 **Emergency SOS** (red)
2. 📞 **Emergency Contacts**
3. ☎️ **Helpline Numbers**
4. 💡 **Safety Tips**
5. ⚖️ **Legal Rights**

---

## 🧪 STEP 6: Test the App

### Test 1: Add an Emergency Contact

1. **Tap "Emergency Contacts"**
2. **Tap the "+" button** (bottom right)
3. **Fill in details:**
   - Name: Your name
   - Phone: Your phone number (for testing)
   - Relationship: Friend/Family
   - ✅ Check "Primary Contact"
4. **Tap "Add"**
5. **See your contact** in the list

### Test 2: Test SOS (CAREFULLY!)

⚠️ **IMPORTANT: Use Cancel to avoid sending real SMS!**

1. **Go back** to main screen
2. **Tap "Emergency SOS"**
3. **Press the big red "SOS" button**
4. **Countdown starts:** 5... 4... 3...
5. **QUICKLY tap "Cancel SOS"** button
6. **Confirmation:** "SOS Cancelled"

**DO NOT let it reach 0 unless you want to send real emergency SMS!**

### Test 3: View Helplines

1. **Go back** to main screen
2. **Tap "Helpline Numbers"**
3. **Scroll through** different helplines
4. **See categories:** Emergency, Women Safety, etc.

### Test 4: Read Content

1. **Tap "Safety Tips"** - read safety information
2. **Tap "Legal Rights"** - read legal rights

**✅ All features working!**

---

## 🎉 SUCCESS!

**You've successfully:**
- ✅ Installed Android Studio
- ✅ Opened ResQHer project
- ✅ Added Google Maps API key
- ✅ Set up a device (phone or emulator)
- ✅ Run the app
- ✅ Tested features

**Your ResQHer app is now running!**

---

## 🔧 TROUBLESHOOTING

### Problem 1: Gradle Sync Failed

**Error:** "Gradle sync failed: SDK location not found"

**Solution:**
1. In Android Studio: **File → Project Structure**
2. **SDK Location** tab
3. **Android SDK location:** Should show a path
4. If empty, click **"Edit"**
5. Accept license and download SDK
6. Click **"Apply"** → **"OK"**
7. Try sync again: **File → Sync Project with Gradle Files**

---

### Problem 2: Device Not Showing

**Error:** No device appears in dropdown

**Solution for Real Phone:**
1. Unplug and replug USB cable
2. Try different USB port
3. On phone: Revoke USB debugging authorizations
   - Settings → Developer Options → Revoke USB debugging
   - Replug and allow again
4. Install phone drivers (Windows):
   - Go to phone manufacturer website
   - Download USB drivers for your phone model

**Solution for Emulator:**
1. **Tools → Device Manager**
2. Find your emulator
3. Click **▶** to start it
4. Wait for it to fully boot
5. Check dropdown again

---

### Problem 3: Build Failed - Dependencies

**Error:** "Could not resolve com.google.android.material:material:1.10.0"

**Solution:**
1. Check internet connection
2. **File → Invalidate Caches → Invalidate and Restart**
3. After restart: **File → Sync Project with Gradle Files**
4. If still fails: **Build → Clean Project**, then **Build → Rebuild Project**

---

### Problem 4: App Crashes on Launch

**Error:** App opens then immediately closes

**Solution:**
1. **View → Tool Windows → Logcat**
2. Look for red error messages
3. Common causes:
   - **Permissions not granted:** Grant all permissions
   - **Google Maps API key missing:** Double-check Step 3
   - **Android version too old:** Use Android 7.0+ device

---

### Problem 5: Emulator is Very Slow

**Solution:**
1. Close emulator
2. **Tools → Device Manager**
3. Click **⋮** (three dots) next to your emulator
4. Select **"Edit"**
5. **Show Advanced Settings**
6. **Increase RAM** to 3072 MB or 4096 MB
7. **Enable:** ✅ Hardware - GLES 2.0
8. Click **"Finish"**
9. Restart emulator

---

### Problem 6: "Installed Build Tools revision X.X.X is corrupted"

**Solution:**
1. **Tools → SDK Manager**
2. **SDK Tools** tab
3. **Uncheck** "Android SDK Build-Tools"
4. Click **"Apply"** (uninstalls)
5. **Check** "Android SDK Build-Tools" again
6. Click **"Apply"** (reinstalls)
7. Click **"OK"**
8. Sync project again

---

### Problem 7: Maps Not Loading in App

**Solution:**
1. Verify API key is correct in AndroidManifest.xml
2. Check Google Cloud Console:
   - Maps SDK for Android: ✅ Enabled
   - Geocoding API: ✅ Enabled
3. Wait 5-10 minutes after creating new API key
4. Check internet connection on device/emulator
5. Rebuild app: **Build → Clean Project** → **Build → Rebuild Project**

---

### Problem 8: Cannot Run on Android 14+

**Error:** "App not compatible with Android 14"

**Solution:**
Already configured in project to work with Android 14. If issues:
1. Open `app/build.gradle`
2. Check: `targetSdk 34` is set
3. Sync project

---

## 📚 USEFUL ANDROID STUDIO SHORTCUTS

| Action | Windows/Linux | Mac |
|--------|--------------|-----|
| Run app | Shift + F10 | Ctrl + R |
| Build project | Ctrl + F9 | Cmd + F9 |
| Find file | Ctrl + Shift + N | Cmd + Shift + O |
| Find in file | Ctrl + F | Cmd + F |
| Go to line | Ctrl + G | Cmd + L |
| Comment code | Ctrl + / | Cmd + / |
| Format code | Ctrl + Alt + L | Cmd + Alt + L |
| Show Logcat | Alt + 6 | Cmd + 6 |

---

## 🎓 NEXT STEPS

### For Development:

1. **Explore the code:**
   - `app/src/main/java/` - Java source files
   - `app/src/main/res/layout/` - UI layouts

2. **Make changes:**
   - Edit any Java or XML file
   - Click Run button to see changes

3. **View logs:**
   - **View → Tool Windows → Logcat**
   - Filter by "ResQHer" to see app logs

### For Testing:

1. **Add real emergency contacts**
2. **Test location accuracy** (walk around with phone)
3. **Try all features** thoroughly
4. **Test in different scenarios**

### For Your Project:

1. **Document features** with screenshots
2. **Record demo video** for presentation
3. **Prepare project report**
4. **Test on multiple devices** if possible

---

## 💡 TIPS & BEST PRACTICES

### 1. Keep Android Studio Updated
- **Help → Check for Updates**
- Install updates when prompted

### 2. Save Work Frequently
- Android Studio auto-saves, but use **Ctrl+S / Cmd+S** anyway

### 3. Clean Build When Stuck
- **Build → Clean Project**
- **Build → Rebuild Project**
- Fixes many mysterious errors

### 4. Use Logcat for Debugging
- **View → Tool Windows → Logcat**
- Filter by app package: `com.resqher.safety`

### 5. Test on Real Device
- Emulator is good, but real device testing is essential
- GPS, SMS, and phone features work better on real devices

---

## 🆘 STILL HAVING ISSUES?

### Check these files in your project:
- **README.md** - General project documentation
- **SETUP_GUIDE.md** - Detailed technical setup
- **VSCODE_SETUP.md** - Alternative VS Code instructions

### Common Questions:

**Q: How long should first build take?**
A: 5-15 minutes is normal for first build

**Q: Do I need internet always?**
A: Only for initial setup and first builds. After that, offline works.

**Q: Can I use older Android Studio version?**
A: Recommend latest version for best compatibility

**Q: My phone is not recognized**
A: Try different cable, USB port, install drivers, enable File Transfer mode

**Q: Emulator won't start**
A: Enable virtualization in BIOS (VT-x for Intel, AMD-V for AMD)

---

## 📞 PROJECT DETAILS

**Project Name:** ResQHer - Women Safety Application
**Type:** Final Year Project
**Platform:** Android
**Language:** Java
**IDE:** Android Studio
**Min SDK:** Android 7.0 (API 24)
**Target SDK:** Android 14 (API 34)

---

## ✅ CHECKLIST SUMMARY

Before submitting your project, ensure:

- [ ] App builds without errors
- [ ] App runs on device/emulator
- [ ] All permissions work
- [ ] SOS feature tested (with cancel)
- [ ] Contacts CRUD works
- [ ] Helplines display correctly
- [ ] Safety Tips load
- [ ] Legal Rights load
- [ ] Google Maps API configured
- [ ] Location tracking works
- [ ] SMS sending works (tested carefully)
- [ ] No crashes or freezes

---

**🎉 Congratulations! You're now ready to run, test, and demonstrate your ResQHer Women Safety Application!**

**Good luck with your Final Year Project! 🚀**
