package com.mygdx.gardenguard.model.player;

public class HiderModel extends PlayerModel {

    public HiderModel(String playerID, String position) {
        super(playerID, position);
        this.setSteps(10);
        this.setMax_vision(true);
    }

    @Override
    public void gainPoints() {
        this.setScore(10);
    }



}
