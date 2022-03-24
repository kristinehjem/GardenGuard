package com.mygdx.gardenguard.view.playViews;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class playerBulbaView {
    Texture bulbaTexture = new Texture("bulba.png");

    public Texture getTexture(){
        return this.bulbaTexture;
    }

    public void drawTile(SpriteBatch sb, int x, int y){
        sb.draw(getTexture(), 200, 100);
    }
}