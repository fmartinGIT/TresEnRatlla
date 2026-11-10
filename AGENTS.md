# AGENTS.md

## Project context
Single-module Android app (`:app`) in Kotlin + Jetpack Compose implementing Tic-Tac-Toe with
MVVM. `docs/MVVM-Tres en ratlla.md` (Catalan) is the authoritative assignment spec.
`BoardModel` holds the pure game logic; `GameViewModel` exposes it through `StateFlow`;
Compose UI lives under `ui/` (`GameScreen`, `BoardGrid`, `CellItemView`). Tests are in
`app/src/test` and cover the model and ViewModel.

## Commands
Run from the repo root. The WSL shell has no `java`/`git`, so use the Windows toolchain:
`cmd.exe /c gradlew.bat <task>` and the Windows Git at
`/mnt/c/Program Files/Git/cmd/git.exe`.

- Build: `cmd.exe /c gradlew.bat assembleDebug`
- Unit tests: see the path-encoding workaround below
- Single unit test: `cmd.exe /c gradlew.bat testDebugUnitTest --tests "com.example.tresenratlla.model.BoardModelTest"` (run via the ASCII drive)
- Instrumented tests (device/emulator required): `cmd.exe /c gradlew.bat connectedDebugAndroidTest`
- Lint: `cmd.exe /c gradlew.bat lintDebug`

## Environment gotchas
- `local.properties` points `sdk.dir` at a Windows-only path (`C:\Users\fmdev\...`) and is
  gitignored. Use Windows/Android Studio for builds; a WSL build would need a Linux Android SDK
  and a JDK (the daemon criteria targets JDK 25). Do not commit `local.properties`.
- Git is installed on Windows, not inside WSL. The repository is initialized with remote
  `origin` = `git@github.com:fmartinGIT/TresEnRatlla.git`, branch `main`. Use clear commits,
  e.g. `feat: implementa la comprovació de victòria en el BoardModel`.
- Unit tests fail from the original path because Windows JVMs use `sun.jnu.encoding=Cp1252` while
  Gradle writes its classpath `@argfile` as UTF-8; the accented `Programació` path gets corrupted
  and tests throw `ClassNotFoundException`. Verified workaround:
  ```powershell
  subst T: "C:\Users\fmdev\Cloud\GSDAM II\Programació multimèdia Apps Mòbils\TresEnRatlla"
  ```
  then `cmd.exe /c "cd /d T:\ && gradlew.bat testDebugUnitTest --no-configuration-cache"`.
  Remove the mapping with `subst T: /d`. Builds (`assembleDebug`) work from the original path.

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
- Keep `README.md` in sync with the three spec sections: MVVM analysis vs
  `MVVMSimple`/`MVVM-separatModel`, the app's architecture plus a screenshot/GIF, and concepts
  learned. The screenshot is `docs/captura-app.png`; regenerate it if the UI changes
  (`adb exec-out screencap -p > docs/captura-app.png`).
- Add explanatory comments for unfamiliar Android/Compose/StateFlow APIs (e.g.
  `collectAsState()`, `asStateFlow()`, `.copy()`) as the spec requires.
