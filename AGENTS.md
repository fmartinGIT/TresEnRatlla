# AGENTS.md

## Project context
Single-module Android app (`:app`) in Kotlin + Jetpack Compose. It is still the Android Studio
template (`MainActivity` renders `Greeting("Android")`); the real work is defined in
`docs/MVVM-Tres en ratlla.md` (Catalan, authoritative). Implement Tic-Tac-Toe with the exact
required types in package `com.example.tresenratlla`: `CellModel`, `BoardModel`
(`createInitialBoard()`, `makeMove()`, `checkWinner()`), `GameViewModel` (`onCellClick()`,
`restartGame()`), `GameScreen`, `BoardGrid`, `CellItemView`.

## Commands
Run from the repo root. The WSL shell has no `java`/`git`, so use the Windows toolchain:
`cmd.exe /c gradlew.bat <task>` and the Windows Git at
`/mnt/c/Program Files/Git/cmd/git.exe`.

- Build: `cmd.exe /c gradlew.bat assembleDebug`
- Unit tests: `cmd.exe /c gradlew.bat testDebugUnitTest`
- Single unit test: `cmd.exe /c gradlew.bat testDebugUnitTest --tests "com.example.tresenratlla.model.BoardModelTest"`
- Instrumented tests (device/emulator required): `cmd.exe /c gradlew.bat connectedDebugAndroidTest`
- Lint: `cmd.exe /c gradlew.bat lintDebug`

## Environment gotchas
- `local.properties` points `sdk.dir` at a Windows-only path (`C:\Users\fmdev\...`) and is
  gitignored. Use Windows/Android Studio for builds; a WSL build would need a Linux Android SDK
  and a JDK (the daemon criteria targets JDK 25). Do not commit `local.properties`.
- Git is installed on Windows, not inside WSL. The repository is initialized with remote
  `origin` = `git@github.com:fmartinGIT/TresEnRatlla.git`, branch `main`. Use clear commits,
  e.g. `feat: implementa la comprovació de victòria en el BoardModel`.

## Build configuration quirks
- Versions are centralized in `gradle/libs.versions.toml`; add dependencies there and reference
  them through `libs.*` rather than hardcoding versions.
- The project uses AGP 9.4.1 DSL: `compileSdk { version = release(37) }` (not `compileSdk = 37`),
  and R8 rules belong in `app/src/main/keepRules/*.keep`, not `proguard-rules.pro`.
- Configuration cache is enabled (`org.gradle.configuration-cache=true`); avoid build-script side
  effects.
- `namespace`/`applicationId` are `com.example.tresenratlla`, and the instrumented test asserts
  that package name.
- No DI, navigation, or networking libraries are set up. Keep the Compose/AndroidX stack already
  declared unless the task requires more.

## Assignment constraints
- `BoardModel` must stay immutable: return updated instances via `.copy()`; the View must not
  mutate state directly.
- Create a `README.md` (none exists) with the three spec sections: MVVM analysis vs
  `MVVMSimple`/`MVVM-separatModel`, the app's architecture plus a screenshot/GIF, and concepts
  learned.
- Add explanatory comments for unfamiliar Android/Compose/StateFlow APIs (e.g.
  `collectAsState()`, `asStateFlow()`, `.copy()`) as the spec requires.
