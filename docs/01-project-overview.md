# Project Overview

## What ResQHer Is

ResQHer is an Android women-safety app built in Java. It combines emergency response actions (SOS, SMS, call, location share) with supportive resources (helplines, safety tips, legal rights).

## Core Value

- Fast emergency alerting to trusted contacts.
- Clear access to emergency call numbers.
- Practical safety and legal awareness in one place.

## Platform & Stack

- Language: Java
- Platform: Android (min SDK 24, target SDK 35)
- Build: Gradle + Android Gradle Plugin 8.13.0
- UI: AndroidX + Material Components
- Data: Room (local SQLite)
- Location: Google Play Services Location API

## Main User Flows

1. Open app and grant permissions.
2. Add emergency contacts and mark primary contacts.
3. Trigger SOS from SOS screen or quick action.
4. App gets location and sends SMS to primary contacts.
5. User can call police and share emergency context through WhatsApp.

## Current Modules

- Home Dashboard (`MainActivity`)
- SOS Flow (`SOSActivity`)
- Emergency Contacts (`EmergencyContactsActivity`)
- Helplines (`HelplineActivity`)
- Safety Tips (`SafetyTipsActivity`)
- Legal Rights (`LegalRightsActivity`)
- Background Location Service (`LocationTrackingService`)

## Important Runtime Dependencies

- Device telephony/SMS capability for emergency SMS/calls.
- Runtime permissions for call, SMS, location, contacts.
- Google Maps API key configured in `AndroidManifest.xml` metadata.

## Intended End-User Outcome

The app should feel calm and structured during normal use, but make emergency actions immediate, visible, and reliable when needed.
