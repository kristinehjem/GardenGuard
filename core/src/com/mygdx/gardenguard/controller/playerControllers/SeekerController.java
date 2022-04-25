package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

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
        this.view = new Rectangle(x_pos -1f, y_pos - 1f,  2, 2);
    }

    @Override
    public SeekerModel getPlayer() {
        return player;
    }

    // Checks if a player is in view
    public void checkForPlayers() {
        for(PlayerModel hider : getPlayers()) {
            if (getView().contains(hider.getPosition()) && !hider.getIsFound() && !hider.getIsSeeker()) {
                player.setScore(player.getScore() + 5);
                gsm.getFBIC().UpdateIsFoundInDB(gsm.getGamePin(), hider.getPlayerID(), true);
                gsm.getFBIC().UpdateScoreInDB(gsm.getGamePin(), player.getPlayerID(), player.getScore());
            }
        }
    }
}