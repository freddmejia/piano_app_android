# MyPiano

**MyPiano** is an application built with Kotlin and Jetpack Compose. It delivers a realistic and responsive virtual piano experience for one octave

## 🚀 Overview

- **Tech Stack:** Kotlin, Jetpack Compose, Hilt, SoundPool, Coroutines.
---

## 🏗 Project Structure

```
 ├─ data/
 │    ├─ PianoSoundLoader.kt        # Loads raw sound resources into SoundPool
 │    └─ PianoMediaHandler.kt       # Plays notes by name or index
 ├─ di/
 │    ├─ AppModule.kt               # Hilt providers
 │    └─ PianoApplication.kt        # @HiltAndroidApp
 ├─ ui/
 │    ├─ menu/
 │    │    └─ PianoScreen.kt        # Composable container for piano UI
 │    └─ viewmodel/
 │         └─ PianoViewModel.kt     # ViewModel: bridges UI events to media handler
 └─ MainActivity.kt                 # Hosts Compose UI
 ├─ res/raw                         # sounds
```

---

## 📐 Architectural Layers

1. **UI Layer** (`ui/menu`, `ui/theme`): Stateless, testable Composables for keys and screen.
2. **ViewModel Layer** (`ui/viewmodel`): `PianoViewModel` captures user interactions and triggers audio playback.
3. **Data/Audio Layer** (`data`):
   - `PianoSoundLoader`: Loads and caches sound samples.
   - `PianoMediaHandler`: Exposes simple APIs to play notes.
4. **DI Layer** (`di`): Hilt modules (`AppModule`) and application setup (`PianoApplication`).

---

## 🛠 Setup & Build

1. **Clone** the repository:
   ```bash
   git clone https://github.com/your-username/MyPiano.git
   cd MyPiano
   ```
2. **Open** in Android Studio. Gradle sync should resolve all dependencies.
3. **Run** on an emulator or physical device (minSdkVersion ≥ 21).

### Dependencies
- Jetpack Compose (UI)
- Hilt (DI)
- SoundPool (Audio)
- Kotlin Coroutines (async tasks)
- Material3 (theming)
