package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;

public class MenuState extends State {

    private Stage stage;
    private String inputPin;
    private MenuController menuController;
    private Viewport viewport;
    private BitmapFont info = new BitmapFont();
    private BitmapFont header = new BitmapFont();

    public MenuState(){
        super();
        this.menuController = new MenuController();
        info.getData().setScale(3f);
        header.getData().setScale(4f);
        create();
    }

    @Override
    public Controller getController() {
        return this.menuController;
    }


    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        header.draw(sb, "Garden Guard",GardenGuard.WIDTH/10, GardenGuard.HEIGHT-100);
        stage.act();
        stage.draw();
        sb.end();
    }

    @Override
    protected void dispose() {
        stage.dispose();
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button join = new TextButton("Join game", mySkin, "small");
        Button create = new TextButton("Create game", mySkin, "small");
        Button tutorial = new TextButton("Tutorial", mySkin, "small");
        join.setSize(GardenGuard.WIDTH/3, GardenGuard.HEIGHT/12);
        join.setPosition((GardenGuard.WIDTH-GardenGuard.WIDTH/3)/2, GardenGuard.HEIGHT/2);
        join.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("join");
                Gdx.input.getTextInput(new TextInputListener() {
                    @Override
                    public void input(String text) {
                        inputPin = text;
                        try {
                            menuController.handleJoin(inputPin);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void canceled() {
                    }
                }, "Enter game pin", "", "");
                return true;
            }

        });
        create.setSize(GardenGuard.WIDTH/2, GardenGuard.HEIGHT/12);
        create.setPosition((GardenGuard.WIDTH-GardenGuard.WIDTH/2)/2, GardenGuard.HEIGHT/3);
        create.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    menuController.handleCreate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        tutorial.setSize(GardenGuard.WIDTH/2, GardenGuard.HEIGHT/12);
        tutorial.setPosition((GardenGuard.WIDTH-GardenGuard.WIDTH/2)/2, GardenGuard.HEIGHT/6);
        tutorial.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                menuController.handleTutorial();
                return true;
            }
        });
        stage.addActor(join);
        stage.addActor(create);
        stage.addActor(tutorial);
    }

    @Override
    public void setTrueSwitch(){

    }

    @Override
    public void setFalseSwitch() {

    }


}
