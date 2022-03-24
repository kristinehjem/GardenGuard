package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;

public class MenuState extends State{

    public MenuState(){
        super();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
