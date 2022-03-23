package com.mygdx.gardenguard.API;

public class Player {

    public String name;
    public String position;
    public String playerID;

    public Player() {

    }

    public Player(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public String getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
}
