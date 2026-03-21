# How Shake Functionality Actually Works (Code-Level Explanation)

This document explains the exact behavior of shake-to-SOS in ResQHer, based on the implementation in:

- `app/src/main/java/com/resqher/safety/utils/ShakeToSOSHelper.java`
- `app/src/main/java/com/resqher/safety/MainActivity.java`
- Activity lifecycle usage across screens (`onResume` / `onPause`)

---

## 1) Where Shake Is Implemented

The shake logic is centralized in `ShakeToSOSHelper`, which implements Android’s `SensorEventListener`.

That helper is instantiated in `MainActivity` and also in feature activities (e.g., contacts, helpline, safety tips, legal rights). Each activity starts/stops the listener in lifecycle callbacks:

- `onResume()` → `shakeToSOSHelper.start()`
- `onPause()` → `shakeToSOSHelper.stop()`

So shake detection is active while an app screen is visible/resumed.

---

## 2) Sensor Used

- Sensor type: `Sensor.TYPE_ACCELEROMETER`
- Registration rate: `SensorManager.SENSOR_DELAY_UI`

No special Android runtime permission is required for accelerometer events.

---

## 3) Trigger Math

On each accelerometer event:

1. Read raw acceleration axes: `x`, `y`, `z`.
2. Compute total acceleration magnitude and normalize by earth gravity.

Formula used:

$$
gForce = \frac{\sqrt{x^2 + y^2 + z^2}}{SensorManager.GRAVITY_EARTH}
$$

A shake candidate is accepted when:

- `gForce > 2.7f` (`SHAKE_THRESHOLD_G`), and
- time since previous accepted shake > `400 ms` (`SHAKE_DEBOUNCE_MS`).

---

## 4) Two-Stage Protection Against False Triggers

ResQHer uses **two timing guards**:

### Guard A: Shake Debounce (400 ms)

Prevents one physical shake from generating multiple immediate hits due to noisy consecutive sensor events.

### Guard B: SOS Cooldown (3500 ms)

Even after a valid shake, SOS launch is blocked if another SOS was triggered very recently:

- `SOS_TRIGGER_COOLDOWN_MS = 3500`
- tracked via static field `lastSOSTriggerAt`

Because this is `static`, the cooldown applies process-wide across helper instances in different activities.

---

## 5) What Happens After a Valid Shake

If cooldown allows it, helper executes:

1. `Toast`: **"Shake detected. Starting SOS..."**
2. Creates Intent to `SOSActivity`
3. Adds `Intent.FLAG_ACTIVITY_SINGLE_TOP`
4. Calls `activity.startActivity(intent)`

Then `SOSActivity` opens, and the normal SOS flow still requires user action (press SOS button + countdown) before SMS dispatch. In short:

- **Shake opens SOS screen**
- **Shake does not directly auto-send messages**

---

## 6) Enable/Disable Behavior

Shake feature state is saved in SharedPreferences:

- Pref file: `resqher_settings`
- Key: `shake_to_sos_enabled`
- Default value: `true`

Methods:

- `ShakeToSOSHelper.isEnabled(context)`
- `ShakeToSOSHelper.setEnabled(context, enabled)`

UI integration:

- `MainActivity` has a `SwitchCompat` (`shakeSosToggle`) in `activity_main.xml`.
- Toggle ON → helper starts and toast says enabled.
- Toggle OFF → helper stops and toast says disabled.

This state persists across app restarts.

---

## 7) Lifecycle Behavior (Important Practical Detail)

Since listener registration happens in activities’ `onResume` and unregistration in `onPause`:

- Shake works while user is inside app screens that wire the helper.
- Shake is not continuously active when app is backgrounded (unless a dedicated background component is added in future).

This is a battery-safe and privacy-friendly choice but limits always-on emergency triggering.

---

## 8) Edge Cases and Real-World Behavior

1. **Phone moved quickly in bag/pocket:** may exceed threshold; debounce/cooldown reduce repeats but cannot eliminate all false positives.
2. **User disabled shake toggle:** helper exits early and does nothing.
3. **No accelerometer available:** helper safely skips registration (`accelerometer == null`).
4. **Repeated aggressive shaking:** first valid event can open SOS, next events are gated by cooldown.

---

## 9) End-to-End Flow Summary

1. Activity resumes and registers accelerometer listener (if enabled).
2. Sensor emits events.
3. Helper computes gForce.
4. If `gForce > 2.7` and debounce passes, helper checks global SOS cooldown.
5. If cooldown passes, helper launches `SOSActivity`.
6. User then executes normal SOS activation pipeline.

---

## 10) Why This Design Is Sensible

The current shake implementation is intentionally conservative:

- Keeps emergency entry fast,
- avoids most accidental multi-triggers,
- gives user explicit control with a toggle,
- remains simple to maintain and debug.

For production hardening, future upgrades could include adaptive thresholds, multi-shake pattern validation, and optional background trigger support with stricter safeguards.
