package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.math.Vector2;

public class SeekerModel extends PlayerModel {

    public SeekerModel(Vector2 position) {
        super(position);
        super.setIsSeeker(true);
        super.setIsFound(false);
    }

    //Used by Firebase
    public SeekerModel() {}

}
