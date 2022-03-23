package com.mygdx.gardenguard.model.player;

import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Integer> path; //må endre Integer til Tile

    public HiderModel(int playerID, int xPos, int yPos) {
        super(playerID, xPos, yPos);
        this.setSteps(15);
        this.setMax_vision(true);
    }

    @Override
    public void gainPoints() {
        this.setScore(10);
    }

    public Stack<Integer> getPath() {
        return path;
    }

    public void pushPath(Integer tile) { //endre Integer til Tile
        path.push(tile);
    }

    public void popPath() {
        path.pop();
    }

}
