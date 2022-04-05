package com.mygdx.gardenguard.model.board;

import com.mygdx.gardenguard.GardenGuard;

import java.util.Random;

public class Board {
    public static final int numTilesWidth = 9;
    public static final int numTilesHeight = 15;
    Tile[][] tiles = new Tile[numTilesHeight][numTilesWidth];

    public Board() {
        for (int y = 0; y< GardenGuard.numVertical; y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                // Hedge top for info
                if(y==14){
                    tiles[y][x] = new Tile(x,y, false);
                    continue;
                }
                // Clean center
                if((y==6 && x==3) || (y==7 && x==3) || (y==8 && x==3) || (y==6 && x==4) || (y==7 && x==4) || (y==8 && x==4) || (y==6 && x==5) || (y==7 && x==5) || (y==8 && x==5)){ // Note: Tallene til board/tiles går 0-8 og 0-14
                    tiles[y][x] = new Tile(x, y,true);
                    continue;
                }
                // Hedges for up, down, right, left button
                if((y==1 && x==4) || (y==2 && (x==3 || x==4 || x==5)) || (y==3 && x==4)){
                    tiles[y][x] = new Tile(x,y, false);
                    continue;
                }
                else {
                    Random random = new Random();
                    tiles[y][x] = new Tile(x, y,getRandomBoolean(random));
                }
                tiles[y][x].setPosX(x * tiles[y][x].getWidth());
                tiles[y][x].setPosY(y * tiles[y][x].getHeight());
            }
        }
    }

    public boolean getRandomBoolean(Random random){
        return random.nextFloat() < 0.75;
    }


    public Tile[][] getTiles() {
        return tiles;
    }
}
