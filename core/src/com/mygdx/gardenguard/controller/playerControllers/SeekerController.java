package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
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

    public void updateView() {
        float x_pos = this.player.getPosition().x;
        float y_pos = this.player.getPosition().y;
        this.view = new Rectangle(x_pos - tileWidth, y_pos - tileHeight, tileWidth * 2, tileHeight *2);
    }

    @Override
    public SeekerModel getPlayer() {
        return player;
    }

    // Checks if a player is in view
    // TODO: The object which uses this controller is a hider. Needs to be changed for score to work
    public void checkForPlayers() {
        List<PlayerModel> list_player = super.gsm.getPlayers();
        for(PlayerModel hider : list_player) {
            if (getView().contains(hider.getPosition()) && !hider.getIsFound() && !hider.getIsSeeker()) {
                player.setScore(player.getScore() + 5);
                super.gsm.getFBIC().UpdateIsFoundInDB(gsm.getGamePin(), player.getPlayerID(), true);
                super.gsm.getFBIC().UpdateScoreInDB(gsm.getGamePin(), player.getPlayerID(), player.getScore());
            }
        }
    }
}