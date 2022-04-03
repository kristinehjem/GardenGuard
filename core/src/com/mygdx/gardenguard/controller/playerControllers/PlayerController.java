package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;

/*
* PlayerController skal ta seg av all logikk som har med hvordan spilleren beveger seg på bordet per nå.
*/

public abstract class PlayerController extends Controller {

    protected Board board;


    public PlayerController(Board board) {
        this.board = board;
    }

    public abstract PlayerModel getPlayer();

    public void updatePosition() {
        // Logikken for flytting som nå ligger i PlayState burde flyttes hit
    }

    protected abstract void checkForPlayers();

    // Bytt til try catch hvis dette ikke fungerer: https://coderanch.com/t/649165/java/prevent-user-bounds-simple-array
    // Disse metodene implementeres hver for seg i hider og seeker, siden hider skal gå og lagre det i en stack, mens seeker bare skal gå
    protected abstract void moveRight();

    protected abstract void moveLeft();

    protected abstract void moveUp();

    protected abstract void moveDown();

    protected boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }


}
