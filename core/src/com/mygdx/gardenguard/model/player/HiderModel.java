package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Vector2> path; //må endre Integer til Tile

    public HiderModel(Vector2 position) {
        super(position);
        this.setSteps(15);
        this.setMax_vision(true);
        super.setIsSeeker(false);
    }

    //ikke slett
    public HiderModel() {

    }

    @Override
    public void gainPoints() {
        this.setScore(10);
    }

    public Stack<Vector2> getPath() {
        return path;
    }

    public void pushPath(Vector2 tile) { //endre Integer til Tile
        path.push(tile);
    }

    public void popPath() {
        path.pop();
    }

}
