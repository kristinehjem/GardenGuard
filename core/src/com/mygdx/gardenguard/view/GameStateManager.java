package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private static GameStateManager gsm = new GameStateManager();

    public static GameStateManager getInstance(){
        return gsm;
    }

    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public State getState() {
        return states.peek();
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

}
