# StoryGen

A data-driven story generation engine built with Kotlin. StoryGen focuses on creating dynamic, persistence-backed narrative worlds where characters, locations, and objects are managed through a robust database schema.

## Key Features
- **Persistence-Driven**: Entire world state, including character history and object locations, is stored in an H2 database using JetBrains Exposed.
- **Dynamic World Engine**: Flexible domain models for `Character`, `Location`, and `WorldObject`.
- **Random Encounters**: A registry-based encounter system that generates dynamic interactions at runtime.
- **Modern Kotlin**: Fully modernized to use **Kotlin 2.1.0** features like sealed interfaces, data objects, and efficient collection builders.

## Technology Stack
- **Language**: Kotlin 2.1.0
- **Database**: H2 (Embedded)
- **ORM**: JetBrains Exposed
- **Build Tool**: Gradle (with Kotlin DSL)

## How to Run
To run the story engine with initial test data:
```bash
./gradlew run --args="--seed"
```
This will initialize the schema, seed the world with sample data, and output a persistence-driven story sequence.

## Project Structure
- `domain`: Core interfaces and entities (`Character.kt`, `Location.kt`, etc.).
- `application`: Business logic, database initialization, and repository management.
