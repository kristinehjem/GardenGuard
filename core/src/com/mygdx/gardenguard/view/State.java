package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GameStateManager;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;

public abstract class State {

    protected OrthographicCamera cam;
    protected GameStateManager gsm;

    protected State(){
        this.gsm = GameStateManager.getInstance();
        cam = new OrthographicCamera();
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
    }

    public abstract Controller getController();

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    protected abstract void dispose();
    protected abstract void create();
    public abstract void setTrueSwitch();
    public abstract void setFalseSwitch();
}


