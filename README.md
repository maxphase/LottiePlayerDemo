# Lottie Player Demo

A simple Android application demonstrating Lottie animation playback capabilities using Jetpack Compose.

## Purpose

This app serves as a proof of concept for implementing a basic Lottie animation player in Android. It allows cycling through multiple Lottie animations stored in the app's assets, with basic playback controls.

## Features

- Lottie animation playback with play/pause functionality
- Navigation between multiple animations
- Current animation filename display
- Safe area handling for modern Android devices with camera cutouts
- Light grey background for better visibility of animations with transparency

## Technical Details

### Development Environment
- Android Studio Ladybug Feature Drop | 2024.2.2
- Kotlin with Jetpack Compose
- Lottie Compose library version 6.3.0

### Requirements
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 35
- Compile SDK: 35

### Dependencies
- Jetpack Compose
- Lottie Compose

### Tested On
- Pixel 8 Emulator (API 35)

## Setup & Usage

1. Clone the repository
2. Place your Lottie JSON animation files in `app/src/main/assets/animations/`
3. Build and run the project

Note: Make sure to include at least one animation file named `default.json` in the animations folder as a fallback.

## Implementation Notes

The app uses modern Android development practices:
- Jetpack Compose for UI
- Material3 design components
- Proper handling of system insets and cutouts
- State management using Compose state

## Debugging

To test the app:
1. Create an ARM64 emulator (required for Apple Silicon Macs)
2. Place test animations in the assets folder
3. Run the app and verify:
    - Animation playback
    - Play/Pause functionality
    - Navigation between animations
    - Filename display
    - Proper handling of device cutouts

## Project Structure

Key files and directories:
- `/app/src/main/assets/animations/` - Location for Lottie JSON files
- `/app/src/main/kotlin/.../MainActivity.kt` - Main implementation
- `build.gradle.kts` - Project configuration and dependencies