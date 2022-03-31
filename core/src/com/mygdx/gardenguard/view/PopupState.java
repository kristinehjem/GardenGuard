package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import com.mygdx.gardenguard.controller.stateControllers.PopupController;

public class PopupState extends State{

    private Stage stage;
    Texture bg = new Texture("oransje.jpg");

    private String text;
    BitmapFont font;
    private PopupController popupController;
    private Viewport viewport;

    public PopupState(){
        super();
        this.text = "POPUP WINDOW \n hente noe tekst her";
        this.popupController = new PopupController();
    }

    @Override
    public Controller getController() {
        return this.popupController;
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
        create();
        stage.act();
        stage.draw();
        font.draw(sb, text, 150, 700);
        sb.end();

    }

    @Override
    protected void dispose() {
        bg.dispose();
        stage.dispose();
        font.dispose();

    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        stage.getBatch().begin();
        stage.getBatch().draw(bg, 75, 75, GardenGuard.WIDTH - 150, GardenGuard.HEIGHT - 150);
        stage.getBatch().end();
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button close = new TextButton("Close", mySkin, "small");
        close.setSize(100, 50);
        close.setPosition(190, 100);
        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("closed popup window");
                popupController.handleClose();
                return true;
            }
        });
        stage.addActor(close);
        font = new BitmapFont();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
