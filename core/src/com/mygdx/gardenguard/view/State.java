package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.controller.stateControllers.Controller;

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(){
        this.gsm = GameStateManager.getInstance();
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract Controller getController();

    protected abstract void handleInput();
    protected abstract void update(float dt);
    protected abstract void render(SpriteBatch sb);
    protected abstract void dispose();
    protected abstract void create();
}


