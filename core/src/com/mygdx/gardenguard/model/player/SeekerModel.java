package com.mygdx.gardenguard.model.player;

public class SeekerModel extends PlayerModel {

    public SeekerModel(int playerID, String position) {
        super(playerID, position);
        this.setSteps(3);
        this.setMax_vision(false);
    }

    //Method for gaining points as a player
    @Override
    public void gainPoints() {
        this.setScore(5);
    }


}
