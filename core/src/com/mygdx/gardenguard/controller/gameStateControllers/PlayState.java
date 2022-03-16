package com.mygdx.gardenguard.controller.gameStateControllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State {

    public PlayState(GameStateManager gsm){
        super(gsm);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();

    }

    @Override
    protected void render(SpriteBatch sb) {

    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
