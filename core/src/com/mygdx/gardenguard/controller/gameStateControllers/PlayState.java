package com.mygdx.gardenguard.controller.gameStateControllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;

public class PlayState extends State {

    private Board board;
    private Tile tile;

    public PlayState(GameStateManager gsm){
        super(gsm);
        this.board = new Board();
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
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        board.getTiles()[0][0].getSprite().draw(sb);
        sb.end();

    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
