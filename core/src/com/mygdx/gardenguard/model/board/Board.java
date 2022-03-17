package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Arrays;

public class Board {
    Tile[][] tiles = new Tile[15][10];
    OrthographicCamera camera = new OrthographicCamera();
    int tileHeight = 15;
    int tileWidth = 10;
    Texture bg;

    public Board() {
        this.bg = new Texture("grass.png");
        for (int y=0;y<tileHeight;y++) {
            for (int x=0; x<tileWidth; x++) {
                //creating board without obstructions
                tiles[y][x] = new Tile(true);
                tiles[y][x].setX(x * tiles[y][x].getWidth());
                tiles[y][x].setY(y * tiles[y][x].getHeight());
            }
        }
        System.out.println(tiles[0][0]);
    };

    public Tile[][] getTiles() {
        return tiles;
    }

    public Texture getTileTexture(int y, int x){
        return tiles[y][x].getTexture();
    }

    public Texture getBg() {
        return bg;
    }
}
