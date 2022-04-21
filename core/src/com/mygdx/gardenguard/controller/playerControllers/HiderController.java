package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;

public class HiderController extends PlayerController {

    private HiderModel player;

    public HiderController(HiderModel player, Board board) {
        super(board);
        this.player = player;
    }

    @Override
    public HiderModel getPlayer() {
        return player;
    }

    protected void checkForPlayers() {}

    public void foundByHider(HiderModel hider) {
        hider.setIsFound(true);
        gsm.getFBIC().UpdateIsFoundInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
    }
}
