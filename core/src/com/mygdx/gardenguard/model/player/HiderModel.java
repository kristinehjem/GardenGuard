package com.mygdx.gardenguard.model.player;

<<<<<<< HEAD
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Vector2> path; //må endre Integer til Tile

    public HiderModel(String playerID, Vector2 position) {
        super(playerID, position);
=======
import java.util.Stack;

public class HiderModel extends PlayerModel {
    private Stack<Integer> path; //må endre Integer til Tile

    public HiderModel(int playerID, int xPos, int yPos) {
        super(playerID, xPos, yPos);
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
        this.setSteps(15);
        this.setMax_vision(true);
    }

    @Override
    public void gainPoints() {
        this.setScore(10);
    }

<<<<<<< HEAD
    public Stack<Vector2> getPath() {
        return path;
    }

    public void pushPath(Vector2 tile) { //endre Integer til Tile
=======
    public Stack<Integer> getPath() {
        return path;
    }

    public void pushPath(Integer tile) { //endre Integer til Tile
>>>>>>> dbd033265bf2895de0890fc35d7fdcc7798abed6
        path.push(tile);
    }

    public void popPath() {
        path.pop();
    }

}
