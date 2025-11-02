# ⚡ QUICK START - ResQHer App

**Time: 30 minutes** | **Difficulty: Easy**

---

## 📋 What You Need

- [ ] Computer (Windows/Mac/Linux)
- [ ] Internet connection
- [ ] Android phone with USB cable OR willingness to use emulator

---

## 🚀 5 Simple Steps

### 1️⃣ Download Android Studio (10 min)
```
https://developer.android.com/studio
```
- Click "Download Android Studio"
- Install with default settings
- Let it download components (be patient!)

### 2️⃣ Open Project (2 min)
```
Android Studio → Open → d:\resQ → Trust Project
```
- Wait for Gradle sync (5-10 minutes first time)

### 3️⃣ Get Google Maps API Key (5 min)
```
1. https://console.cloud.google.com/
2. New Project → "ResQHer"
3. Enable "Maps SDK for Android"
4. Create API Key → Copy it
5. Paste in: app/src/main/AndroidManifest.xml (line 57)
```

### 4️⃣ Connect Device (3 min)

**Option A: Real Phone**
```
Settings → About Phone → Tap "Build Number" 7 times
Settings → Developer Options → USB Debugging ON
Connect phone via USB
```

**Option B: Emulator**
```
Tools → Device Manager → Create Device
Select "Pixel 6" → Download System Image
Start emulator
```

### 5️⃣ Run! (2 min)
```
Click green ▶ button in Android Studio
Grant permissions on phone/emulator
Done! ✅
```

---

## 🆘 Problems?

**Can't find the Run button?**
- It's green ▶ in top toolbar, or press Shift+F10

**Gradle sync failed?**
- File → Invalidate Caches → Restart

**Device not showing?**
- Phone: Try different USB cable/port
- Emulator: Tools → Device Manager → Start emulator

**Build errors?**
- Build → Clean Project
- Build → Rebuild Project

---

## 📖 Need More Help?

Open **[HOW_TO_RUN_IN_ANDROID_STUDIO.md](HOW_TO_RUN_IN_ANDROID_STUDIO.md)** for:
- Detailed step-by-step guide with explanations
- Screenshots descriptions
- Complete troubleshooting section
- Tips and best practices

---

## ✅ Success Checklist

- [ ] Android Studio installed
- [ ] Project opened and synced
- [ ] Google Maps API key added
- [ ] Device connected/emulator running
- [ ] App running and permissions granted
- [ ] Emergency contact added and tested

---

**🎉 That's it! Your ResQHer app is now running!**

For detailed instructions, see: **HOW_TO_RUN_IN_ANDROID_STUDIO.md**
