# Architecture & Code Structure

## High-Level Architecture

ResQHer follows a lightweight layered structure:

- **Presentation layer**: Activities + RecyclerView adapters + XML layouts.
- **Data layer**: Room database (`AppDatabase`, `EmergencyContactDao`, model entities).
- **Utility layer**: Permission, location, SMS, shake detection helpers.
- **Service layer**: Foreground location tracking service.

## Package Map

- `com.resqher.safety`
  - `MainActivity`
- `com.resqher.safety.activities`
  - `SOSActivity`
  - `EmergencyContactsActivity`
  - `HelplineActivity`
  - `SafetyTipsActivity`
  - `LegalRightsActivity`
- `com.resqher.safety.adapters`
  - `ContactsAdapter`, `HelplineAdapter`, `SafetyTipsAdapter`, `LegalRightsAdapter`
- `com.resqher.safety.database`
  - `AppDatabase`, `EmergencyContactDao`
- `com.resqher.safety.models`
  - `EmergencyContact`, `Helpline`, `SafetyTipSection`, `LegalRightsSection`
- `com.resqher.safety.services`
  - `LocationTrackingService`
- `com.resqher.safety.utils`
  - `PermissionManager`, `LocationHelper`, `SMSManager`, `ShakeToSOSHelper`

## Activity Responsibilities

- **MainActivity**
  - Entry point and navigation hub.
  - Handles quick actions: call police, share location, open SOS.
  - Handles shake-to-SOS toggle and permission onboarding.

- **SOSActivity**
  - 5-second SOS countdown and cancellation.
  - Gets location, fetches primary contacts, sends emergency SMS.
  - Supports police call and WhatsApp share flow.

- **EmergencyContactsActivity**
  - CRUD operations for trusted contacts.
  - Supports device contact picker import.
  - Uses Room + LiveData for list updates.

- **HelplineActivity**
  - Static categorized helpline dataset.
  - One-tap call action with permission checks.

- **SafetyTipsActivity / LegalRightsActivity**
  - Display structured educational content lists.

## Data Flow Example (SOS)

1. `SOSActivity` starts countdown.
2. `LocationHelper` returns address + maps URL.
3. `EmergencyContactDao.getPrimaryContacts()` returns recipients.
4. `SMSManager.sendEmergencySMS()` sends multipart SMS if needed.
5. UI updates with status + optional WhatsApp sharing.

## Resource Structure

- `res/layout`: activity, list item, and dialog XML files.
- `res/drawable`: icons and custom backgrounds.
- `res/values`: color, string, dimension, and theme tokens.

## Build Structure

- Root: `build.gradle`, `settings.gradle`, `gradle.properties`
- App module: `app/build.gradle`

## Architectural Notes

- Concurrency uses `ExecutorService` for DB/background tasks.
- DB migrations currently use `fallbackToDestructiveMigration()`.
- State persistence for shake toggle uses `SharedPreferences`.
