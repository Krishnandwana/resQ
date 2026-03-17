# Database & Data Models

## Storage Technology

- Room Database (`AppDatabase`)
- SQLite file name: `resqher_database`
- Schema export: disabled (`exportSchema = false`)
- Migration behavior: `fallbackToDestructiveMigration()`

## Entities

### `EmergencyContact`

Fields:

- `id` (int, primary key, auto-generated)
- `name` (String)
- `phoneNumber` (String)
- `relationship` (String)
- `isPrimary` (boolean)

Table: `emergency_contacts`

## DAO

### `EmergencyContactDao`

Methods:

- `insert(EmergencyContact)`
- `update(EmergencyContact)`
- `delete(EmergencyContact)`
- `getAllContacts()` → `LiveData<List<EmergencyContact>>`
- `getPrimaryContacts()` → `List<EmergencyContact>`
- `deleteAll()`

Query behavior:

- Full list sorted by `isPrimary DESC, name ASC`.
- SOS uses only `isPrimary = 1` contacts.

## In-Memory / Non-DB Models

- `Helpline`: name, number, description, category (runtime static list)
- `SafetyTipSection`: section title/subtitle + list items
- `LegalRightsSection`: section title/subtitle + legal bullet items

## Threading Pattern

- UI-observed list uses `LiveData` in contacts screen.
- Insert/update/delete uses `ExecutorService` background thread.
- SOS reads primary contacts in background thread before UI updates.

## Data Integrity Notes

- Current contact validation is activity-level (not DB constraint level).
- Duplicate numbers are possible (no unique index).
- Destructive migration can wipe data on schema version changes.

## Suggested DB Enhancements

- Add unique constraint for `phoneNumber` (or composite key strategy).
- Add created/updated timestamps.
- Add soft delete and restore workflows.
- Add encrypted persistence if privacy requirement increases.
