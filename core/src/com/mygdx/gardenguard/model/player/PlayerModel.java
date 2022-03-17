package com.mygdx.gardenguard.model.player;

public abstract class PlayerModel {

    private int playerID;
    private String position; //Skal ha et board (Board) som består av tiles (Tile).
    private int score;
    private boolean max_vision;
    private int steps;
    //private firebase for å kunne lage playerID og lagre informasjonen
    //private int face: Sier noe om hvilken retning spriten ser.

    public PlayerModel(int playerID, String position){

        this.playerID = playerID;
        this.position = position;
        this.score = 0;
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

    public int getPlayerID() {
        return playerID;
    }

    protected void setMax_vision(boolean max_vision) {
        this.max_vision = max_vision;
    }

    public void setScore(int points) {
        this.score += points;
    }

    public abstract void gainPoints();

}

