package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.gardenguard.GardenGuard;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.awt.Color;

public class MenuState extends State implements TextInputListener {

    BitmapFont name, font;
    Texture bg = new Texture("newBg.jpg");

    private Stage stage;
    public String pin;

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
        //sb.draw(bg, 0,0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        create();
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
        Button join = new TextButton("Join game", mySkin, "small");
        Button create = new TextButton("Create game", mySkin, "small");
        join.setSize(100, 50);
        join.setPosition(170, 400);
        join.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("join");
                Gdx.input.getTextInput(getThis(), "Enter game pin", "", "");
                //pin attribute will now be updated with the typed pin
                //if for å sjekke om pin er gyldig
                //GameStateManager.getInstance().push(new LobbyState());
                return true;
            }
        });
        create.setSize(130, 50);
        create.setPosition(170, 330);
        create.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //GameStateManager.getInstance().push(new LobbyState());
                System.out.println("create");
                return true;
            }
        });
        stage.addActor(join);
        stage.addActor(create);
    }

    @Override
    public void input(String text) {
        this.pin = text;
    }

    @Override
    public void canceled() {
        pin = "cancelled";
    }

    public TextInputListener getThis() {
        return this;
    };
}
