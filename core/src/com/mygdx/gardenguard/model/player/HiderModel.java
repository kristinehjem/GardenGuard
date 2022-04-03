package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Tile;

import java.util.ArrayList;
import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Vector2> path;

    public HiderModel(Vector2 position) {
        super(position);
        this.setSteps(15);
<<<<<<< HEAD
=======
        this.setMax_vision(true);
        this.setIsFound(false);
>>>>>>> f2853cc (Added view and some more functionality)
        super.setIsSeeker(false);
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
