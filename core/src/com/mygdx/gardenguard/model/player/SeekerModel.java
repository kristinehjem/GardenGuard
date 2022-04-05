package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Tile;

import java.util.Stack;

public class SeekerModel extends PlayerModel {

    public SeekerModel(Vector2 position) {
        super(position);
        this.setSteps(10);
        super.setIsSeeker(true);
    }

    //ikke slett, trenger til databasen
    public SeekerModel() {}

    //Method for gaining points as a player

    @Override
    public Stack<Vector2> getPath() {
        return null;
    }

    @Override
    public void pushPath(Vector2 position) {

    }

}
