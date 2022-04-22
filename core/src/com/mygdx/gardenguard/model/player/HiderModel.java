package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Tile;

import java.util.ArrayList;
import java.util.Stack;

public class HiderModel extends PlayerModel {

    public HiderModel(Vector2 position) {
        super(position);
        super.setIsFound(false);
        super.setIsSeeker(false);
    }

    //Only used by Firebase
    public HiderModel() {

    }

}
