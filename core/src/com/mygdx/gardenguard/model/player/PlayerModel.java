package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.utils.Array;

public abstract class PlayerModel {

    private int playerID;
    //private String position; //Skal ha et board (Board) som består av tiles (Tile).
    // Forslag Ingrid: heller ha posisjon som x-verdi og y-verdi, og at det sammen gir en tile
    private int xPos;
    private int yPos;
    private int score;
    private boolean max_vision;
    private int steps;
    //private firebase for å kunne lage playerID og lagre informasjonen
    //private int face: Sier noe om hvilken retning spriten ser.

    public PlayerModel(int playerID, int xPos, int yPos){

        this.playerID = playerID;
        this.xPos = xPos;
        this.yPos = yPos;
        //this.position = position;
        this.score = 0;
    }

    protected void setSteps(int i) {
        this.steps = i;
    }

    // Ulempen med å dele posisjon opp i x og y er at da trenger man dobbelt sett med variabler og gettere.
    // Men samtidig så ser jeg ikke hvordan vi skal finne eks. tilen til høyre om man ikke har x- og y-verdier.
    // Men om vi finner ut dette, så kan mpten man bruker posisjon oå selvfølgelig endres.
    /*public String getPosition() {
        return position;
    }*/

    public int getxPos() {
        return this.xPos;
    }

    public int getyPos() {
        return this.yPos;
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

    public abstract Array getPath();
}

