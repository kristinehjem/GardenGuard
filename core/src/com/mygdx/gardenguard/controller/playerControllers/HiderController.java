package com.mygdx.gardenguard.controller.playerControllers;

import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;

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
}
