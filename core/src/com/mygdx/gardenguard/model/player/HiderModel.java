package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Tile;

import java.util.ArrayList;
import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Vector2> path;

    public HiderModel(Vector2 position) {
        super(position);
        super.setIsFound(false);
        super.setIsSeeker(false);
        this.path = new Stack<>();
    }

    //Ikke slett, trenger til database
    public HiderModel() {

    }

    public Stack<Vector2> getPath() {
        return path;
    }

    public void pushPath(Vector2 position) { //endre Integer til Tile
        path.push(position);
    }

    public void popPath() {
        path.pop();
    }


}
