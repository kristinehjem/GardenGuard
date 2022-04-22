package com.mygdx.gardenguard.model.board;

import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.view.playViews.TileView;

public class Tile {
    private boolean walkable;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private TileView tileView;

    public Tile(int x, int y, boolean walkable) {
        this.walkable = walkable;
        width = GardenGuard.WIDTH / GardenGuard.numHorisontal;
        height = GardenGuard.HEIGHT / GardenGuard.numVertical;
        this.posX = x;
        this.posY = y;
        tileView = new TileView(this);
    }

    public boolean isWalkable() {
        return walkable;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setTileView(TileView tw){
        this.tileView = tw;
    }

    public TileView getTileView() {
        return tileView;
    }

}
