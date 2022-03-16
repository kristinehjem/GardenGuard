package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Board {
    Tile[][] tiles;
    OrthographicCamera camera = new OrthographicCamera();
    int tileHeight = 15;
    int tileWidth = 10;

    public Board() {
        for (int y=0;y<tileHeight;y++) {
            for (int x=0; x<tileWidth; x++) {
                //creating board without obstructions
                tiles[y][x] = new Tile(true);
            }
        }
    };
    public void render(SpriteBatch sb) {
        sb.begin();
        for (int y = 0; y < tileHeight; y++) {
            for (int x = 0; x < tileWidth; x++) {
                Tile tile = tiles[y][x];
                tile.setX(x * tile.getWidth());
                tile.setY(y * tile.getHeight());
                tile.render(sb, tile.getX(), tile.getY());
                //sb.draw(tile.getTexture(), tile.getWidth(), tile.getHeight());
            }
        }
        sb.end();
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
