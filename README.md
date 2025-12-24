# SpaceMissionKMP

A Kotlin Multiplatform (KMP) sample app that fetches and displays SpaceX launch data. The project shares domain, data, and business logic across Android and iOS, using a native UI on each platform (Jetpack Compose on Android, SwiftUI on iOS).

## Overview
- Shared logic in the `shared` KMP module (Kotlin):
  - Networking with Ktor client
  - JSON with Kotlinx Serialization
  - Coroutines for concurrency
  - Dependency Injection with Koin
  - Date/Time with Kotlinx DateTime
- Android app (`androidApp`): Jetpack Compose UI, Navigation Compose, ViewModel (Lifecycle)
- iOS app (`iosApp`): SwiftUI app consuming the shared module

Data source
- SpaceX public API v4: `https://api.spacexdata.com/v4/` (see `shared/src/commonMain/.../data/LaunchesApi.kt`)

Entry points
- Android: `androidApp/src/main/java/.../core/MainActivity.kt`
- iOS: `iosApp/iosApp/iOSApp.swift`

## Tech Stack
- Language: Kotlin (Multiplatform), Swift (iOS app)
- Build system: Gradle with Kotlin DSL; Gradle Wrapper included
- Android UI: Jetpack Compose (Material 3), Navigation Compose
- iOS UI: SwiftUI
- Networking: Ktor Client
- Serialization: kotlinx-serialization-json
- Concurrency: Kotlin Coroutines
- DI: Koin (core + Android/Compose)
- Other: Kotlinx DateTime, AndroidX Lifecycle
- Plugins: Kotlin Multiplatform, Android Application/Library, Kotlin Serialization, Touchlab SKIE

## Requirements
- macOS (to build both Android and iOS targets). Android-only may use any OS supported by Android tooling.
- JDK 17+
- Android Studio (latest stable recommended)
- Xcode (for iOS build/run; latest stable recommended)
- Android SDK with compileSdk 35, minSdk 26 (androidApp)
- CocoaPods is NOT used in this setup (shared framework is produced directly via KMP).

## Project Structure
```
SpaceMissionKMP/
├── androidApp/                      # Android application (Compose)
│   ├── src/main/AndroidManifest.xml
│   └── src/main/java/com/...        # UI, DI, navigation, viewmodels
├── iosApp/                          # iOS application (SwiftUI)
│   ├── iosApp.xcodeproj
│   └── iosApp/                      # Swift sources (entry: iOSApp.swift)
├── shared/                          # KMP shared module (Android + iOS)
│   ├── src/commonMain/kotlin/       # shared Kotlin code (domain/data/DI)
│   ├── src/androidMain/kotlin/      # Android-specific implementations
│   ├── src/iosMain/kotlin/          # iOS-specific implementations
│   ├── src/commonTest/kotlin/       # common tests
│   ├── src/androidUnitTest/kotlin/  # Android unit tests for shared module
│   └── src/iosTest/kotlin/          # iOS tests for shared module
├── build.gradle.kts                 # root Gradle config
├── settings.gradle.kts              # includes modules (androidApp, shared)
├── gradle/libs.versions.toml        # dependency versions catalog
├── LICENSE                          # MIT License
└── README.md
```

## Setup
1. Clone the repository.
2. Open the project root in Android Studio (for Android), and optionally in Xcode for iOS.
3. Ensure you have JDK 17 set in your IDE/ENV.
4. Let Gradle sync and download dependencies.

## Build & Run

### Android
- From Android Studio: select the `androidApp` run configuration and click Run.
- From command line:
  - Build debug APK: `./gradlew :androidApp:assembleDebug`
  - Install on a connected device/emulator: `./gradlew :androidApp:installDebug`
  - Clean build: `./gradlew clean`

Notes
- Minimum SDK: 26; Target/Compile SDK: 35
- Compose is enabled with Material 3 and Navigation Compose

### iOS
- Open `iosApp/iosApp.xcodeproj` in Xcode.
- Select the `iosApp` scheme and a simulator or a device.
- Build & Run from Xcode.

KMP iOS frameworks
- The shared module produces a static framework named `shared` for iOS targets (`iosX64`, `iosArm64`, `iosSimulatorArm64`). Xcode project is already configured to consume it.

## Scripts & Tasks
Common Gradle tasks:
- `./gradlew build` — assemble and run checks for all modules
- `./gradlew :androidApp:assembleDebug` — build Android debug APK
- `./gradlew :androidApp:installDebug` — install Android debug APK to a device/emulator
- `./gradlew :shared:assemble` — build shared artifacts (including iOS frameworks)
- `./gradlew clean` — clean build outputs

iOS testing/build tasks (Mac only):
- `./gradlew :shared:iosSimulatorArm64Test` — run iOS tests on simulator
- Other iOS targets (if configured locally): `:shared:iosX64Test`, `:shared:iosArm64Test`

## Environment Variables & Configuration
- API base URL is currently hard-coded as `https://api.spacexdata.com/v4/` in `shared/src/commonMain/.../data/LaunchesApi.kt`.

TODOs
- [ ] Externalize the API base URL into build config or environment variables for flexibility.
- [ ] Add separate build flavors/environments if needed (dev/staging/prod).

## Tests
Locations
- Common tests: `shared/src/commonTest/kotlin`
- Android unit tests (shared module): `shared/src/androidUnitTest/kotlin`
- iOS tests (shared module): `shared/src/iosTest/kotlin`

Commands
- Android unit tests for shared: `./gradlew :shared:androidUnitTest`
- iOS simulator tests for shared (macOS + Xcode): `./gradlew :shared:iosSimulatorArm64Test`

Note: The Android app module may not contain unit tests at the moment; tests are mainly under the shared module.

## Notable Code
- DI modules: `shared/src/commonMain/kotlin/.../di/` and `androidApp/src/main/java/.../di/`
- API client: `shared/src/commonMain/kotlin/.../data/LaunchesApi.kt`
- Android entry & navigation: `androidApp/src/main/java/.../core/MainActivity.kt`, `.../navigation/MainNavHost.kt`
- iOS entry: `iosApp/iosApp/iOSApp.swift`

## License
This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
