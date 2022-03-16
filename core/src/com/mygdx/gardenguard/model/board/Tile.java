package com.mygdx.gardenguard.model.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    boolean walkable;
    Texture texture;
    Sprite tile;
    OrthographicCamera camera = new OrthographicCamera();
    int width;
    int height;

    public Tile(boolean walkable) {
        this.walkable = walkable;
        this.texture = new Texture("grass.png");
        width = (int)camera.viewportWidth / 10;
        height = (int)camera.viewportHeight / 15;
        this.tile = new Sprite(texture, width, height);
    }
    public Texture getTexture() {
        return texture;
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
