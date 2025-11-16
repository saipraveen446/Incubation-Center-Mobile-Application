# Incubation Centre Mobile App

A comprehensive Android application designed to manage and streamline operations within an incubation center. This app provides a platform for startups, mentors, and administrators to collaborate, track progress, and access resources.

## 📱 App Screenshots

### Home Page
![Home Page](Incubation%20Center%20Demo/Home%20page.jpg)

### Dashboard
![Dashboard](Incubation%20Center%20Demo/Dashboardpage.jpg)

### User Management
![All Users](Incubation%20Center%20Demo/All%20Users.jpg)

### Profile Section
![Profile](Incubation%20Center%20Demo/Profile.jpg)

### Account Management
![Account](Incubation%20Center%20Demo/Account.jpg)

## 🎥 Demo Video

Watch the complete app demonstration:

[![Demo Video](Incubation%20Center%20Demo/Home%20page.jpg)](Incubation%20Center%20Demo/Incubation%20Center%20Mobile%20App%20Demo.mp4)

*Click the image above to watch the demo video*

## ✨ Features

- **User Management**: Comprehensive user profiles and authentication
- **Dashboard**: Real-time analytics and progress tracking
- **Resource Management**: Access to incubation resources and materials
- **Startup Tracking**: Monitor startup progress and milestones
- **Mentorship**: Connect startups with industry mentors
- **Event Management**: Schedule and track incubation events
- **Document Sharing**: Secure document upload and sharing
- **Notifications**: Real-time updates and alerts

## 🛠 Technology Stack

- **Platform**: Android
- **Language**: Java
- **Build System**: Gradle
- **UI Framework**: Android Native Components
- **Backend**: Firebase (Google Services)
- **Architecture**: MVC Pattern



## 🚀 Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) 
- Java Development Kit (JDK) 8 or higher
- Android SDK (API Level 21+)
- Firebase account (for backend services)
- Internet connection for dependency resolution

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/saipraveen446/Incubation-Center-Mobile-Application.git
   cd incubation-center-app/Incubation\ Center/Incubation\ Centre
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select `Open an existing project`
   - Navigate to the cloned directory and select the `Incubation Centre` folder

3. **Configure Firebase:**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Download `google-services.json` and replace the existing one in `app/` directory
   - Enable Authentication, Firestore, and Storage services

4. **Build the project:**
   - Android Studio will automatically sync and build the project
   - If sync fails, click `Sync Project with Gradle Files`

### Running the App

1. **Using Android Studio:**
   - Connect an Android device or start an emulator
   - Click the 'Run' button (green play icon) in the toolbar

2. **Using Command Line:**
   ```bash
   # Build debug APK
   ./gradlew assembleDebug
   
   # Install on connected device
   ./gradlew installDebug
   ```

## 🔧 Configuration

### Firebase Setup
1. Enable Authentication (Email/Password and Google Sign-In)
2. Set up Firestore Database with the following collections:
   - `users` (user profiles and roles)
   - `startups` (startup information and progress)
   - `events` (incubation events and schedules)
   - `resources` (shared documents and materials)



## 📱 App Permissions

The app requires the following permissions:

- `INTERNET`: Network access for Firebase services
- `READ_EXTERNAL_STORAGE`: Access device storage for document uploads
- `WRITE_EXTERNAL_STORAGE`: Save documents to device storage
- `CAMERA`: Capture images for profiles and documents




## 🔒 Security

- User authentication via Firebase Auth
- Secure data transmission using HTTPS
- Input validation and sanitization
- Role-based access control

## 📈 Performance

- Optimized image loading and caching
- Efficient data fetching with pagination
- Background task management
- Memory leak prevention





**Made with ❤️ for the Incubation Center Community** 