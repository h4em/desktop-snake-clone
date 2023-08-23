# 🐍 desktop-snake-clone 🐍

## About
This repo showcases my [Snake game](https://en.wikipedia.org/wiki/Snake_(video_game_genre)) implementation I did as one 
of my Java class assignments.

## Gameplay
The player controls the snake's direction with **arrow keys**, with goal of collecting as much food as possible - getting the best score he can. 
As the snake eats food, it grows - making the game progressively more difficult. If the snake crashes (by running into itself or the gameboard's wall) the game ends, 
giving the player option to play again or quit. Game keeps track of the player's highscore by saving it to a binary file. 

## Setup
Compile the project yourself and run it through your CLI/IDE or create .jar executable:

`C:\snake> jar cvfm Snake.jar MANIFEST.mf *.class`

and run it by either double-clicking the icon or by:

`C:\gsm> java -jar Snake.jar`

Java's `jar.exe` utility is located in the JDK's `\bin` folder so make sure your system `PATH` variable includes it.

## Technology
It's build in the Swing framework, and is based on MVVM design pattern to 
ensure separation between the game's GUI and internal logic modules. 
The communication between the two is done through events and according listeners.

## Preview
![](res/preview.gif)