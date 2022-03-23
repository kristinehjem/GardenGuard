package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.view.playViews.TileView;

public class Tile {
    boolean walkable;
    int width;
    int height;
    int posX;
    int posY;
    private TileView tileView;

    public Tile(int x, int y, boolean walkable) {
        this.walkable = walkable;
        width = (int) GardenGuard.WIDTH / GardenGuard.numHorisontal;
        height = (int) GardenGuard.HEIGHT / GardenGuard.numVertical;
        this.posX = x;
        this.posY = y;
        tileView = new TileView(this);
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
