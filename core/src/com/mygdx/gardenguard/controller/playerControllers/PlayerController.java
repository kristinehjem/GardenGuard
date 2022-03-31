package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.board.Tile;

/*
* PlayerController skal ta seg av all logikk som har med hvordan spilleren beveger seg på bordet per nå.
*/

public abstract class PlayerController extends Controller {

    protected Board board;


    public PlayerController(Board board) {
        this.board = board;
    }

    public abstract PlayerModel getPlayer();

    // Denne metoden setter texturen til playeren ut i fra når den joinet spillet
    // Flytter denne metoden til menu state. Kommenterer den derfor bare ut her.
    /*public void setTexture() {
        int numOfPlayers = super.getPlayers().size();
        for(int playerNo = 0; playerNo < numOfPlayers; playerNo++) {
            if(super.gsm.getPlayer().getPlayerID() == super.getPlayers().get(playerNo).getPlayerID()) {
                super.gsm.getPlayer().setTexture("player" + String.valueOf(playerNo) + ".png");
            }
        }
    }*/

    public void updatePosition() {
        if(Gdx.input.isButtonJustPressed(1)) { // TODO: endre isButtonJustPressed() til noe som passer med knappene våre
            System.out.println("Gå til høyre \n");
            moveRight();
        }
        if(Gdx.input.isButtonJustPressed(2)) {
            System.out.println("Gå til venstre \n");
            moveLeft();
        }
        if(Gdx.input.isButtonJustPressed(3)) {
            System.out.println("Gå opp \n");
            moveUp();
        }
        if(Gdx.input.isButtonJustPressed(4)) {
            System.out.println("Gå ned \n");
            moveDown();
        }
    }

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
