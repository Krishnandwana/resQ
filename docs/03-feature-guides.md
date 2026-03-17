# Feature Guides

## 1) Home Dashboard

Implemented in `MainActivity` and `activity_main.xml`.

### Available actions

- Open SOS screen
- Open emergency contacts
- Open helplines
- Open safety tips
- Open legal rights
- Quick action: call police
- Quick action: share current location
- Quick action: open SOS directly

### Shake-to-SOS

- Controlled by `SwitchCompat` toggle.
- Backed by `ShakeToSOSHelper` + SharedPreferences.
- Automatically starts/stops with activity lifecycle.

---

## 2) SOS Flow

Implemented in `SOSActivity`.

### Sequence

1. User presses SOS button.
2. 5-second countdown starts.
3. User can cancel countdown.
4. On finish, app fetches location.
5. App loads primary contacts from DB.
6. App sends emergency SMS to all primary contacts.
7. App offers WhatsApp share for emergency context.

### Key behavior

- SOS state remains active after sending until user cancels/resets.
- If location fails, fallback text is sent (`Location unavailable`).
- If no primary contacts exist, user gets a blocking prompt.

---

## 3) Emergency Contacts Management

Implemented in `EmergencyContactsActivity` + Room.

### Capabilities

- Add contact manually.
- Import contact from device contacts.
- Edit contact details.
- Delete contact with confirmation.
- Mark contact as primary/non-primary.

### Validation

- Name required.
- Phone required (minimum length check).
- Relationship required.

---

## 4) Helpline Directory

Implemented in `HelplineActivity`.

### Categories included

- Emergency
- Women Safety
- Child Safety
- Senior Safety
- Mental Health
- Legal

### Action

- Tap `Call` to place a direct phone call.

---

## 5) Safety Tips & Legal Rights

Implemented in `SafetyTipsActivity` and `LegalRightsActivity`.

### Content strategy

- Structured in sections with subtitle + bullet points.
- Safety tips: awareness, travel, digital, emergency readiness.
- Legal rights: constitutional rights, domestic violence, POSH, IPC sections, legal aid.

---

## 6) Location Features

Implemented mainly in `LocationHelper` and `LocationTrackingService`.

### SOS location fetch

- `getCurrentLocation()` with high-accuracy request.
- Fallback to `getLastLocation()` when needed.
- Message includes geocoded address + Google Maps URL.

### Background tracking service

- Foreground service with notification channel.
- Receives periodic location updates.
- Broadcasts latitude/longitude via `LOCATION_UPDATE` intent.

---

## 7) Messaging

Implemented in `SMSManager`.

### Emergency SMS behavior

- Checks SMS permission before sending.
- Builds final message with emergency text + location.
- Uses `divideMessage()` and multipart sending for long payloads.

---

## 8) Calling

Used in `MainActivity`, `SOSActivity`, and `HelplineActivity`.

- Uses `Intent.ACTION_CALL` with explicit call permission checks.
- Police shortcut number currently set to `100`.
