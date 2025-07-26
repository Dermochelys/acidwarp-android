# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android port of Acid Warp, a classic psychedelic graphics demo program originally created by Noah Spurrier and Mark Bilk in 1992-1993. The Android port was created by Matthew Zavislak, building on SDL ports by Boris Gjenero and Steven Wills.

The application generates real-time procedural graphics with rotating palettes and various visual effects using native C code through SDL3 and JNI.

## Architecture

### Core Components

- **Native C Engine**: Core Acid Warp logic in `app/jni/src/` including:
  - `acidwarp.c/h`: Main program logic and image generation
  - `display.c/h` & `draw.c`: Graphics rendering and display management  
  - `rolnfade.c/h`: Palette rotation and color effects
  - `bit_map.c/h`: Bitmap manipulation utilities
  - `palinit.c/h`: Lookup tables and palette initialization

- **SDL3 Integration**: Located in `app/jni/SDL/` - provides cross-platform graphics, input, and audio capabilities

- **Android Activity**: `app/src/main/java/com/dermochelys/acidwarp/Activity.kt` extends SDLActivity with fullscreen immersive mode setup

- **JNI Bridge**: SDL's Java classes in `app/src/main/java/org/libsdl/app/` handle communication between Android and native code

### Build System

The project uses Android Gradle with CMake for native compilation:
- Root `build.gradle`: Project-level Gradle configuration  
- `app/build.gradle`: Android app module with NDK configuration
- `app/jni/CMakeLists.txt`: Top-level CMake that builds SDL and native sources
- `app/jni/src/CMakeLists.txt`: Native source compilation rules

## Development Commands

### Building
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK  
./gradlew assembleRelease

# Install and run on connected device
./gradlew installDebug
```

### Testing
```bash
# Run lint checks
./gradlew lint

# Run unit tests (if any exist)
./gradlew test
```

### Native Development
The native code uses CMake and is built automatically through Gradle. Key configuration:
- NDK version: 28.2.13676358
- Target Android API: 21+ 
- Supported ABIs: arm64-v8a, x86_64, armeabi-v7a, x86
- C++ standard: c++_static

### Debugging
- Use Android Studio for Java/Kotlin debugging
- For native debugging, use `gdb` or `lldb` with Android NDK tools
- Enable GPU validation for graphics debugging

## Key Implementation Details

- The app runs in fullscreen immersive mode hiding system bars
- Native code generates procedural graphics using 40 different image functions
- Real-time palette cycling creates the classic "acid warp" effect
- Input handling supports touch, gamepad, and keyboard controls
- The main loop runs continuously generating and displaying new frames