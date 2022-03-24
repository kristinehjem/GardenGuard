package com.mygdx.gardenguard.model.player;

<<<<<<< HEAD
import com.badlogic.gdx.math.Vector2;
import java.util.Stack;

public abstract class PlayerModel {

    private String playerID;
    private Vector2 position;
=======
import com.badlogic.gdx.utils.Array;

public abstract class PlayerModel {

    private int playerID;
    //private String position; //Skal ha et board (Board) som består av tiles (Tile).
    // Forslag Ingrid: heller ha posisjon som x-verdi og y-verdi, og at det sammen gir en tile
    private int xPos;
    private int yPos;
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
    private int score;
    private boolean max_vision;
    private int steps;
    //private firebase for å kunne lage playerID og lagre informasjonen
    //private int face: Sier noe om hvilken retning spriten ser.

<<<<<<< HEAD
    public PlayerModel(String playerID, Vector2 position){
        this.playerID = playerID;
        this.position = position;
=======
    public PlayerModel(int playerID, int xPos, int yPos){

        this.playerID = playerID;
        this.xPos = xPos;
        this.yPos = yPos;
        //this.position = position;
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
        this.score = 0;
    }

    protected void setSteps(int i) {
        this.steps = i;
    }

<<<<<<< HEAD
    public Vector2 getPosition() {return this.position;}

    public void setPosition(int xPos, int yPos) {
        this.position = new Vector2(xPos, yPos);
=======
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
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
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

<<<<<<< HEAD
    public String getPlayerID() {
=======
    public int getPlayerID() {
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
        return playerID;
    }

    protected void setMax_vision(boolean max_vision) {
        this.max_vision = max_vision;
    }

    public void setScore(int points) {
        this.score += points;
    }

    public abstract void gainPoints();

<<<<<<< HEAD
    public abstract Stack<Vector2> getPath();

    public abstract void pushPath(Vector2 tile);
=======
    public abstract Array getPath();
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
}

