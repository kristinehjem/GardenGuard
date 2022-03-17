package com.mygdx.gardenguard.model.player;

public class SeekerModel extends PlayerModel {

    public SeekerModel(String playerID, String position) {
        super(playerID, position);
        this.setSteps(3);
        this.setMax_vision(false);
    }



}
