# Testing & Release Checklist

## A) Build & Static Checks

- [ ] `assembleDebug` passes
- [ ] `installDebug` passes on target device
- [ ] `testDebugUnitTest` passes
- [ ] `lintDebug` reviewed (errors addressed or documented)

## B) Permission Tests (Fresh Install)

- [ ] First-launch permission prompts shown
- [ ] Deny then grant path works correctly
- [ ] App behavior degrades gracefully when permissions are denied

## C) Core Functional Tests

### Home

- [ ] All resource tiles navigate correctly
- [ ] Quick actions work (call, share location, open SOS)
- [ ] Shake-to-SOS toggle persists and functions

### SOS

- [ ] Countdown starts and cancels correctly
- [ ] SOS sends to all primary contacts
- [ ] Message includes location/maps link when available
- [ ] SOS does not auto-reset unexpectedly
- [ ] WhatsApp share button works

### Contacts

- [ ] Add/edit/delete contact
- [ ] Contact picker import works
- [ ] Primary contact logic correctly affects SOS recipients

### Helplines

- [ ] List renders all categories
- [ ] Call action works with permission

### Content Modules

- [ ] Safety tips list renders correctly
- [ ] Legal rights list renders correctly

## D) Device Matrix (Recommended)

- [ ] Android 8/9 era device
- [ ] Android 12+ device
- [ ] Android 14/15/16 device
- [ ] At least one device with notch/cutout

## E) SMS/Location Reliability Tests

- [ ] Weak network scenario
- [ ] No GPS lock scenario
- [ ] Location permission denied scenario
- [ ] Long SMS content multipart behavior verified

## F) UI/UX Checks

- [ ] Home visual hierarchy: calm base + dominant SOS
- [ ] Safe area / notch spacing is correct
- [ ] Card spacing and text readability
- [ ] No clipping/overflow on smaller screens

## G) Release Readiness

- [ ] Replace placeholder Google Maps API key
- [ ] Review emergency numbers for target region
- [ ] Verify privacy policy and legal disclaimer
- [ ] Create signed release config
- [ ] Smoke test release build (`assembleRelease`)

## H) Post-Release Monitoring

- [ ] Gather user feedback on SOS reliability
- [ ] Track crash reports and ANRs
- [ ] Prioritize fixes for permission and SMS edge cases
