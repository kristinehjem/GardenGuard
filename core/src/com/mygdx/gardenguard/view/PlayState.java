package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;
import com.mygdx.gardenguard.view.playViews.TileView;

public class PlayState extends State {

    private Board board;

    public PlayState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
    }

    @Override
    public Controller getController() {
        return null;
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
        for (int y=0;y<GardenGuard.numVertical;y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }}

    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
