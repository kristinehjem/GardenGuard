package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.model.player.PlayerModel;

//PlayerController skal ta av seg all logikk som har med hvordan spilleren beveger seg på bordet per nå.

public class PlayerController {

    private String board; //Må endres til board
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
            System.out.println("Gå til høyre");
            move("right");
        }
        if(Gdx.input.isButtonJustPressed(2)) {
            System.out.println("Gå til vensre");
        }
        if(Gdx.input.isButtonJustPressed(3)) {
            System.out.println("Gå opp");
        }
        if(Gdx.input.isButtonJustPressed(4)) {
            System.out.println("Gå ned");
        }
    }

    private void moveRight() {

    }

    private void moveLeft() {

    }

    private void moveUp() {

    }

    private void moveDown() {

    }

    private checkCollision() {

    }


}
