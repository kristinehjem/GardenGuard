package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.model.player.PlayerModel;

import java.util.Stack;

public class GameStateManager {

    private static GameStateManager gsm = new GameStateManager();

    public static GameStateManager getInstance(){
        return gsm;
    }

    private Stack<State> states;
    private FireBaseInterface FBIC;
    private DataHolderClass dataholder;
    private String gamePin;
    private PlayerModel player;

    public GameStateManager(){
        states = new Stack<State>();
        dataholder = new DataHolderClass();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void setFBIC(FireBaseInterface FBIC) {
        this.FBIC = FBIC;
    }

    public FireBaseInterface getFBIC() {
        return FBIC;
    }

    public String getGamePin() {
        return gamePin;
    }

    public void setGamePin(String pin) {
        this.gamePin = pin;
    }

    public DataHolderClass getDataholder() {
        return dataholder;
    }

    public void update(float dt) {
        states.peek().update(dt);
    }

    public State getState() {
        return states.peek();
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
