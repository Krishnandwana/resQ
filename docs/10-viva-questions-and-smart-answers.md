# Viva Questions and Smart Answers

This document helps you answer common questions from invigilators and project mentors with clear, confident, technical responses.

## How to Use This

- Read answers as structure, not a script.
- Start with one-line summary, then add 2-3 technical points.
- If challenged, refer to implementation files and tested behavior.

---

## A) Problem Statement and Scope

### 1) What problem does your project solve?
**Answer:** This app reduces emergency response delay for women in distress by combining one-tap SOS, automatic contact alerts, location sharing, and verified helpline access in a single mobile flow.

### 2) Why did you choose this project?
**Answer:** Safety apps are most useful when they are fast under stress. We focused on reducing cognitive load by keeping actions simple: trigger SOS, notify trusted contacts, and provide immediate call/share options.

### 3) Who are the target users?
**Answer:** Primary users are women needing rapid emergency support. Secondary users include guardians/trusted contacts who receive SOS alerts.

### 4) What is out of scope currently?
**Answer:** Cloud backend, cross-device sync, multilingual content management, and advanced analytics are not included yet. Current version is local-first for reliability and simplicity.

---

## B) Architecture and Technology Choices

### 5) Why Java instead of Kotlin?
**Answer:** Java was selected for consistency with team familiarity and timeline constraints. The architecture remains modular, so migration to Kotlin can be phased later.

### 6) Why Room database?
**Answer:** Room gives structured local storage, compile-time query checks, and easy lifecycle integration with LiveData for contact list updates.

### 7) Why local storage only?
**Answer:** Local-first design reduces dependency on network/backend during emergencies and improves privacy because sensitive contact data stays on-device.

### 8) Why use Google Play Services for location?
**Answer:** Fused location provides practical accuracy and battery-aware behavior. We request current location first and use last-known fallback to improve success rate.

### 9) Explain your app layers.
**Answer:** Presentation layer has activities/adapters/layouts, data layer is Room, utility layer handles permissions/location/SMS/shake detection, and service layer supports foreground location tracking.

---

## C) SOS and Emergency Flow

### 10) Describe SOS flow end-to-end.
**Answer:** User taps SOS, countdown starts, location is fetched, primary contacts are loaded, emergency SMS is sent, status is shown, and user can additionally share details via WhatsApp.

### 11) Why countdown before SOS?
**Answer:** It prevents accidental triggers while keeping emergency action quick. User can cancel during countdown.

### 12) What if location is unavailable?
**Answer:** We still send emergency SMS with fallback text indicating location unavailability so alerting does not fail completely.

### 13) What if SMS text is too long?
**Answer:** We split messages using multipart SMS so location links and details are not truncated by carrier length limits.

### 14) Why only primary contacts receive SOS?
**Answer:** It ensures focused emergency broadcast to the most relevant people and avoids alert fatigue/spam.

### 15) Does SOS auto-reset?
**Answer:** Current behavior keeps SOS state active until user cancellation, which avoids confusion after alert dispatch.

---

## D) Permissions and Security

### 16) Which runtime permissions are critical?
**Answer:** Call phone, send SMS, fine/coarse location, and contacts. Without them, the app degrades gracefully and shows user feedback.

### 17) How do you handle denied permissions?
**Answer:** We check before each sensitive action and prompt again when required. Features fail safely with clear toasts instead of crashes.

### 18) Is user data secure?
**Answer:** Data is local in Room and not synced to cloud in current version. Privacy risk is reduced, but stronger encryption can be added as next phase.

### 19) Any ethical concerns?
**Answer:** Yes. Emergency tools must avoid false confidence. We communicate permission dependency and suggest testing setup so users know limits.

---

## E) UI/UX Decisions

### 20) Explain your color hierarchy.
**Answer:** Soft base colors create calm context, white cards provide structure, red is reserved for emergency action, and green indicates safe/active status.

### 21) Why isolate the SOS card visually?
**Answer:** In emergencies, dominant focus reduces decision time. Extra spacing and high contrast help quick recognition.

### 22) Why quick actions on home screen?
**Answer:** They reduce taps for common urgent tasks: call police, share current location, open SOS immediately.

### 23) How did you handle notch/cutout overlap?
**Answer:** Root layouts use safe-area handling so headers remain visible and not blocked by camera cutouts.

---

## F) Data and Content

### 24) Are helpline numbers dynamic?
**Answer:** Current version uses static curated entries in code for reliability. A future backend can make updates region-aware.

### 25) Why include legal rights and safety tips?
**Answer:** Emergency response is reactive; education is preventive. Both are needed for meaningful safety support.

### 26) How is contact CRUD implemented?
**Answer:** Add/edit/delete actions run on background executor, and list updates are observed via LiveData from Room.

---

## G) Testing, Reliability, and Failure Handling

### 27) How did you test the app?
**Answer:** We validated build/install flows, real-device feature checks, permission-denial paths, SOS messaging behavior, and UI usability on current target device.

### 28) What are known limitations?
**Answer:** Automated test coverage is limited, lint has pending issues, and emergency number defaults are region-specific.

### 29) How do you handle no contacts configured?
**Answer:** SOS flow blocks send and prompts the user to add emergency contacts first.

### 30) What if WhatsApp is not installed?
**Answer:** We catch the failure and show a user-friendly message instead of crashing.

---

## H) Mentor-Level Deep Questions

### 31) Why not use WorkManager for SOS sending?
**Answer:** SOS requires immediate foreground user-triggered action, so direct execution is faster. WorkManager is better for guaranteed deferred/background jobs.

### 32) Why fallbackToDestructiveMigration in Room?
**Answer:** It simplifies early-stage development, but production should replace it with proper migration scripts to preserve user data.

### 33) How would you scale this to production?
**Answer:** Add backend for profile/settings sync, audit logging, secure messaging fallback, feature flags, analytics, and robust migration/testing pipeline.

### 34) How would you improve anti-false-trigger logic?
**Answer:** Add configurable countdown, optional press-and-hold gesture, and context checks (screen lock, motion pattern) with user tuning.

### 35) How would you improve location reliability further?
**Answer:** Add timeout telemetry, passive updates cache, optional service warm-up before SOS, and fallback to coarse location when fine fails.

### 36) What about internationalization?
**Answer:** Move all UI text/content into resource files, externalize legal/helpline datasets by locale, and add language selection with regional defaults.

---

## I) Project Management and Evaluation

### 37) What was your biggest technical challenge?
**Answer:** Ensuring SOS reliability across permission/device states. We solved this with explicit checks, location fallback, multipart SMS, and clearer state handling.

### 38) What did you improve in recent iterations?
**Answer:** Reliable location fetch, non-resetting SOS state, functional quick actions, safe-area UI handling, and stronger visual hierarchy for emergency focus.

### 39) If given 2 more months, what next?
**Answer:** Add automated tests, proper Room migrations, backend-configurable helplines, encrypted local storage, and robust telemetry for SOS success rates.

### 40) Why is this project academically valuable?
**Answer:** It demonstrates practical Android engineering across UI/UX, permissions, geolocation, telephony, local persistence, lifecycle handling, and safety-oriented product design.

---

## 30-Second Pitch Answer (Memorize)

ResQHer is a local-first Android safety app focused on reducing emergency response time. It combines SOS countdown, location-aware SMS alerts to primary contacts, direct police calling, and support resources in one flow. We prioritized reliability under stress through permission-aware fallbacks, multipart SMS, and clear action hierarchy. The current version is production-oriented in structure, with future scope for cloud sync, analytics, and stronger security hardening.
