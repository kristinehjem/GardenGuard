package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Vector2;
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

    protected abstract void checkForPlayers();

    public void move(String direction) {
        PlayerModel player = getPlayer();

        if (player.getSteps() > 0) {
            switch (direction) {
            case "up":
                if(player.getPosition().y == 14) {
                    System.err.println("Player cannot move further up, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y + 1][(int) player.getPosition().x].isWalkable()) {
                    int x = (int) player.getPosition().x;
                    int y = (int) player.getPosition().y + 1;
                    player.setPosition(x, y);
                    gsm.getFBIC().UpdatePositionInDB(gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.err.print("Can't move up because of hedge \n");
                }
                break;
            case "down":
                if(player.getPosition().y == 0) {
                    System.err.println("Player cannot move further down, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y - 1][(int) player.getPosition().x].isWalkable()) {
                    int x = (int) player.getPosition().x;
                    int y = (int) player.getPosition().y - 1;
                    player.setPosition(x, y);
                    gsm.getFBIC().UpdatePositionInDB(gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.err.print("Can't move down because of hedge \n");
                }
                break;
            case "left":
                if(player.getPosition().x == 0) {
                    System.err.println("Player cannot move further left, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x - 1].isWalkable()) {
                    int x = (int) player.getPosition().x - 1;
                    int y = (int) player.getPosition().y;
                    player.setPosition(x, y);
                    gsm.getFBIC().UpdatePositionInDB(gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.err.print("Can't move left because of hedge \n");
                }
                break;
            case "right":
                if(player.getPosition().x == 8) {
                    System.err.println("Player cannot move further right, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x + 1].isWalkable()) {
                    int x = (int) player.getPosition().x + 1;
                    int y = (int) player.getPosition().y;
                    player.setPosition(x, y);
                    gsm.getFBIC().UpdatePositionInDB(gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.err.print("Can't move right because of hedge \n");
                }
                break;
            }
            checkForPlayers();
        } else {
            System.err.println("You have no steps left");
        }
    }
}
