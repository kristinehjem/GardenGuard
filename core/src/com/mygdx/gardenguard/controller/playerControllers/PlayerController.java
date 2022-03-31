package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.board.Tile;

/*
* PlayerController skal ta seg av all logikk som har med hvordan spilleren beveger seg på bordet per nå.
*/

public abstract class PlayerController {

    protected Board board; //Må endre type til board


    public PlayerController(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public abstract PlayerModel getPlayer();

    public void updatePosition() {
        if(Gdx.input.isButtonJustPressed(1)) {
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
    protected abstract void moveRight();

    protected abstract void moveLeft();

    protected abstract void moveUp();

    protected abstract void moveDown();

    protected boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }


}
