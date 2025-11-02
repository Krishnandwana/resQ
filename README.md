# ResQHer - Women Safety Application

A comprehensive Android-based mobile application designed to provide rapid assistance and essential resources to women in distress.

## Overview

ResQHer is a Final Year Project that addresses the critical concern of women's safety by offering immediate support and educational resources. Built using Java in Android Studio, the application combines practical safety tools with informative content to empower women with both preventive measures and active emergency support.

## Key Features

### 1. Emergency SOS System
- **Quick SOS Activation**: 5-second countdown before activation to prevent accidental triggers
- **Automated Alerts**: Sends emergency SMS to all registered primary contacts
- **Real-time Location Sharing**: Includes current GPS coordinates and Google Maps link
- **Direct Police Call**: One-tap access to call emergency services (100)
- **Vibration Alerts**: Physical feedback during emergency situations

### 2. GPS Location Tracking
- **Real-time Location Updates**: Continuous location tracking using Google Play Services
- **Foreground Service**: Background location tracking for safety monitoring
- **Address Geocoding**: Converts GPS coordinates to readable addresses
- **Location Sharing**: Share precise location via SMS with emergency contacts

### 3. Emergency Contacts Management
- **Contact Database**: Store multiple emergency contacts with Room database
- **Primary Contacts**: Mark important contacts for priority alerts
- **Contact Details**: Store name, phone number, and relationship information
- **Easy Management**: Add, edit, and delete contacts with simple UI
- **CRUD Operations**: Full create, read, update, delete functionality

### 4. Helpline Numbers Directory
- **Verified Helplines**: Comprehensive list of emergency helpline numbers
- **Categorized**: Organized by Emergency, Women Safety, Child Safety, Mental Health, and Legal
- **One-tap Calling**: Direct call functionality to any helpline
- **Key Numbers Include**:
  - Police: 100
  - Women Helpline: 1091
  - Ambulance: 108
  - National Commission for Women: 7827170170
  - Mental Health Support
  - Legal Aid Services

### 5. Safety Education
- **Safety Tips**: Comprehensive safety guidelines covering:
  - General alertness and awareness
  - Travel safety protocols
  - Digital safety measures
  - Self-defense basics
  - Night safety precautions
  - Public space awareness
  - Home security
  - Emergency preparedness

### 6. Legal Rights Information
- **Know Your Rights**: Detailed information about women's legal rights in India
- **Key Legislation**: Constitutional rights, protection laws, workplace rights
- **Important Acts**:
  - Domestic Violence Act, 2005
  - Dowry Prohibition Act, 1961
  - Sexual Harassment at Workplace Act, 2013
  - Relevant IPC Sections
- **Emergency Procedures**: Information about Zero FIR, legal aid, and complaint filing

## Technical Architecture

### Technology Stack
- **Language**: Java
- **IDE**: Android Studio
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Build System**: Gradle

### Libraries & Dependencies
- **AndroidX Libraries**: AppCompat, Material Design Components, ConstraintLayout
- **Google Play Services**: Location services, Maps API
- **Room Database**: Local data persistence for emergency contacts
- **Lifecycle Components**: ViewModel and LiveData for reactive UI
- **RecyclerView**: Efficient list displays for contacts and helplines

### Architecture Components
1. **Activities**:
   - MainActivity (Dashboard)
   - SOSActivity (Emergency response)
   - EmergencyContactsActivity (Contact management)
   - HelplineActivity (Helpline directory)
   - SafetyTipsActivity (Educational content)
   - LegalRightsActivity (Legal information)

2. **Services**:
   - LocationTrackingService (Background location tracking)

3. **Database**:
   - AppDatabase (Room database)
   - EmergencyContactDao (Data access object)

4. **Utilities**:
   - PermissionManager (Runtime permissions)
   - LocationHelper (GPS and geocoding)
   - SMSManager (Emergency messaging)

5. **Adapters**:
   - ContactsAdapter (Emergency contacts list)
   - HelplineAdapter (Helpline numbers list)

## Permissions Required

The application requires the following permissions:
- `CALL_PHONE`: Direct emergency calling
- `SEND_SMS`: Send emergency alerts
- `ACCESS_FINE_LOCATION`: Precise location tracking
- `ACCESS_COARSE_LOCATION`: Approximate location
- `ACCESS_BACKGROUND_LOCATION`: Background tracking
- `READ_CONTACTS`: Contact selection (optional)
- `INTERNET`: Maps and network services
- `VIBRATE`: Alert notifications
- `FOREGROUND_SERVICE`: Background location service
- `WAKE_LOCK`: Emergency alerts

## Setup Instructions

### Prerequisites
1. Android Studio (latest version recommended)
2. JDK 8 or higher
3. Android SDK with API 24+
4. Google Play Services

### Installation Steps

1. **Clone/Download the Project**
   ```bash
   git clone <repository-url>
   cd resQ
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Click "Open an Existing Project"
   - Navigate to the ResQ directory

3. **Configure Google Maps API**
   - Get a Google Maps API key from [Google Cloud Console](https://console.cloud.google.com/)
   - Open `app/src/main/AndroidManifest.xml`
   - Replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` with your actual API key

4. **Sync Gradle**
   - Click "Sync Now" when prompted
   - Wait for dependencies to download

5. **Build the Project**
   ```bash
   ./gradlew build
   ```

6. **Run on Device/Emulator**
   - Connect Android device or start emulator
   - Click "Run" button in Android Studio
   - Select target device

## Usage Guide

### First Time Setup
1. Launch the application
2. Grant required permissions when prompted
3. Add emergency contacts via "Emergency Contacts" option
4. Mark primary contacts for SOS alerts
5. Test SOS functionality (cancel before completion to avoid false alarms)

### Emergency SOS
1. Open the app and tap "Emergency SOS"
2. Press the large red SOS button
3. A 5-second countdown begins
4. Cancel if pressed accidentally
5. After countdown, alerts are sent automatically
6. Location is shared with all primary contacts
7. Use "Call Police" button for immediate police assistance

### Managing Contacts
1. Tap "Emergency Contacts" from main screen
2. Use "+" button to add new contact
3. Fill in name, phone, relationship
4. Check "Primary Contact" for SOS alerts
5. Tap contact to edit or delete

### Accessing Helplines
1. Select "Helpline Numbers" from main menu
2. Browse categorized helplines
3. Tap "Call" button to dial directly

## Project Structure

```
resQ/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/resqher/safety/
│   │       │   ├── activities/          # Activity classes
│   │       │   ├── adapters/           # RecyclerView adapters
│   │       │   ├── database/           # Room database
│   │       │   ├── models/             # Data models
│   │       │   ├── services/           # Background services
│   │       │   ├── utils/              # Helper utilities
│   │       │   └── MainActivity.java   # Main entry point
│   │       ├── res/
│   │       │   ├── layout/             # XML layouts
│   │       │   ├── values/             # Strings, colors, themes
│   │       │   ├── drawable/           # Icons and images
│   │       │   └── xml/                # Configuration files
│   │       └── AndroidManifest.xml     # App configuration
│   └── build.gradle                    # App-level build config
├── gradle/                              # Gradle wrapper
├── build.gradle                         # Project-level build config
├── settings.gradle                      # Project settings
└── README.md                            # This file
```

## Key Features Implementation

### SOS System Flow
1. User presses SOS button
2. 5-second countdown starts (with cancel option)
3. Location service retrieves GPS coordinates
4. Address geocoding converts to readable location
5. SMS messages prepared with location link
6. Messages sent to all primary contacts
7. Vibration pattern activated
8. Confirmation displayed to user

### Database Schema
```java
EmergencyContact {
    int id (Primary Key)
    String name
    String phoneNumber
    String relationship
    boolean isPrimary
}
```

## Security Considerations

- All contact data stored locally using Room database
- No cloud storage of personal information
- Permissions requested only when needed
- SMS content includes emergency keywords for quick identification
- Location sharing only during SOS activation

## Future Enhancements

Potential features for future versions:
- Audio/Video recording during emergency
- Shake gesture for SOS activation
- Multi-language support
- Integration with local police stations
- Community safety features
- Fake call feature for safety
- Safe route suggestions
- Check-in/check-out functionality
- Integration with wearable devices

## Testing

### Unit Testing
```bash
./gradlew test
```

### Instrumented Testing
```bash
./gradlew connectedAndroidTest
```

### Manual Testing Checklist
- [ ] SOS activation and cancellation
- [ ] Contact CRUD operations
- [ ] Permission handling
- [ ] Location tracking accuracy
- [ ] SMS delivery
- [ ] Phone call functionality
- [ ] UI responsiveness
- [ ] Background service stability

## Troubleshooting

### Common Issues

1. **Location not working**
   - Ensure location services are enabled
   - Check GPS permissions granted
   - Verify Google Play Services installed

2. **SMS not sending**
   - Confirm SMS permission granted
   - Check phone has network coverage
   - Verify contact numbers are valid

3. **App crashes on startup**
   - Clear app data and cache
   - Reinstall application
   - Check minimum Android version

4. **Google Maps not loading**
   - Verify API key is correct
   - Enable Maps SDK in Google Cloud Console
   - Check internet connection

## Contributing

This is an academic Final Year Project. For suggestions or improvements:
1. Document the feature/bug
2. Provide detailed description
3. Include screenshots if applicable

## License

This project is developed as an educational Final Year Project.

## Acknowledgments

- Android Developer Documentation
- Material Design Guidelines
- Google Maps Platform
- Stack Overflow Community
- Women Safety Organizations in India

## Contact & Support

For queries regarding this project:
- Project Type: Final Year Project
- Domain: Women Safety & Security
- Technology: Android Application Development

## Disclaimer

This application is designed as a safety tool and educational project. It should complement, not replace, official emergency services. Always contact local authorities (Police: 100) in case of immediate danger.

## Version History

### Version 1.0 (Current)
- Initial release
- Core SOS functionality
- Emergency contacts management
- Helpline directory
- Safety tips and legal rights
- GPS location tracking
- SMS alerts

---

**ResQHer** - Empowering Women Through Technology

*"Your Safety, Our Priority"*
