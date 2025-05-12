# Java Chess Engine

A chess game developed in Java featuring a graphical user interface and an AI powered by the Minimax algorithm with Alpha-Beta pruning.

## Features

- **Game Modes**: Human vs. Human and Human vs. Computer
- **Difficulty Levels**: Easy, Medium, Hard mode for Human vs. Computer
- **Graphical Interface**: Interactive Java Swing GUI with a visual chessboard and pieces
- **AI Implementation**: Utilizes Minimax algorithm with Alpha-Beta pruning for computer opponent
- **Chess Rules Compliance**:
  - Check and checkmate detection
  - Castling
  - En passant
  - Pawn promotion
- **Undo and Redo**: Supports undoing and redoing moves during gameplay
- **Modular Design**: Organized into `Board`, `Pieces`, `GameLogic`, and `Players` components
- **Ease of Use**: Simple to compile, run, and extend

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or later
- `make` utility installed and accessible in your system's PATH

### Clone the Repository

```bash
git https://github.com/Avadhoot4757/Chess.git
cd Chess
```

### Build the Project

```bash
make
```

### Run the Project

```bash
make run
```

### Clean Build Files

```bash
make clean
```

> **Note**: Ensure that Java and `make` are properly installed before building or running the project.

## Future Enhancements

- Support saving and loading games in PGN format
- Add multiplayer support over LAN or internet
- Introduce sound effects and timers

## Resources

- [Chess Programming Wiki](https://www.chessprogramming.org)
- [Chess evaluation function](https://chess.stackexchange.com/questions/26893/chess-evaluation-function)
- [Micro-Max](https://home.hccnet.nl/h.g.muller/max-src2.html)
- [Design a Chess Game](https://www.geeksforgeeks.org/design-a-chess-game/)
- [Chess evaluation function](https://chess.stackexchange.com/questions/26893/chess-evaluation-function)
- [Java Swing Documentation](https://docs.oracle.com/javase/tutorial/uiswing/)