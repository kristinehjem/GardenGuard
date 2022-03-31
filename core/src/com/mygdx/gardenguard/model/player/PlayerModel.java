package com.mygdx.gardenguard.model.player;


import com.badlogic.gdx.math.Vector2;
import java.util.Stack;

public abstract class PlayerModel implements Comparable<PlayerModel> {

    private String playerID;
    private Vector2 position;
    private int score;
    private boolean max_vision;
    private int steps;
    protected boolean isSeeker;
    protected boolean isFounded;

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
    public String getPlayerID() {
        return playerID;
    }

    protected void setSteps(int i) {
        this.steps = i;
    }
    public int getSteps() {
        return steps;
    }

    public void setPosition(int xPos, int yPos) {
        this.position = new Vector2(xPos, yPos);
    }
    public Vector2 getPosition() {return this.position;}

    public boolean isMax_vision() {
        return max_vision;
    }

    public void setScore(int points) {
        this.score += points;
    }
    public int getScore() {
        return score;
    }

    protected void setMax_vision(boolean max_vision) {
        this.max_vision = max_vision;
    }

    protected void setIsSeeker(boolean isSeeker) { this.isSeeker = isSeeker; }
    public boolean getIsSeeker() { return this.isSeeker; }

    public abstract void gainPoints();

    public abstract Stack<Vector2> getPath();

    public abstract void pushPath(Vector2 tile);

    public int compareTo(PlayerModel other) {
        return this.getScore() - other.getScore();
    }

    public boolean isFounded() {
        return isFounded;
    }

    public void setIsFound(boolean isFounded) {
        this.isFounded = isFounded;
    }


}

