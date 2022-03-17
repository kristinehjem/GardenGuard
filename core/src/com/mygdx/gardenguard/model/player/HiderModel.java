package com.mygdx.gardenguard.model.player;

import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Integer> path; //må endre Integer til Tile

    public HiderModel(int playerID, String position) {
        super(playerID, position);
        this.setSteps(15);
        this.setMax_vision(true);
    }

    @Override
    public void gainPoints() {
        this.setScore(10);
    }

}
