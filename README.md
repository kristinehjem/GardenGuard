# Garden Guard - hide and seek your friends

## **Table of Contents**
1. [Information](#Information) 
2. [How to run program](#Howto)
3. [Overview of project structure](#Overview)


## Information <a name="Information"></a>
This repository is made by Ingrid Frøyland Gomo, Kristine Hjemgård, Sven Herman Holmsen, 
Sondre Westby Liestøl, Elen Katrine Paulsen and Beate Maria Kanutte Simonsen. 
The project is a result of the course TDT4240 Software Architecture with a goal to create a game.
Our game is called __Garden Guard__, which is inspired by a game similar to hide-and-seek.

## How to run program <a name="Howto"></a>
> ##### TODO: How to run program.

To run the program you press cmd+alt+del and pray to higher powers that nothing happened.

## Overview of project structure <a name="Overview"></a>
Following is an overview of the most important files in our project. Branches with dotted lines (...) 
indicates other files/repositories within the branch. They have been omitted to simplify the 
overall structure.

```
GardenGuard
├── android
│   ├── java
│   │    └── com.mygdx.GardenGuard
│   │         └── ...
│   ├── assets
│   │    ├── skin
│   │    └── ...
│   └── ...
├── core
│   └── java
│        └── com.mygdx.GardenGuard
│             ├── API
│             │    └── ...
│             ├── controller
│             │    ├── playerControllers
│             │    │    └── ...
│             │    └── stateControllers
│             │         └── ...
│             ├── model
│             │    ├── board
│             │    │    └── ...
│             │    └── player
│             │         └── ...
│             ├── view
│             │    ├── playViews
│             │    │    └── ...
│             │    └── GameStateManager.java
│             │    └── ... 
│             ├── GardenGuard.java
│             └── ...
├── desktop
│   ├── java
│   │    └── com.mygdx.GardenGuard
│   │         └── ...
│   └── assets
│        ├── skin
│        └── ...
├── gradle/wrapper
│   └── ...
├── README.md
└── ...
```
