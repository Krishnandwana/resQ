# 🔧 Fix SMS Not Sending Issue

## 📋 Checklist - Go Through Each Step

### ✅ Step 1: Check SMS Permission

**In the app:**
1. Go to phone Settings
2. Apps → ResQHer → Permissions
3. Make sure **SMS** permission is **ALLOWED**

**If not allowed:**
- Open the app
- Grant SMS permission when prompted
- Or manually enable it in Settings

---

### ✅ Step 2: Check Emergency Contacts Exist

**Critical:** You MUST have contacts marked as "Primary"!

1. Open ResQHer app
2. Tap **"Emergency Contacts"**
3. **Do you see any contacts?**
   - **NO** → Add contacts first (see Step 2a)
   - **YES** → Check if they're marked as "Primary" (see Step 2b)

#### Step 2a: Add Emergency Contact
1. Tap the **+ (Plus)** button
2. Fill in:
   - **Name:** Your name (for testing)
   - **Phone:** Your phone number (include country code if needed)
   - **Relationship:** Friend/Family
3. ✅ **IMPORTANT: Check "Primary Contact"** box
4. Tap **"Add"**

#### Step 2b: Verify Contact is Primary
- Look for a **"PRIMARY"** badge next to the contact name
- If no badge, edit the contact and check "Primary Contact"

**Why this matters:**
The code only sends SMS to contacts marked as PRIMARY (line 126 in SOSActivity)

---

### ✅ Step 3: Check Location Permission

1. Go to phone Settings
2. Apps → ResQHer → Permissions
3. **Location** should be set to:
   - **"Allow all the time"** OR
   - **"Allow only while using the app"**

**Enable if not allowed**

---

### ✅ Step 4: Test with Your Own Number

**Best way to test:**
1. Add YOUR OWN phone number as emergency contact
2. Mark it as Primary
3. Activate SOS
4. You should receive SMS on the same phone

---

### ✅ Step 5: Check Phone Number Format

**Common issues:**

❌ **Wrong:** `9876543210` (missing country code)
✅ **Correct:** `+919876543210` (with country code)

**For India:** Use `+91` prefix
**For USA:** Use `+1` prefix
**For UK:** Use `+44` prefix

**How to fix:**
1. Edit the contact
2. Add country code: `+91XXXXXXXXXX`
3. Save

---

### ✅ Step 6: Check Android Studio Logcat

**This will tell you exactly what's wrong!**

1. **In Android Studio:**
   - Bottom panel → Click **"Logcat"** tab
   - Or: View → Tool Windows → Logcat

2. **Filter logs:**
   - In the search box, type: `SMSManager`
   - Or type: `ResQHer`

3. **Trigger SOS:**
   - Run the app
   - Press SOS button
   - Let countdown finish

4. **Look for these messages:**

   **✅ Success:**
   ```
   SMSManager: Emergency SMS sent to: John Doe
   ```

   **❌ Permission Error:**
   ```
   SMSManager: SMS permission not granted
   ```
   → Fix: Grant SMS permission

   **❌ No Contacts:**
   ```
   No emergency contacts found!
   ```
   → Fix: Add contacts and mark as Primary

   **❌ Exception Error:**
   ```
   SMSManager: Failed to send SMS to John Doe
   java.lang.SecurityException: ...
   ```
   → Fix: Check permissions

---

### ✅ Step 7: Verify SMS is Actually Sent

**Check your phone's SMS app:**
1. Open **Messages** app
2. Look for outgoing messages
3. Check if SMS was sent to the contact number

**If message shows in Sent folder:**
- SMS was sent successfully! ✅
- The recipient might not have received it due to network

**If no message in Sent folder:**
- SMS was not sent
- Check permissions again

---

### ✅ Step 8: Check Network/Carrier

**SMS requires:**
- ✅ Active SIM card
- ✅ Network signal
- ✅ SMS plan (or balance for prepaid)

**Try:**
- Send a regular SMS manually to verify network works
- If manual SMS works, app SMS should work too

---

## 🐛 Common Issues & Solutions

### Issue 1: "No emergency contacts found!"

**Problem:** No contacts marked as Primary

**Solution:**
1. Add at least one contact
2. **MUST check "Primary Contact" checkbox**
3. Save
4. Try SOS again

---

### Issue 2: SMS Permission Denied

**Problem:** App doesn't have SMS permission

**Solution:**
```
Method 1 (In App):
- Open app
- App should ask for SMS permission
- Tap "Allow"

Method 2 (Settings):
- Settings → Apps → ResQHer → Permissions
- SMS → Allow
```

---

### Issue 3: Location Permission Denied

**Problem:** Can't get location to send

**Solution:**
- Settings → Apps → ResQHer → Permissions
- Location → "Allow only while using the app"

**Note:** Even if location fails, SMS should still send (with "Location unavailable")

---

### Issue 4: SMS Sent But Not Received

**If Logcat shows "SMS sent" but contact didn't receive:**

**Possible reasons:**
1. **Wrong phone number format**
   - Add country code: `+919876543210`

2. **Recipient's phone is off**
   - SMS will deliver when phone is on

3. **Network issue**
   - Try again in area with better signal

4. **DND/Do Not Disturb**
   - SMS might be blocked by carrier

5. **Message too long**
   - Current message is ~150 characters, should work
   - Some carriers limit to 160 characters

---

### Issue 5: App Crashes When Pressing SOS

**Check Logcat for error**

**Common cause:** Missing permissions

**Solution:**
- Grant all permissions:
  - Location
  - SMS
  - Phone
- Restart app

---

## 🧪 Step-by-Step Testing Guide

### Test 1: Add Contact and Check Database

1. Open app → Emergency Contacts
2. Add a contact (your number)
3. Mark as "Primary Contact" ✅
4. Save
5. **Verify:** You should see contact in list with "PRIMARY" badge

### Test 2: Check Permissions

1. Before testing SOS:
   - Settings → Apps → ResQHer → Permissions
   - **SMS:** Allowed ✅
   - **Location:** Allowed ✅
   - **Phone:** Allowed ✅

### Test 3: Activate SOS

1. Open app → Emergency SOS
2. Press SOS button
3. **Watch countdown:** 5... 4... 3... 2... 1...
4. **Don't cancel** - let it finish

### Test 4: Check Logcat

1. In Android Studio → Logcat
2. Filter: `SMSManager`
3. Look for: `Emergency SMS sent to: [Name]`

### Test 5: Check Phone SMS App

1. Open phone Messages app
2. Check Sent folder
3. Should see message to your contact number

### Test 6: Check Received SMS

1. If you used your own number
2. You should receive SMS on same phone
3. SMS should contain:
   - "EMERGENCY ALERT!"
   - Your location or coordinates
   - Google Maps link

---

## 🔍 Debug Mode - Add Logging

If still not working, let's add debug messages.

**Check if contacts are being retrieved:**

In Logcat, filter by: `SOSActivity`

You should see logs when SOS is activated.

---

## 📱 Quick Test Script

**Follow this exactly:**

1. ✅ **Step 1:** Settings → Apps → ResQHer → Permissions → Allow ALL
2. ✅ **Step 2:** Open ResQHer app
3. ✅ **Step 3:** Emergency Contacts → Tap +
4. ✅ **Step 4:** Add your phone number with country code: `+919876543210`
5. ✅ **Step 5:** Check "Primary Contact" box
6. ✅ **Step 6:** Save
7. ✅ **Step 7:** Go back → Emergency SOS
8. ✅ **Step 8:** Press SOS button
9. ✅ **Step 9:** Wait for countdown to finish (don't cancel)
10. ✅ **Step 10:** Check Android Studio Logcat for: "Emergency SMS sent"
11. ✅ **Step 11:** Check phone Messages app for sent SMS

---

## 🆘 Still Not Working?

**Share this information:**

1. **What happens when you press SOS?**
   - Does countdown start?
   - Any error messages?
   - What does status text show?

2. **Check Logcat and share error:**
   - Open Logcat
   - Filter by `SMSManager` or `SOSActivity`
   - Copy any red error messages

3. **Verify contacts:**
   - How many contacts do you have?
   - Are they marked as "Primary"?
   - What's the phone number format?

4. **Permissions:**
   - Screenshot of app permissions from Settings

5. **Android version:**
   - What Android version is your phone?
   - Settings → About Phone → Android version

---

## 💡 Most Common Fix

**90% of the time, the issue is:**

1. ❌ No contacts added
2. ❌ Contact not marked as "Primary"
3. ❌ SMS permission not granted

**Solution:**
- Add contact with YOUR phone number
- ✅ Check "Primary Contact"
- Grant SMS permission
- Try again!

---

## ✅ Success Indicators

**You'll know it's working when:**

1. ✅ Countdown finishes
2. ✅ Status shows: "Emergency alerts sent!"
3. ✅ Toast message: "Emergency alerts sent to X contacts"
4. ✅ Logcat shows: "Emergency SMS sent to: [Name]"
5. ✅ SMS appears in phone's Messages app (Sent folder)
6. ✅ You receive the SMS (if you used your number)

---

**Follow these steps carefully and SMS should work!** 📱

Let me know which step fails and I'll help you fix it!
