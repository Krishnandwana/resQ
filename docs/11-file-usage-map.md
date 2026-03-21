# File Usage Map (What Each File Is Used For)

This document explains what each important file/folder in the ResQHer project is used for.

## 1) Root-Level Project Files

- `build.gradle`  
  Top-level Gradle config; defines plugin repositories, Android Gradle plugin version, and root `clean` task.

- `settings.gradle`  
  Declares included modules (`:app`) and global repositories for dependency resolution.

- `gradle.properties`  
  Build-time Gradle options and JVM tuning flags.

- `gradlew.bat`  
  Windows Gradle wrapper entrypoint to build/run tasks without installing global Gradle.

- `local.properties`  
  Machine-local Android SDK path configuration (developer-specific).

- `README.md`  
  Primary project overview, key features, architecture summary, and setup guidance.

- `PROJECT_OVERVIEW.md`  
  Extended summary useful for academic/project report context.

- `QUICK_START.md`  
  Fast setup instructions for first run.

- `SETUP_GUIDE.md`  
  Detailed setup/build/test/troubleshooting instructions.

- `HOW_TO_RUN_IN_ANDROID_STUDIO.md`  
  Android Studio workflow guide.

- `VSCODE_SETUP.md`  
  VS Code setup and task workflow for this project.

- `SMS_WHATSAPP_FEATURE.md`  
  Product behavior notes for dual alert strategy (SMS + WhatsApp).

- `DEBUG_SMS_NOT_WORKING.md`  
  Debug checklist specifically for SMS delivery failures.

- `FIX_SDK_ERROR.md`  
  Notes for SDK/Gradle compatibility fixes.

- `START_HERE.txt`  
  Entry pointer for new contributors.

## 2) Module Build + Manifest

- `app/build.gradle`  
  App module build config: SDK versions, dependencies, Java compatibility, and build types.

- `app/src/main/AndroidManifest.xml`  
  Central declaration of permissions, activities, service, app theme, and Maps API key metadata.

## 3) Java Source Files (What Each Class Does)

### `app/src/main/java/com/resqher/safety`

- `MainActivity.java`  
  Home/dashboard controller. Handles navigation, quick actions (call police/share location/open SOS), and shake-to-SOS toggle state.

### `app/src/main/java/com/resqher/safety/activities`

- `SOSActivity.java`  
  Core emergency flow: countdown, cancel, location fetch, emergency SMS dispatch, WhatsApp sharing, and emergency feedback UI.

- `EmergencyContactsActivity.java`  
  Contact management screen (CRUD), contact picker import, and primary-contact selection.

- `HelplineActivity.java`  
  Displays predefined helpline list and supports one-tap direct call.

- `SafetyTipsActivity.java`  
  Displays structured safety education content sections.

- `LegalRightsActivity.java`  
  Displays women legal-rights awareness sections.

### `app/src/main/java/com/resqher/safety/adapters`

- `ContactsAdapter.java`  
  RecyclerView adapter for emergency contacts list; handles edit/delete UI callbacks.

- `HelplineAdapter.java`  
  RecyclerView adapter for helplines; binds call action for each helpline item.

- `SafetyTipsAdapter.java`  
  RecyclerView adapter for safety tips sections with bullet-point rendering.

- `LegalRightsAdapter.java`  
  RecyclerView adapter for legal rights sections with bullet-point rendering.

### `app/src/main/java/com/resqher/safety/database`

- `AppDatabase.java`  
  Room singleton database provider (`resqher_database`).

- `EmergencyContactDao.java`  
  DAO for contact insert/update/delete/read operations, including `getPrimaryContacts()` for SOS recipients.

### `app/src/main/java/com/resqher/safety/models`

- `EmergencyContact.java`  
  Room entity for trusted contacts (`name`, `phoneNumber`, `relationship`, `isPrimary`).

- `Helpline.java`  
  POJO model for helpline dataset.

- `SafetyTipSection.java`  
  Immutable model for safety-tip section rendering.

- `LegalRightsSection.java`  
  Immutable model for legal-rights section rendering.

### `app/src/main/java/com/resqher/safety/services`

- `LocationTrackingService.java`  
  Foreground service for periodic location updates and broadcasting coordinates.

### `app/src/main/java/com/resqher/safety/utils`

- `PermissionManager.java`  
  Central runtime permission checks and permission request API.

- `LocationHelper.java`  
  Fetches high-accuracy current location, falls back to last known location, and formats address + Maps URL.

- `SMSManager.java`  
  Sends emergency SMS (single or multipart) to recipients.

- `ShakeToSOSHelper.java`  
  Accelerometer listener that detects shake gestures and launches `SOSActivity` with debounce + cooldown control.

## 4) Layout Files (`app/src/main/res/layout`)

- `activity_main.xml` – Dashboard UI and shake toggle (`shakeSosToggle`).
- `activity_sos.xml` – SOS screen layout (countdown/status/buttons).
- `activity_emergency_contacts.xml` – Contacts list + add button layout.
- `activity_helpline.xml` – Helpline list layout.
- `activity_safety_tips.xml` – Safety tips list container.
- `activity_legal_rights.xml` – Legal rights list container.
- `dialog_add_contact.xml` – Add/Edit contact dialog layout.
- `item_contact.xml` – One contact row UI.
- `item_helpline.xml` – One helpline row UI.
- `item_safety_tip.xml` – One safety-tip section row UI.
- `item_legal_right.xml` – One legal-right section row UI.

## 5) Values and XML Config Files

- `app/src/main/res/values/strings.xml` – User-facing text constants.
- `app/src/main/res/values/colors.xml` – Color palette tokens.
- `app/src/main/res/values/dimens.xml` – Spacing/size tokens.
- `app/src/main/res/values/themes.xml` – Material/AppCompat theme setup.
- `app/src/main/res/xml/backup_rules.xml` – Backup behavior config.
- `app/src/main/res/xml/data_extraction_rules.xml` – Data extraction policy config.

## 6) Build and Generated Directories

- `app/build/` and root `build/`  
  Generated artifacts, intermediates, reports, and APK outputs. Not source-of-truth for application logic.

## 7) Documentation Folder

- `docs/01` to `docs/10`  
  Structured project docs for overview, architecture, setup, permissions, database, troubleshooting, UI design, testing, and viva prep.

---

## Practical Navigation Order for New Developers

1. `README.md`
2. `app/src/main/AndroidManifest.xml`
3. `MainActivity.java`
4. `SOSActivity.java`
5. `ShakeToSOSHelper.java`
6. `EmergencyContactsActivity.java`
7. `LocationHelper.java` + `SMSManager.java`
8. `AppDatabase.java` + `EmergencyContactDao.java`
9. `docs/02-architecture-and-code-structure.md`
