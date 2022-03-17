package com.mygdx.gardenguard.controller.gameStateControllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;

public class PlayState extends State {

    private Board board;
    private Tile tile;

    public PlayState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        handleInput();

    }

    private void drawBackground(SpriteBatch sb, Tile tile, int x, int y) {
        sb.draw(tile.getTexture(), x * tile.getWidth(), y * tile.getHeight(), 50, 60);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        //Sprite sprite = new Sprite(new Texture("green.png"));
        for (int y=0;y<15;y++) {
            for (int x=0; x<10; x++) {
                //sb.draw(board.getTileTexture(y, x), x * board.getTiles()[y][x].getWidth(), y * board.getTiles()[y][x].getHeight(), 50, 60);
                //sprite.setPosition(x * board.getTiles()[y][x].getWidth(), y * board.getTiles()[y][x].getHeight());
                drawBackground(sb, board.getTiles()[y][x], x , y );
            }}
        //board.getTiles()[0][0].getSprite().draw(sb);

    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
