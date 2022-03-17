package com.mygdx.gardenguard.model.player;

public abstract class PlayerModel {

    private String playerID;
    private String position; //Skal ha et board (Board) som består av tiles (Tile).
    private int score;
    private boolean max_vision;
    private int steps;
    //private firebase for å kunne lage playerID og lagre informasjonen
    //private int face: Sier noe om hvilken retning spriten ser.

    public PlayerModel(String playerID, String postion){

        this.playerID = playerID;
        this.score = 0;
        this.position = position;
    }

    protected void setSteps(int i) {
        this.steps = i;
    }

    public String getPosition() {
        return position;
    }

    public boolean isMax_vision() {
        return max_vision;
    }

    public int getScore() {
        return score;
    }

    public int getSteps() {
        return steps;
    }

    public String getPlayerID() {
        return playerID;
    }

    protected void setMax_vision(boolean max_vision) {
        this.max_vision = max_vision;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setScore(int score) {
        this.score = score;
    }

}

