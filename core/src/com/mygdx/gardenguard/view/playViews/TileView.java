package com.mygdx.gardenguard.view.playViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Tile;

public class TileView {

    Sprite sprite;
    Tile tile;
    Texture grass = new Texture("green2.png");
    Texture hedge = new Texture("newBg.jpg");

    public TileView(Tile tile){
        this.tile = tile;
        setPicture(tile);
        tile.setTileView(this);
    }

    public void setPicture(Tile tile){
        if (tile.isWalkable()){
            sprite = new Sprite(grass, tile.getPosX(), tile.getPosY(), tile.getWidth(), tile.getHeight());
        }
        else {
            sprite = new Sprite(hedge, tile.getPosX(), tile.getPosY(), tile.getWidth(), tile.getHeight());
        }
    }

    public Texture getTexture(){
        return sprite.getTexture();
    }

    public void drawTile(SpriteBatch sb, int x, int y){
        sb.draw(getTexture(), x*tile.getWidth(), y*tile.getHeight());

    }

}
