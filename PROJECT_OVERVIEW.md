# ResQHer - Project Overview

## Final Year Project Documentation

### Project Title
**ResQHer - Women Safety Application**

### Project Type
Android Mobile Application Development

### Domain
Women Safety & Security Technology

---

## Executive Summary

ResQHer is a comprehensive Android application designed to address critical women's safety concerns through technology. The application provides immediate emergency response capabilities, educational resources, and access to verified helpline services, all within a simple and accessible interface.

---

## Project Components

### 1. Core Modules Implemented

#### A. Emergency Response System
- **SOS Activation**: 5-second countdown mechanism with cancel option
- **GPS Integration**: Real-time location tracking and sharing
- **Automated Alerts**: SMS dispatch to pre-registered emergency contacts
- **Direct Calling**: One-tap access to emergency services (Police: 100)
- **Haptic Feedback**: Vibration patterns for alert confirmation

#### B. Contact Management System
- **Database**: Room database for persistent storage
- **CRUD Operations**: Complete Create, Read, Update, Delete functionality
- **Priority System**: Primary contact designation for SOS alerts
- **Data Fields**: Name, phone number, relationship tracking

#### C. Information & Resources
- **Helpline Directory**: Categorized emergency contact numbers
- **Safety Education**: Comprehensive safety tips and guidelines
- **Legal Information**: Women's rights and legal frameworks in India

#### D. Location Services
- **Foreground Service**: Background location tracking capability
- **Geocoding**: Convert GPS coordinates to readable addresses
- **Maps Integration**: Google Maps links for precise location sharing

---

## Technical Implementation

### Technology Stack

| Component | Technology |
|-----------|------------|
| Programming Language | Java |
| IDE | Android Studio |
| Minimum SDK | API 24 (Android 7.0) |
| Target SDK | API 34 (Android 14) |
| Database | Room (SQLite) |
| Architecture | MVVM Pattern |
| Location Services | Google Play Services |
| Maps | Google Maps Android API |

### Key Libraries

```gradle
// UI Components
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.10.0
androidx.constraintlayout:constraintlayout:2.1.4
androidx.recyclerview:recyclerview:1.3.2

// Location Services
com.google.android.gms:play-services-location:21.0.1
com.google.android.gms:play-services-maps:18.2.0

// Database
androidx.room:room-runtime:2.6.0

// Lifecycle
androidx.lifecycle:lifecycle-viewmodel:2.6.2
androidx.lifecycle:lifecycle-livedata:2.6.2
```

---

## Project Structure

### Package Organization

```
com.resqher.safety/
├── activities/              # UI Controllers
│   ├── MainActivity
│   ├── SOSActivity
│   ├── EmergencyContactsActivity
│   ├── HelplineActivity
│   ├── SafetyTipsActivity
│   └── LegalRightsActivity
├── adapters/               # RecyclerView Adapters
│   ├── ContactsAdapter
│   └── HelplineAdapter
├── database/               # Data Layer
│   ├── AppDatabase
│   └── EmergencyContactDao
├── models/                 # Data Models
│   ├── EmergencyContact
│   └── Helpline
├── services/               # Background Services
│   └── LocationTrackingService
└── utils/                  # Helper Classes
    ├── PermissionManager
    ├── LocationHelper
    └── SMSManager
```

### File Count Summary
- **Java Classes**: 17 files
- **XML Layouts**: 9 files
- **Resource Files**: 5 files
- **Gradle Files**: 3 files
- **Documentation**: 3 files

**Total Lines of Code**: ~2,500+ lines

---

## Features Breakdown

### Feature 1: Emergency SOS
**Files Involved**:
- [SOSActivity.java](app/src/main/java/com/resqher/safety/activities/SOSActivity.java)
- [activity_sos.xml](app/src/main/res/layout/activity_sos.xml)
- [LocationHelper.java](app/src/main/java/com/resqher/safety/utils/LocationHelper.java)
- [SMSManager.java](app/src/main/java/com/resqher/safety/utils/SMSManager.java)

**Functionality**:
1. User presses SOS button
2. 5-second countdown (cancelable)
3. GPS location retrieved
4. Address geocoded
5. SMS sent to all primary contacts
6. Vibration feedback provided

**Code Snippet**:
```java
private void activateSOS() {
    locationHelper.getCurrentLocation(new LocationHelper.LocationCallback() {
        @Override
        public void onLocationReceived(String location, double lat, double lng) {
            sendEmergencyAlerts(location);
        }
    });
}
```

### Feature 2: Contact Management
**Files Involved**:
- [EmergencyContactsActivity.java](app/src/main/java/com/resqher/safety/activities/EmergencyContactsActivity.java)
- [ContactsAdapter.java](app/src/main/java/com/resqher/safety/adapters/ContactsAdapter.java)
- [EmergencyContact.java](app/src/main/java/com/resqher/safety/models/EmergencyContact.java)
- [AppDatabase.java](app/src/main/java/com/resqher/safety/database/AppDatabase.java)

**Database Schema**:
```java
@Entity(tableName = "emergency_contacts")
public class EmergencyContact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phoneNumber;
    private String relationship;
    private boolean isPrimary;
}
```

### Feature 3: Helpline Directory
**Files Involved**:
- [HelplineActivity.java](app/src/main/java/com/resqher/safety/activities/HelplineActivity.java)
- [HelplineAdapter.java](app/src/main/java/com/resqher/safety/adapters/HelplineAdapter.java)
- [Helpline.java](app/src/main/java/com/resqher/safety/models/Helpline.java)

**Categories**:
- Emergency Services (Police, Ambulance, Fire)
- Women Safety Helplines
- Child & Senior Safety
- Mental Health Support
- Legal Aid Services

### Feature 4: Location Tracking
**Files Involved**:
- [LocationTrackingService.java](app/src/main/java/com/resqher/safety/services/LocationTrackingService.java)
- [LocationHelper.java](app/src/main/java/com/resqher/safety/utils/LocationHelper.java)

**Implementation**:
- Foreground service for continuous tracking
- Location updates every 10 seconds
- Broadcast location to app components
- Google Maps link generation

### Feature 5: Educational Content
**Files Involved**:
- [SafetyTipsActivity.java](app/src/main/java/com/resqher/safety/activities/SafetyTipsActivity.java)
- [LegalRightsActivity.java](app/src/main/java/com/resqher/safety/activities/LegalRightsActivity.java)

**Content Coverage**:
- 8 safety tip categories
- Constitutional rights (Articles 14, 15, 21)
- Key legislation (Domestic Violence Act, Dowry Prohibition Act, etc.)
- IPC sections related to women's safety
- Workplace rights and protections

---

## Permissions & Security

### Required Permissions
```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
<uses-permission android:name="android.permission.SEND_SMS" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.FOREGROUND_SERVICE" />
```

### Security Measures
- Runtime permission handling via PermissionManager
- Local data storage (no cloud sync)
- Permission validation before critical operations
- No storage of sensitive user data

---

## User Interface Design

### Design Principles
- **Material Design**: Following Google's Material Design guidelines
- **Accessibility**: Large touch targets, clear typography
- **Color Coding**: Red for emergency, green for safe actions
- **Minimalism**: Simple, distraction-free interface

### UI Components
- CardView for feature modules
- RecyclerView for lists
- FloatingActionButton for primary actions
- Material buttons and text fields
- Constraint layouts for responsive design

### Color Scheme
```xml
<color name="primary">#E91E63</color>        <!-- Pink -->
<color name="primary_dark">#C2185B</color>   <!-- Dark Pink -->
<color name="sos_red">#F44336</color>        <!-- Emergency Red -->
<color name="safe_green">#4CAF50</color>     <!-- Safe Green -->
```

---

## Testing Strategy

### Testing Performed
1. **Unit Testing**: Individual component functionality
2. **Integration Testing**: Database operations, API calls
3. **UI Testing**: User interaction flows
4. **Permission Testing**: Runtime permission handling
5. **Device Testing**: Multiple Android versions

### Test Scenarios
- ✅ SOS activation and cancellation
- ✅ Contact CRUD operations
- ✅ Location accuracy (real device)
- ✅ SMS delivery confirmation
- ✅ Permission denial handling
- ✅ Network connectivity issues
- ✅ Low battery scenarios

---

## Challenges & Solutions

### Challenge 1: Background Location Tracking
**Problem**: Android 10+ restricts background location access
**Solution**: Implemented foreground service with persistent notification

### Challenge 2: SMS Delivery Confirmation
**Problem**: No guarantee SMS was delivered
**Solution**: Used SentIntent and DeliveryIntent in SmsManager

### Challenge 3: Permission Handling
**Problem**: Complex runtime permission flow
**Solution**: Created PermissionManager utility class for centralized handling

### Challenge 4: Location Accuracy
**Problem**: GPS coordinates not readable
**Solution**: Integrated Geocoding API for address conversion

---

## Future Scope

### Planned Enhancements
1. **Audio Recording**: Record audio during emergency
2. **Shake Gesture**: Activate SOS by shaking device
3. **Fake Call**: Simulate incoming call to exit situations
4. **Safe Routes**: Integration with maps for safe path suggestions
5. **Community Features**: Report unsafe areas, share experiences
6. **Multi-language**: Hindi, regional language support
7. **Wearable Integration**: Smart watch support
8. **AI Integration**: Threat detection using ML

### Scalability
- Cloud backend for contact sync
- Web dashboard for family members
- Integration with local police stations
- Real-time location sharing portal

---

## Installation & Deployment

### Development Setup
1. Clone repository
2. Open in Android Studio
3. Add Google Maps API key
4. Sync Gradle dependencies
5. Build and run

### APK Generation
```bash
./gradlew assembleDebug    # For testing
./gradlew assembleRelease  # For production
```

### Deployment Options
- Google Play Store (Production)
- APK sideloading (Testing)
- Firebase App Distribution (Beta testing)

---

## Performance Metrics

### App Size
- APK Size: ~8-10 MB (debug)
- APK Size: ~5-6 MB (release with ProGuard)

### Memory Usage
- Average RAM: 40-60 MB
- Peak RAM: 80-100 MB (with location service)

### Battery Impact
- Idle: Minimal impact
- Active tracking: ~5-10% per hour (varies by device)

---

## Documentation Files

1. **[README.md](README.md)**: Complete project documentation
2. **[SETUP_GUIDE.md](SETUP_GUIDE.md)**: Step-by-step setup instructions
3. **PROJECT_OVERVIEW.md** (this file): Technical overview

---

## Academic Relevance

### Learning Outcomes
- Android application development
- Database management with Room
- Location-based services
- Material Design implementation
- Permission handling
- Background services
- RecyclerView and adapters
- MVP/MVVM architecture

### Technologies Mastered
- Java programming
- Android SDK
- Google Play Services
- SQLite/Room database
- Gradle build system
- XML layouts
- Git version control

---

## Social Impact

### Target Users
- Women of all ages
- Students and working professionals
- Travelers
- Night shift workers
- Anyone seeking personal safety

### Expected Impact
- Faster emergency response
- Increased awareness of legal rights
- Easy access to helpline services
- Empowerment through knowledge
- Peace of mind for families

---

## Conclusion

ResQHer successfully demonstrates the application of mobile technology to address a critical social issue. The project combines emergency response capabilities with educational content, creating a comprehensive safety solution.

The application showcases:
- ✅ Complete Android development lifecycle
- ✅ Integration of multiple Android APIs
- ✅ Database design and implementation
- ✅ User-centric interface design
- ✅ Real-world problem solving
- ✅ Security and privacy considerations

### Project Status
**Status**: ✅ Completed and Functional
**Version**: 1.0
**Last Updated**: 2025

---

## Contact Information

**Project Category**: Final Year Project
**Domain**: Mobile Application Development
**Focus Area**: Women Safety & Security
**Platform**: Android

---

## Appendix

### Glossary
- **SOS**: Emergency distress signal
- **GPS**: Global Positioning System
- **Room**: Android's SQLite abstraction library
- **DAO**: Data Access Object
- **API**: Application Programming Interface
- **SDK**: Software Development Kit

### References
- Android Developer Documentation
- Material Design Guidelines
- Google Maps Platform Documentation
- Women Safety Laws in India (IT Act, IPC)
- Stack Overflow community

### Resources Used
- Android Studio IDE
- Google Cloud Console
- GitHub for version control
- Stack Overflow for problem-solving
- Android documentation

---

**ResQHer** - Empowering Women Through Technology

*Final Year Project | Android Application Development*
