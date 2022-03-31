package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.Stack;

public abstract class PlayerModel implements Comparable<PlayerModel> {

    private String playerID;
    private Vector2 position;
    private int score;
    private int steps;
    private String textureFile;
    protected boolean isSeeker;
    private String username;

    public PlayerModel(Vector2 position){
        this.position = position;
        this.score = 0;
        this.username = "";
    }

    //ikke slett denne, trenger for database
    public PlayerModel() {}

    public void setTexture(String textureFile) { this.textureFile = textureFile;};
    public String getTextureFile() { return this.textureFile;};

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

    public void setScore(int points) {
        this.score += points;
    }
    public int getScore() {
        return score;
    }

    protected void setIsSeeker(boolean isSeeker) { this.isSeeker = isSeeker; }
    public boolean getIsSeeker() { return this.isSeeker; }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public abstract void gainPoints();

    public abstract Stack<Vector2> getPath();

    public abstract void pushPath(Vector2 tile);

    public int compareTo(PlayerModel other) {
        return this.getScore() - other.getScore();
    }
}

