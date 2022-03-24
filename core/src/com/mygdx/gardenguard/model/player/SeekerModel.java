package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

public class SeekerModel extends PlayerModel {

    public SeekerModel(String playerID, Vector2 position) {
        super(playerID, position);
        this.setSteps(3);
        this.setMax_vision(false);
    }

    //Method for gaining points as a player
    @Override
    public void gainPoints() {
        this.setScore(5);
    }

    @Override
    public Stack<Vector2> getPath() {
        return null;
    }

    @Override
    public void pushPath(Vector2 tile) {

    }

}
