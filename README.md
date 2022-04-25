# Garden Guard - hide and seek your friends

## **Table of Contents**
1. [Information](#Information) 
2. [User manual](#Howto)
3. [Overview of project structure](#Overview)


## Information <a name="Information"></a>
This repository is made by Ingrid Frøyland Gomo, Kristine Hjemgård, Sven Herman Holmsen, 
Sondre Westby Liestøl, Elen Katrine Paulsen and Beate Maria Kanutte Simonsen. 
The project is a result of the course TDT4240 Software Architecture, and the goal was to create an online multiplayer game.
Our game is called __Garden Guard__, which is inspired by a game similar to hide-and-seek.

## User manual <a name="Howto"></a>

### Requirements
To run this game one would need Android Studios. In addition one would need an Android device connected to this IDE or an emulator in this programming environment. The android device/emulator must run on Android version 5.0 (Lollipop) or higher. For example the emulator Pixel 3a API 29 is a suitable device. 

### Running the game
Following is an instruction to run the game for the emulator Pixel 3a API 29. 

Download the APK-file from the repository or from the assignment delivery on Blackboard. Drag the APK-file over to the emulator in Android Studio. A view similar to picture 1 will pop up. Then, when the download is finished, a notification will be shown as seen in picture 2.

Picture 1 | Picture 2
:------------:|:------------------:
<img src="/readme_images/1_install.png" width="150" /> | <img src="/readme_images/2_installed.png" width="250" />

Next, locate the downloaded game in the emulator. This can be done by first navigating to the navbar and clicking on the settings icon which can be seen in picture 3. Move to the "Apps and notifications" in the settings view in picture 4. Then, touch the "App info" which is shown in picture 5. Picture 6 shows this directory, and the libGDX file with the name Garden Guard. Touch the file and then click "Open" to start running the game.

Picture 3 | Picture 4 | Picture 5 | Picture 6 
:------------:|:------------------:|:---------:|:---------:
<img src="/readme_images/3_navbar_marked.png" width="150" /> | <img src="/readme_images/4_settings_marked.png" width="150" /> | <img src="/readme_images/5_appsANDnotfications_marked.png" width="150" /> | <img src="/readme_images/6_apps_marked.png" width="150" />

## Overview of project structure <a name="Overview"></a>
Following is an overview of the most important files in our project. Branches with dotted lines (...) 
indicates other files/repositories within the branch. They have been omitted to simplify the 
overall structure. Especially the directories of API, controller, model and view in the core-directory contains several java-files which we have coded. The GardenGuard.java and GameStateManager.java are kept outside of the MVC-pattern in core, and this is discussed more thoroughly in the implementation document. The assets- and resources-directories contains pictures used in our application.

```
GardenGuard
├── android
│   ├── java
│   │    └── com.mygdx.GardenGuard
│   │         ├── AndroidInterFaceClass.java
│   │         └── AndroidLauncher.java
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
│             │    └── ...
│             ├── GameStateManager.java
│             ├── GardenGuard.java
│             └── ...
├── desktop
│   ├── java
│   │    └── com.mygdx.GardenGuard
│   │         ├── DesktopInterFaceClass.java
│   │         └── DesktopLauncher.java
│   └── resources
│        └── ...
├── gradle/wrapper
│   └── ...
├── README.md
└── ...
```
