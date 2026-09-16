# World Generation Tests

Run from the project root:

```powershell
.\gradlew.bat clean runGameTest
```

Minecraft 26.3 GameTest uses the `minecraft:flat_all_dimensions` world preset.
Its default End is flat and has feature generation disabled. The test-only
resource override keeps the Overworld and Nether flat, but uses the normal
End noise generator. It is not included in the production mod JAR.

The server test world persists under `build/run/gameTest/world`. Use `clean`
when validating changes to generation or the test preset; otherwise existing
chunks can hide regressions or retain the previous flat terrain. This clears
build outputs and the test world, not development saves under `run`.

`SuspiciousEndStoneGameTests` checks the End generator, feature injection into
all tagged End biomes, natural generation in 81 central-island chunks, and
brushable block entities. It does not manually place ore to satisfy the test.
