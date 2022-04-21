package com.mygdx.gardenguard.model.board;

import com.mygdx.gardenguard.GardenGuard;

import java.util.Random;

public class Board {
    public static final int numTilesWidth = 9;
    public static final int numTilesHeight = 15;
    Tile[][] tiles = new Tile[numTilesHeight][numTilesWidth];
    Boolean[][] walkable = new Boolean[numTilesHeight][numTilesWidth];

    public Board(int boardNr) {
        if (boardNr == 0) {
            setWalkableVersionOne();
        } else if (boardNr == 1) {
            setWalkableVersionTwo();
        } else if (boardNr == 2) {
            setWalkableVersionThree();
        }
        for (int y = 0; y< GardenGuard.numVertical; y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                tiles[y][x] = new Tile(x, y, walkable[y][x]);
                /*
                // Hedge top for info
                if(y==14){
                    tiles[y][x] = new Tile(x,y, false);
                    walkable[y][x] = false;
                    continue;
                }
                // Clean center
                if((y==6 && x==3) || (y==7 && x==3) || (y==8 && x==3) || (y==6 && x==4) || (y==7 && x==4) || (y==8 && x==4) || (y==6 && x==5) || (y==7 && x==5) || (y==8 && x==5)){ // Note: Tallene til board/tiles går 0-8 og 0-14
                    tiles[y][x] = new Tile(x, y,true);
                    walkable[y][x] = true;
                    continue;
                }
                // Hedges for up, down, right, left button
                if((y==1 && x==4) || (y==2 && (x==3 || x==4 || x==5)) || (y==3 && x==4)){
                    tiles[y][x] = new Tile(x,y, false);
                    walkable[y][x] = true;
                    continue;
                }
                else {
                    Random random = new Random();
                    boolean bol = getRandomBoolean(random);
                    tiles[y][x] = new Tile(x, y, bol);
                    walkable[y][x] = bol;
                }
                tiles[y][x].setPosX(x * tiles[y][x].getWidth());
                tiles[y][x].setPosY(y * tiles[y][x].getHeight());*/
            }
        }
    }

    public void setWalkableVersionOne(){
        walkable = new Boolean[][]{
                {true, true, true, true, true, true, true, true, true}, //y = 0
                {true, false, true, false, false, true, true, false, true}, // y = 1
                {true, false, true, false, true, false, true, false, true}, // y = 2
                {true, false, true, true, false, false, true, false, false}, // y = 3
                {true, true, true, true, true, true, true, true, true}, // y = 4
                {true, true, false, false, false, true, true, true, true}, // y = 5
                {false, true, true, true, true, true, false, true, false}, // y = 6
                {false, true, false, true, true, true, true, false, false, false}, // y = 7
                {false, true, true, true, true, true, true, true, true}, // y = 8
                {false, false, true, false, true, true, true, true, true}, // y = 9
                {true, true, true, false, true, true, true, false, true}, // y = 10
                {true, true, true, false, true, false, false, false, true}, // y = 11
                {false, false, false, false, true, true, true, false, true}, // y = 12
                {true, true, true, true, true, true, true, false, false}, // y = 13
                {false, false, false, false, false, false, false, false, false} // y = 14
        };
    }

    public void setWalkableVersionTwo(){
        walkable = new Boolean[][]{
                {true, true, true, true, true, true, true, true, true}, //y = 0 - bottom of board
                {true, true, true, false, false, true, true, false, true}, // y = 1 - buttons
                {true, true, true, false, true, false, true, false, true}, // y = 2 - buttons
                {true, true, true, true, false, false, true, false, true}, // y = 3 - buttons
                {true, true, false, false, false, true, false, false, true}, // y = 4
                {true, true, false, true, true, true, true, true, true}, // y = 5
                {true, false, false, true, true, true, false, true, false}, // y = 6 - player start position
                {true, false, false, true, true, true, true, false, false, false}, // y = 7 - player start position
                {true, true, true, true, true, true, true, false, false}, // y = 8 - player start position
                {true, false, true, true, true, true, true, true, true}, // y = 9
                {false, false, true, true, true, true, true, true, true}, // y = 10
                {true, false, true, false, false, true, false, false, false}, // y = 11
                {true, false, false, true, true, false, true, true, true}, // y = 12
                {true, false, true, true, true, true, true, true, true}, // y = 13
                {false, false, false, false, false, false, false, false, false} // y = 14 - top of board
        };

    }

    public void setWalkableVersionThree(){
        walkable = new Boolean[][]{
                {false, false, true, true, true, true, true, true, true}, //y = 0 - bottom of board
                {true, false, true, false, false, true, true, false, true}, // y = 1 - buttons
                {true, false, true, false, true, false, true, false, true}, // y = 2 - buttons
                {true, false, true, true, false, false, true, true, true}, // y = 3 - buttons
                {false, false, true, true, true, true, true, true, true}, // y = 4
                {true, true, true, true, true, true, true, true, true}, // y = 5
                {true, false, true, true, true, true, false, true, false}, // y = 6 - player start position
                {true, false, false, true, true, true, true, false, false, false}, // y = 7 - player start position
                {true, true, true, true, true, true, true, false, true}, // y = 8 - player start position
                {true, false, true, true, true, true, true, false, true}, // y = 9
                {true, false, false, false, true, true, true, true, true}, // y = 10
                {true, true, true, false, false, true, false, false, false}, // y = 11
                {true, true, false, false, true, true, false, true, true}, // y = 12
                {true, true, true, true, true, true, true, true, true}, // y = 13
                {false, false, false, false, false, false, false, false, false} // y = 14 - top of board
        };

    }

    public boolean getRandomBoolean(Random random){
        return random.nextFloat() < 0.75;
    }


    public Tile[][] getTiles() {
        return tiles;
    }
}
