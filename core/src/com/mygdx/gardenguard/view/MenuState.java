package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.gardenguard.GardenGuard;

import java.awt.Color;

public class MenuState extends State{

    BitmapFont name, font;
    Texture bg = new Texture("newBg.jpg");

    Stage stage;


    public MenuState(){
        super();

    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        create();
        sb.draw(bg, 0,0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        //name.draw(sb, "Garden Guard", 160, 150);
        stage.act();
        stage.draw();
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
    }
}
