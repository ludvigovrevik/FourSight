# Connect four - JavaFX Application

## Play against our Monte carlo tree search algorithm!
  <img src="/docs/four_in_a_row.gif" width="400"> 

## Overview
A small project created by **Thomas Sørensen** and **Ludvig Øvrevik** during our first year at **NTNU Trondheim**.

This is a JavaFX-based **Connect four** game with an option to play against the **Monte Carlo Tree Search (MCTS)** algorithm. The project was created as part of an object-oriented programming course in our first year, while the MCTS was a fun side project since we were also implementing AlphaZero. The game is built using Java and JavaFX, and includes an intuitive user interface.



## Features
- Play **Connect four** against another player or an **AI opponent** (MCTS).
- Interactive **Graphical User Interface (GUI)** built with JavaFX.
- Responsive board with real-time updates.
- Simple, easy-to-use controls.

## Installation
### **Prerequisites**
Ensure you have the following installed:
- **Java 17+** (required for JavaFX support)
- **Git** (for cloning the repository)


# Run the Game

## Windows
Open a command prompt (cmd) and navigate to the project folder:

```cmd
cd path\to\project
```
Run the game using the batch script:

```
run-windows.bat
```

## macOS/Linux
Open a terminal and navigate to the project folder:

```cmd
cd path/to/project
```
Give execution permission (only needed once):

```
chmod +x run.sh
```

Run the game:
```
./run.sh
```
# How to use
MCTS simulates 1000 games every time it's the algorithms turn. You can see how it choose its move in the terminal. You can increase or lower the amount of simulation to make the algorithm better or worse respectively. 

# Contributing
Feel free to fork the repository and submit pull requests! If you find a bug, create an issue on GitHub or GitLab.

# License
This project is licensed under the MIT License.