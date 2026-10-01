<!-- readme-seo: bannysukumar-professional-v4 -->

# JNTUH Results

JNTUH Results is a Capacitor Android project. `settings.gradle` includes the `app` module and `capacitor-cordova-android-plugins`. The packaged site under `app/src/main/assets/public` has pages for academic results, class results, backlog reports, and a credit checker.

## Overview

The web assets are static HTML. Result routes include `academicresult`, `academicallresult`, `classresult`, `backlogreport`, and `creditchecker`, each with a `result` page. Admin assets include dashboard, users, feedback, health, and settings. This is the Android shell for that site, not the separate `jntuh-results-website` repository.

## Features

Paths under `app/src/main/assets/public`:

- Academic result and all-result pages
- Class result and backlog report
- Credit checker
- Admin dashboard, users, feedback, health, and settings
- FAQ and feedback pages

## Tech Stack

| Technology | Where it shows up |
|---|---|
| HTML | `app/src/main/assets/public` |
| Capacitor | `capacitor.settings.gradle` and `settings.gradle` |
| Android Gradle | `build.gradle`, `gradlew` |

## Architecture

Android Capacitor shell → static HTML in `app/src/main/assets/public`.

## Project Structure

```text
jntuh-results-app/
├── app/src/main/assets/public/
├── capacitor-cordova-android-plugins/
├── settings.gradle
├── capacitor.settings.gradle
└── build.gradle
```

## Prerequisites

- Android Studio, or a JDK plus the Gradle wrapper

## Installation

```bash
git clone https://github.com/Bannysukumar/jntuh-results-app.git
cd jntuh-results-app
```

Open the Android project in Android Studio.

## Usage

Run the `app` module. Result screens are the HTML files under `academicresult`, `classresult`, `backlogreport`, and `creditchecker`.

## Contributing

Read [CONTRIBUTING.md](CONTRIBUTING.md) before opening a pull request.

## License

Licensed under MIT. See [LICENSE](LICENSE).

## Author

Banny Sukumar

GitHub: https://github.com/Bannysukumar
