package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.List;

public class SeekerController extends PlayerController {

    private SeekerModel player;
    private Rectangle view;

    public SeekerController(SeekerModel player, Board board) {
        super(board);
        this.player = player;
        this.view = new Rectangle();

    }

    public Rectangle getView() {
        return this.view;
    }

    private void updateView() {
        float x_pos = this.player.getPosition().x;
        float y_pos = this.player.getPosition().y;

        this.view = new Rectangle(x_pos - tileWidth, y_pos - tileHeight, tileWidth * 2, tileHeight *2);

    }

    @Override
    public SeekerModel getPlayer() {
        return player;
    }

    protected void moveRight() {
        if(player.getPosition().x == board.getTiles()[0].length) {
            System.out.println("Player cannot move further right, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x+1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)+1, (int) player.getPosition().y);
            updateView();
        }
    }

    protected void moveLeft() {
        if(player.getPosition().x == board.getTiles()[0].length) {
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x-1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)-1, (int) player.getPosition().y);
            updateView();
        }
    }

    protected void moveUp() {
        if(player.getPosition().y == board.getTiles()[0].length) {
            System.out.println("Player cannot move further up, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y-1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y-1);
            updateView();
        }
    }

    protected void moveDown() {
        if(player.getPosition().y == board.getTiles()[0].length) {
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y+1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y+1);
            updateView();
        }
    }

    // Checks if a player is in view
    public void checkForPlayers() {
        List<PlayerModel> list_player = super.getPlayers();
        for(PlayerModel other_players : list_player) {
            if(getView().contains(other_players.getPosition()) || !other_players.isFound()) {
                super.gsm.getFBIC().UpdateIsFoundInDB(gsm.getPin(), player.getPlayerID(), String.valueOf(true));
                super.gsm.getFBIC().UpdateScoreInDB(gsm.getPin(), player.getPlayerID(), String.valueOf(player.getScore() + 5));

            }
        }
    }

}