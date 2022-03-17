package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;

public class Tile {
    boolean walkable;
    Texture texture;
    Sprite tile;
    int width;
    int height;

    public Tile(boolean walkable) {
        this.walkable = walkable;
        this.texture = new Texture("green.png");
        width = (int) GardenGuard.WIDTH / 10;
        height = (int) GardenGuard.HEIGHT / 15;
        this.tile = new Sprite(texture, width, height);
    }
    public Texture getTexture() {
        return texture;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setY(float y) {
        tile.setY(y);
    }
    public float getY() {
        return tile.getY();
    }

    public void setX(float x) {
        tile.setX(x);
    }
    public float getX() {
        return tile.getX();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Sprite getSprite() {
        return tile;
    }
}
