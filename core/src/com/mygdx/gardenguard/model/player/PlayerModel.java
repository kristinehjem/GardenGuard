package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;
import java.util.Stack;

public abstract class PlayerModel {

    private String playerID;
    private Vector2 position;
    private int score;
    private boolean max_vision;
    private int steps;
    protected boolean isSeeker;

    public PlayerModel(Vector2 position){
        this.position = position;
        this.score = 0;
    }

    //ikke slett denne, trenger for database
    public PlayerModel() {

    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    protected void setSteps(int i) {
        this.steps = i;
    }


    public Vector2 getPosition() {return this.position;}

    public void setPosition(int xPos, int yPos) {
        this.position = new Vector2(xPos, yPos);
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

    public void setScore(int points) {
        this.score += points;
    }

    public abstract void gainPoints();

    public abstract Stack<Vector2> getPath();

    public abstract void pushPath(Vector2 tile);
}

