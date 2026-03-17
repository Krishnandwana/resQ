# Permissions & Security Notes

## Declared Permissions

From `AndroidManifest.xml`:

- `android.permission.CALL_PHONE`
- `android.permission.SEND_SMS`
- `android.permission.READ_CONTACTS`
- `android.permission.ACCESS_FINE_LOCATION`
- `android.permission.ACCESS_COARSE_LOCATION`
- `android.permission.ACCESS_BACKGROUND_LOCATION`
- `android.permission.INTERNET`
- `android.permission.ACCESS_NETWORK_STATE`
- `android.permission.VIBRATE`
- `android.permission.FOREGROUND_SERVICE`
- `android.permission.FOREGROUND_SERVICE_LOCATION`
- `android.permission.WAKE_LOCK`

## Runtime Permission Management

Implemented in `PermissionManager`.

### Required runtime set

- Call phone
- Send SMS
- Fine location
- Coarse location
- Read contacts

### Usage patterns

- `checkPermissions(context)` for all-at-once validation
- `requestPermissions(activity)` for request flow
- `hasCallPermission`, `hasSMSPermission`, `hasLocationPermission` for targeted checks

## Security & Privacy Posture

- Contact data stored locally in Room database.
- No cloud sync implemented.
- SMS/calls are user-device native actions.
- Geolocation used for emergency workflows and optional sharing.

## Sensitive Data Touchpoints

- Contact names and phone numbers
- Relationship metadata
- Emergency location details in SMS/WhatsApp messages

## Recommended Hardening (Future)

- Encrypt local DB or sensitive fields.
- Add explicit privacy policy screen.
- Add audit logs for SOS action events.
- Add finer-grained permission rationale dialogs.
- Consider configurable regional emergency numbers.

## Operational Cautions

- If SMS permission is denied, emergency SMS will fail silently except logs/toasts.
- If location permission is denied, emergency messages may send without coordinates.
- Ensure phone numbers are saved with country code for reliable delivery.
