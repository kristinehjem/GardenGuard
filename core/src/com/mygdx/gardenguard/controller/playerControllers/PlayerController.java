package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.model.player.PlayerModel;

//PlayerController skal ta av seg all logikk som har med hvordan spilleren beveger seg på bordet per nå.

public class PlayerController {

    private String board; //Må endre type til board
    private PlayerModel player;

    public PlayerController(PlayerModel player, String board) {
        this.player = player;
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public PlayerModel getPlayer() {
        return player;
    }

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

    private void moveRight() {
        // x og y i denne if-en må gjøres om. Tanken er at [][] skal være tilen til høyre (det er det ikke nå). Må regne om mellom posisjonen til player og rutenummer i matrisen.
        //if(board.getTiles()[y][x].isWalkable) {
        //    path.push(board.getTiles()[y][x]);
            // et sted i koden må vi vise steps left. Det beregner vi fra player.getSteps() - path.size.
        //}
    }

    private void moveLeft() {

    }

    private void moveUp() {

    }

    private void moveDown() {

    }

    private boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }


}
