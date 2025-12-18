# UNO (Java)

## Overview
This project is a **Java implementation of the card game UNO**, developed as a final project.
The game follows standard UNO rules with support for **custom house rules**, real player interaction, and structured object-oriented design.

The program models the full gameplay loop including deck creation, card actions, turn management, and win conditions.

---

## Features
- Full UNO gameplay logic implemented in Java  
- Object-oriented card hierarchy (Number, Skip, Reverse, Wild, etc.)
- Turn-based game flow with draw and play mechanics
- Support for **custom house rules**

---

## Project Structure

All source files are located in the `src/` directory.

### Core Game Classes
- **Game.java**  
  Controls the main game loop, turn order, player interactions, and win conditions.

- **Uno.java**  
  Entry point for the program. Initializes the game and applies house rules.

- **Deck.java**  
  Handles deck creation, shuffling, and card distribution.

- **Draw.java**  
  Implements draw card behavior and penalties.

---

### Card System
- **Card.java**  
  Abstract/base class for all UNO cards.

- **Number.java**  
  Represents standard numbered UNO cards.

- **Skip.java**  
  Implements skip turn behavior.

- **Reverse.java**  
  Handles reversing the turn order.

- **Wild.java**  
  Implements wild card logic, including color selection.

---

### Rules
- **HouseRules.java**  
  Contains custom rules and modifications beyond standard UNO gameplay.
