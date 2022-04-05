package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(){
        this.gsm = GameStateManager.getInstance();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        mouse = new Vector3();
    }

    public abstract Controller getController();

    protected abstract void handleInput() throws InterruptedException;
    protected abstract void update(float dt) throws InterruptedException;
    protected abstract void render(SpriteBatch sb);
    protected abstract void dispose();
    protected abstract void create();
}


