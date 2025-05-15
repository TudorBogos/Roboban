# Roboban 🕹️ – Java Sokoban Clone with Design Patterns

Roboban este un joc desktop scris în **Java** ce recreează mecanica clasică Sokoban: împingi cutiile (📦) pe pozițiile‑țintă (🎯) într‑un labirint. Codul servește drept studiu de caz pentru **cinci șabloane de proiectare** și o arhitectură modulară, ușor de extins.

> TL;DR la final, dacă vrei varianta rapidă.

---

## 📐 Design Patterns implementate
| Categorie | Pattern | Clasă principală | Rol |
|-----------|---------|------------------|-----|
| Creational | **Builder** | `LevelBuilder` | Construiește incremental un nivel (board, player, cutii). |
| Structural | **Adapter** | `AudioAdapter` | Adaptează librăria audio externă la o interfață unificată. |
| Behavioral | **Command** | `MoveCommand` | Encapsulează o mutare; viitorul suport pentru Undo devine trivial. |
| Behavioral | **Mediator** | `GameMediator` | Coordonează interacțiunile Board ↔ UI, centralizând logica. |
| Singleton | **Singleton** | `GameManager` | Stare globală (nivel curent, scor, număr de mutări). |

---

## 🔧 Tehnologii și librării

- **Java 17+** – limbaj principal
- **Java Swing** – interfață grafică (UI clasic desktop)
- **JUnit 5** – testare automată
- **ExternalSoundLib** (sau Java `AudioSystem`) – efecte audio
- **Maven** (opțional) – build și rulare test
- Fără dependențe exotice: totul rulează out‑of‑the‑box cu JDK + JRE.

---

## 🗂️ Structura pachetelor

```
com.roboban
├── model        # Board, Tile, Player, Box, Direction
├── singleton    # GameManager
├── command      # Command, MoveCommand
├── mediator     # GameMediator
├── adapter      # GameAudioInterface, ExternalSoundLib, AudioAdapter
├── builder      # LevelBuilder
└── ui           # GameWindow, MainMenuPanel, GamePanel
```

---

## 🏛️ Arhitectura pe straturi

1. **UI (Presentation)** – pachetul `ui`  
   - Desenează board‑ul și preia input‑ul tastaturii.  
   - Trimite acțiunile spre `GameMediator`.

2. **Logică (Domain)** – pattern‑urile `mediator`, `command`, `builder`, `model`  
   - Se ocupă de mutări, validări și construire niveluri.

3. **Infrastructură** – `adapter`, `singleton`  
   - Audio, scoruri, nivel curent, salvări rapide (viitor).

Această separare face proiectul testabil și plug‑&‑play pentru viitoare feature‑uri (ex. niveluri noi, multiplayer local, AI etc.). citeturn0file0

---

## 🚀 Cum pornești jocul

```bash
# Clonează repo‑ul
git clone https://github.com/<user>/roboban.git
cd roboban

# Rulează din IDE (IntelliJ / VSCode / NetBeans) **sau**:
# Compilează rapid cu Maven
mvn clean package
java -jar target/roboban.jar
```

### Controale implicite
- **W / A / S / D** – deplasare
- **ESC** – revenire la meniu
- **M** – toggling muzică (prin `AudioAdapter`)

---

## 🧪 Testare

Rularea testelor JUnit:

```bash
mvn test
```

Rezumatul setului de teste (din documentație):
- `BoardTest` – 4/4
- `EntitiesTest` – 2/2
- `MoveCommandTest` – 3/3
- `GameMediatorTest` – 2/2
- `GameManagerTest` – 3/3
- `LevelBuilderTest` – 1/1

---

## 🌱 Roadmap / Idei de extindere

- ❎ **Undo/Redo** prin `Command` + `Caretaker`
- 🌐 Portare pe **JavaFX** sau chiar **Unity** pentru grafică modernă
- 💾 Serializare niveluri custom (`.rblvl`) + editor vizual
- 🕹️ Leaderboard global (REST API + SQLite)

---

## 📚 Bibliografie & Resurse

- Documentația de proiect (Universitatea Dunărea de Jos) citeturn0file0
- *Design Patterns: Elements of Reusable Object‑Oriented Software* – GoF
- Tutoriale JUnit & Swing (Oracle, Baeldung, etc.)

---

## TL;DR

Java Sokoban clone cu 5 pattern‑uri (Builder, Adapter, Command, Mediator, Singleton), Swing UI, JUnit tests, sunet și arhitectură curată. Clonează, comentează și împinge cutia până la țintă – în 60 de secunde ești gata de joc.
