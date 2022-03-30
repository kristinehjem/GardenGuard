package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;

import java.util.Arrays;
import java.util.Random;

public class Board {
    Tile[][] tiles = new Tile[15][9];

    public Board() {
        for (int y = 0; y< GardenGuard.numVertical; y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                //creating board without obstructions
                if((y==7) && (x==4)){
                    tiles[y][x] = new Tile(x, y,true);
                }
                else {
                    Random random = new Random();
                    tiles[y][x] = new Tile(x, y,getRandomBoolean(random));
                }
                tiles[y][x].setPosX(x * tiles[y][x].getWidth());
                tiles[y][x].setPosY(y * tiles[y][x].getHeight());
            }
        }
    };

    public boolean getRandomBoolean(Random random){
        return random.nextFloat() < 0.75;
    }


    public Tile[][] getTiles() {
        return tiles;
    }

}
