<p align="center">
  <img src="res/menu_backdrop_heresy.png" width="150" alt="Heresy title card">
</p>

<h1 align="center">Heresy</h1>

<p align="center">
  <em>A 2D action-platformer written in pure Java.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-16%2B-007396?logo=openjdk&logoColor=white" alt="Java 16+">
  <img src="https://img.shields.io/badge/dependencies-none-brightgreen" alt="No external dependencies">
  <img src="https://img.shields.io/badge/render-60%20FPS-blue" alt="60 FPS">
</p>

---

**Heresy** is a side-scrolling hack-and-slash built from scratch with Java's
Swing/AWT libraries. Cut your way through the oakwoods, descend into the caves,
and storm a haunted castle on the way to the final boss arena. Leap across
platforms, smash open crates for potions, dodge spikes, and clear every monster
in a level to move on.

No game engine, no third-party libraries — just a fixed-timestep game loop,
sprite-sheet rendering, and a lot of hand-placed pixels.

## Features

- **10 handcrafted levels** across three chapters plus a boss arena, all defined
  as editable PNG maps.
- **11 enemy types**, including the mimic that hides as a chest and two bosses:
  the Demon Slime and the Frost Guardian.
- **Melee combat** with a light attack and a dash-style power attack that
  consumes mana.
- **Progression by clearing** — a level ends when every enemy in it is dead.
- **Loot and hazards** — red potions heal, blue potions restore mana, barrels and
  jars drop potions when smashed, and spikes kill instantly.
- **Chapter-based soundtrack** with a boss theme, plus jump, attack, death, and
  level-complete sound effects.
- **Full game flow** — main menu, options, pause overlay, level-complete screen,
  death screen, and a live run timer.
- **Text logs** — combat events are appended to `battlelog.txt`, and your run
  time is saved to `leaderboard.txt` when you quit from the main menu.

## Controls

| Action | Input |
| --- | --- |
| Move left / right | `A` / `D` |
| Jump | `Space` |
| Light attack | Left mouse button |
| Power attack (dash, costs 60 mana) | Right mouse button |
| Pause / resume | `Esc` |
| Start from menu | `Enter` or click **Play** |
| Menus, sliders, overlays | Mouse |

Mana regenerates on its own, so the power attack is always charging back up.
Touching a red potion restores 15 HP; a blue potion restores 10 mana.

## Getting started

### Requirements

- **JDK 16 or newer** (the Eclipse project targets JavaSE-16)
- No build tool or external dependencies required

### Run in Eclipse (recommended)

1. **File → Import → General → Existing Projects into Workspace**
2. Select the `Heresy` folder (the one containing `.project`).
3. Make sure the project uses a Java 16+ JDK.
4. Run `src/main/MainClass.java`.

The `res` folder is already configured as a source folder, so Eclipse copies the
art, audio, and levels onto the classpath for you.

### Run from the command line

**PowerShell (Windows)**

```powershell
cd Heresy
New-Item -ItemType Directory -Force bin | Out-Null
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
Copy-Item -Recurse -Force res\* bin
java -cp bin main.MainClass
```

**Bash (Linux / macOS)**

```bash
cd Heresy
mkdir -p bin
javac -d bin $(find src -name "*.java")
cp -r res/* bin/
java -cp bin main.MainClass
```

> Assets are loaded from the classpath root, so `res/` contents must sit next to
> the compiled packages inside `bin/`.

## Project layout

```
Heresy/
├── src/
│   ├── main/        Entry point, window, game panel, game loop
│   ├── gamestates/  Menu, options, playing, and the state machine
│   ├── levels/      PNG-driven level parsing and scrolling camera
│   ├── entities/    Player, 11 enemy classes, enemy manager
│   ├── objects/     Potions, containers, spikes
│   ├── ui/          Buttons, overlays, volume and audio controls
│   ├── inputs/      Keyboard and mouse handlers
│   ├── audio/       Music and sound-effect playback
│   └── utils/       Constants, asset loading, battle log
├── res/
│   ├── lvls/        10 level maps (1.png … 10.png)
│   ├── audio/       WAV music and sound effects
│   └── *.png        Sprites, tilesets, backgrounds, UI art
├── battlelog.txt    Appended combat log (generated at runtime)
├── .classpath
└── .project
```

## Level format

Levels are plain PNG images in `res/lvls/` — **one pixel equals one tile**
(48 × 48 px on screen). The game reads each pixel's RGB channels directly:

| Channel | Meaning |
| --- | --- |
| **Red** | Tile index into `heresy_tilesheet.png` (0–246). Values of 247 or higher mean empty space. |
| **Green** | Entity spawn (see table below). |
| **Blue** | Object spawn (see table below). |

A level image should be 14 pixels tall (the viewport height). Its width determines
how far the camera scrolls.

**Green channel — entities**

| Value | Spawn |
| --- | --- |
| 0 | Skeleton |
| 1 | Slime |
| 2 | Wolf |
| 3 | Blue Golem |
| 4 | Orange Golem |
| 5 | Brown Wolf |
| 6 | Mimic |
| 7 | Eyeball |
| 8 | Black Wolf |
| 9 | Demon Slime (boss) |
| 10 | Frost Guardian (boss) |
| 100 | Player spawn point |

**Blue channel — objects**

| Value | Spawn |
| --- | --- |
| 0 | Red potion (+15 HP) |
| 1 | Blue potion (+10 mana) |
| 2 | Barrel (drops a mana potion when smashed) |
| 3 | Jar (drops a healing potion when smashed) |
| 4 | Spike (instant death) |
| 5 | Cave spike (instant death) |

To add a level, drop `11.png` into `res/lvls/` using the same rules. Levels are
loaded in numeric order, so the filename is the level order.

## Chapters

| Levels | Biome | Music |
| --- | --- | --- |
| 1–3 | Oakwood | `level1.wav` |
| 4–6 | Caves | `cave_music.wav` |
| 7–9 | Castle | `castle_music.wav` |
| 10 | Boss arena | `boss_music.wav` |

## Bestiary

| Enemy | HP | Damage |
| --- | ---: | ---: |
| Skeleton | 40 | 15 |
| Slime | 35 | 10 |
| Wolf | 40 | 13 |
| Brown Wolf | 40 | 13 |
| Black Wolf | 40 | 13 |
| Blue Golem | 50 | 20 |
| Orange Golem | 50 | 20 |
| Mimic | 40 | 13 |
| Eyeball | 40 | 11 |
| **Demon Slime** (boss) | 80 | 23 |
| **Frost Guardian** (boss) | 80 | 23 |

## Development notes

- The game loop runs at a fixed **60 FPS render / 120 updates per second**
  (`Game.java`), with a 1248 × 672 window (26 × 14 tiles).
- **Debug hitboxes are always visible** (pink entity boxes, red attack boxes).
  Comment out the `drawHitbox` / `drawAttackBox` calls in `Player.render()` and
  the enemy draw methods in `EnemyManager` to hide them.
- `battlelog.txt` and `leaderboard.txt` are written to the **working directory**,
  not to `res/`.
- Levels are discovered through `/lvls` as a file URL, so the game expects to run
  from a folder of compiled classes (`bin/` or Eclipse) rather than a packaged
  `.jar`.

## Credits

Art, audio, and level assets live in `res/`. If you redistribute this project,
add the original asset creators' attribution and license terms here.

Code and game design: the Heresy project.
