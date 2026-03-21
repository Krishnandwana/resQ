# ResQHer: Design, Implementation, and Critical Analysis of a Mobile Safety Response System

## Abstract

Women’s safety remains a persistent public safety challenge in many urban and semi-urban contexts, where delayed reporting, fragmented emergency communication, and limited access to immediate support often increase risk. This paper presents a research-style technical study of **ResQHer**, an Android-based women safety application implemented in Java. The app combines emergency SOS activation, location-aware alerts, trusted-contact communication, helpline access, and awareness content in a single interface. The study analyzes the system architecture, core emergency workflow, sensor-assisted interaction (shake-to-SOS), data model, permission model, and implementation trade-offs. 

Rather than proposing a purely theoretical model, this paper evaluates an operational codebase and discusses practical constraints such as runtime permissions, sensor false positives, SMS reliability, and lifecycle behavior in Android activities. Findings indicate that ResQHer’s strongest design choices are its low-friction emergency workflow, offline-capable SMS fallback, and direct integration of educational/legal support content. However, opportunities remain for stronger reliability guarantees (delivery confirmations), privacy-hardening, background-trigger extensibility, and automated testing. The paper concludes with an incremental improvement roadmap suitable for final-year project progression into production-grade safety tooling.

**Keywords:** women safety, Android, SOS, mobile emergency response, location sharing, shake detection, SMS alerts, HCI for safety-critical apps.

---

## 1. Introduction

Mobile phones have become the most personal and persistent computing device in daily life, making them a natural medium for personal safety systems. Traditional emergency support workflows generally assume that users can place a call, communicate context, and share location under stress. In real emergency situations, these assumptions often fail. Time pressure, panic, constrained mobility, and poor situational control can reduce a person’s ability to complete multi-step communication tasks.

ResQHer addresses this gap by combining:

1. **Rapid escalation controls** (single-tap SOS with countdown),
2. **Automated communication** (SMS broadcast to trusted primary contacts),
3. **Contextual data** (location + maps link),
4. **Fallback and redundancy** (SMS first, optional WhatsApp share), and
5. **Preventive support** (safety tips + legal rights + helpline directory).

The problem is not only technical; it is also interactional. A safety system must be fast enough for emergencies, but robust enough to prevent accidental activation. It must guide users while minimizing cognitive load. It must work in constrained connectivity and permission states. This paper studies how ResQHer handles these realities through its architecture and implementation.

---

## 2. Research Objectives

This paper is organized around three objectives:

1. **Architectural Objective:** Analyze how the app’s layered structure (activities, utilities, database, service) supports emergency response.
2. **Functional Objective:** Evaluate emergency response pathways (manual SOS, shake-based SOS, SMS dispatch, location acquisition).
3. **Critical Objective:** Identify strengths, risks, and practical improvement opportunities for reliability, privacy, and scalability.

---

## 3. System Architecture Overview

ResQHer follows a lightweight modular architecture suitable for Android final-year projects:

- **Presentation layer:** Activities + RecyclerView adapters + XML layouts
- **Data layer:** Room database for emergency contacts
- **Utility layer:** Permission, location, SMS, and shake helper classes
- **Service layer:** Foreground location tracking service

### 3.1 Key Components

- `MainActivity`: App entry point, navigation hub, quick actions, shake toggle.
- `SOSActivity`: Core emergency pipeline (countdown → location → contact lookup → SMS alerts).
- `EmergencyContactsActivity`: CRUD for trusted contacts and primary-contact designation.
- `HelplineActivity`: Curated emergency/legal/mental health helplines.
- `SafetyTipsActivity` and `LegalRightsActivity`: Awareness and preparedness modules.
- `ShakeToSOSHelper`: Accelerometer-driven SOS trigger with debounce/cooldown.
- `LocationHelper`: High-accuracy current location with fallback strategy.
- `SMSManager`: Multipart SMS send logic for long emergency messages.

### 3.2 Why This Architecture Works for the Use Case

The architecture reflects an emergency-first design:

- Core emergency operations are separated into utility classes, reducing UI code complexity.
- Contact data persistence is local (Room), enabling offline readiness.
- Trigger mechanisms are redundant: manual SOS and shake gesture.
- High-value user flows are accessible from the home dashboard in one interaction.

This is a pragmatic design for Android constraints and student project scope.

---

## 4. Emergency Response Workflow Analysis

### 4.1 Manual SOS Flow

The manual flow in `SOSActivity` includes a **5-second cancelable countdown**. This protects against accidental activation while maintaining urgency.

Operational sequence:

1. User presses SOS.
2. UI enters active state (`isSOSActivated = true`), cancel button appears.
3. Device vibrates for immediate tactile confirmation.
4. Countdown completes.
5. App fetches location via `LocationHelper`.
6. App loads primary contacts from Room DAO.
7. App sends SMS to all primary contacts using `SMSManager`.
8. UI confirms dispatch and enables optional WhatsApp share.

### 4.2 Communication Redundancy

ResQHer prioritizes **SMS as primary emergency channel** because it can operate without internet. WhatsApp is optional and user-driven. This dual-path strategy is a practical reliability pattern:

- **SMS path:** lower dependency, automatic, cellular network-based.
- **WhatsApp path:** richer context and convenience, but internet-dependent and manual.

### 4.3 Contact Prioritization Strategy

The DAO method `getPrimaryContacts()` ensures alerts target pre-selected trusted contacts. This reduces alert noise and avoids irrelevant recipients during emergency execution.

---

## 5. Shake-to-SOS Interaction Model

Shake detection is implemented through Android accelerometer events (`Sensor.TYPE_ACCELEROMETER`) in `ShakeToSOSHelper`.

### 5.1 Signal Processing Logic

The helper computes normalized g-force:

$$
\text{gForce} = \frac{\sqrt{x^2 + y^2 + z^2}}{g}
$$

where $g \approx 9.81\ \text{m/s}^2$.

A shake is considered valid when:

- `gForce > 2.7` (threshold), and
- elapsed time from last shake exceeds `400 ms` (debounce).

If valid, `triggerSOSIfAllowed()` enforces an additional global cooldown of `3500 ms` before opening `SOSActivity`.

### 5.2 Safety and Usability Balance

The design blends responsiveness and accidental-trigger control via:

- User-facing enable/disable toggle stored in SharedPreferences,
- Debounce at sensor-event level,
- Cooldown at SOS-trigger level,
- Lifecycle-aware listener registration (start on `onResume`, stop on `onPause`).

### 5.3 Important Implementation Characteristic

Shake detection is active while app activities are in foreground/resumed state. This is intentional for battery and control, but it means out-of-app shake triggering is not supported by default.

---

## 6. Data Model and Persistence

ResQHer uses Room with a single entity (`EmergencyContact`) for critical alerting data:

- `id`
- `name`
- `phoneNumber`
- `relationship`
- `isPrimary`

The DAO supports:

- insert, update, delete,
- LiveData stream for full contact list,
- synchronous primary-contact retrieval for SOS dispatch.

This minimal schema is sufficient for first-phase emergency alerting. It keeps CRUD complexity low and improves maintainability.

---

## 7. Permission and Security Posture

The app requests sensitive permissions: call, SMS, location, contacts, vibration, foreground service. Permission checks are centralized via `PermissionManager`, simplifying policy handling in activities.

### 7.1 Security-Relevant Positives

- Runtime checks gate high-risk actions (call/SMS/location).
- Emergency contacts are local-only via Room (no remote backend exposure).
- The app exposes no exported emergency activity endpoints to external apps.

### 7.2 Security and Privacy Gaps (Observed)

- Contact/location data is not encrypted at rest by default.
- SMS delivery outcomes are not tracked with sent/delivered intents.
- No explicit abuse-prevention telemetry for repeated triggers.
- Foreground-service location broadcast could be further hardened by explicit receiver scoping.

---

## 8. HCI and UX Evaluation for Safety-Critical Contexts

Safety-critical interfaces must optimize for low cognitive load and fast comprehension. ResQHer’s dashboard design supports this with:

- direct emergency entry point,
- quick call/share actions,
- prominent visual grouping,
- persistent preparedness modules.

### 8.1 Strong UX Decisions

- Countdown cancellation prevents accidental panic alerts.
- Vibration provides non-visual confirmation.
- Post-alert status text offers immediate reassurance.
- Helpline and legal modules reduce fragmentation of support resources.

### 8.2 UX Risks

- Under panic, users may still mis-tap or fail to complete optional WhatsApp send.
- If no primary contacts exist, alert flow fails at execution stage.
- Phone number validation is basic and not fully E.164-normalized.

---

## 9. Reliability Discussion

Reliability in emergency systems should be evaluated in degraded conditions. ResQHer is partially resilient:

- If high-accuracy fetch fails, location helper attempts last known location.
- SMS path remains functional without internet.
- If location unavailable, message still sends with fallback text.

However, full reliability requires additional instrumentation:

1. Sent/delivery receipts for SMS,
2. retry policy for transient failures,
3. contact health checks (invalid/undeliverable numbers),
4. explicit operational logs for post-incident diagnostics.

---

## 10. Comparative Positioning (Conceptual)

Compared with generic “panic button only” apps, ResQHer provides broader support by integrating prevention (tips), legal awareness, and emergency channels. Compared with cloud-heavy safety platforms, ResQHer has lower infrastructure complexity and better offline viability but fewer analytics and synchronization capabilities.

This suggests ResQHer is well-positioned as a **practical, deployable, educational emergency companion** at student-project maturity, with room to evolve into a stronger operational system.

---

## 11. Limitations of Current Implementation

1. **Foreground-only shake detection**: no background trigger path.
2. **No backend acknowledgment layer**: cannot confirm recipient interaction.
3. **Minimal automated testing coverage**: higher regression risk.
4. **Static content datasets**: helplines/legal info require manual updates.
5. **Region assumptions**: emergency numbers and legal text are India-centric.

These are acceptable for prototype scope but should be addressed for wider deployment.

---

## 12. Future Work Roadmap

### Phase 1: Reliability Hardening

- Add SMS sent/delivered pending intents and UI reporting.
- Add structured error telemetry and alert history timeline.
- Normalize and validate phone numbers to international formats.

### Phase 2: Privacy and Trust

- Encrypt Room data (e.g., SQLCipher/Encrypted storage approach).
- Add app lock and emergency access modes.
- Improve consent and data-retention controls.

### Phase 3: Scalability and Intelligence

- Optional secure cloud sync for contacts and incident logs.
- Context-aware trigger tuning (motion profile adaptation).
- Regional content packs for legal rights and helplines.

### Phase 4: Evaluation and Publication

- Conduct usability testing under simulated stress conditions.
- Compare response time and task completion against baseline call-only workflows.
- Publish measured outcomes for academic validation.

---

## 13. Conclusion

ResQHer demonstrates that a thoughtfully designed Android app can materially improve emergency response readiness by combining rapid activation, location-aware communication, and awareness resources in one system. Its implementation choices—especially countdown-mediated SOS, SMS-first reliability, and shake-trigger option—reflect practical awareness of real-world constraints.

The project’s core architecture is clear, maintainable, and suitable for iterative enhancement. While the current version does not yet deliver full production-grade observability and privacy guarantees, it establishes a strong and credible foundation for both academic evaluation and future real-world hardening.

In summary, ResQHer is not merely a feature collection; it is a coherent mobile safety workflow with clear potential for continued research, engineering refinement, and social impact.

---

## References (Indicative)

1. Android Developers documentation (Activity lifecycle, permissions, location APIs, sensors).  
2. Android Room persistence library guides.  
3. Public emergency communication and women safety helpline frameworks (India context).  
4. Human-computer interaction principles for safety-critical mobile applications.
