package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gardenguard.GardenGuard;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;

import java.awt.Color;

public class MenuState extends State implements TextInputListener {

    BitmapFont name, font;
    Texture bg = new Texture("newBg.jpg");

    private Stage stage;
    private String inputPin;
    private MenuController menuController;
    private Viewport viewport;

    public MenuState(){
        super();
        this.menuController = new MenuController();

    }

    @Override
    public Controller getController() {
        return this.menuController;
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
        sb.begin();
        //sb.draw(bg, 0,0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        /*Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        create();
        stage.act();
        stage.draw();
        sb.end();
    }

    @Override
    protected void dispose() {
        stage.dispose();
        System.out.print("Menu state disposed");
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
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
                return true;
            }
        });
        create.setSize(130, 50);
        create.setPosition(170, 330);
        create.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("create");
                menuController.handleCreate();
                //GameStateManager.getInstance().push(new LobbyState());
                return true;
            }
        });
        stage.addActor(join);
        stage.addActor(create);
    }

    @Override
    public void input(String text) {
        this.inputPin = text;
        try {
            menuController.handleJoin(inputPin);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void canceled() {
    }

    public TextInputListener getThis() {
        return this;
    };
}
