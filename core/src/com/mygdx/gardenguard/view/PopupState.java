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
    private Texture bg = new Texture("oransje.jpg");

    private String text;
    private BitmapFont font;
    private PopupController popupController;
    private Viewport viewport;
    private Texture picture;

    public PopupState(String text){
        super();
        this.text = text;
        this.popupController = new PopupController();
        create();
    }
    public PopupState(Texture texture) {
        picture = texture;
        this.popupController = new PopupController();
        create();
    }

    @Override
    public Controller getController() {
        if (popupController == null) {
            System.err.println("Controller is null");
            return null;
        }
        return popupController;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        if (picture != null) {
            sb.draw(picture, 15, 60, 450, 720);
        }
        if (text != null) {
            sb.draw(bg, 30, 170, GardenGuard.WIDTH-60, GardenGuard.HEIGHT-340);
            font = new BitmapFont();
            font.getData().setScale(2f);
            font.draw(sb, text, 100, 600);
        }
        stage.act();
        stage.draw();
        sb.end();

    }

    @Override
    protected void dispose() {
        if (text != null) {
            bg.dispose();
            font.dispose();
        }
        if (picture != null) {
            picture.dispose();
        }
        stage.dispose();
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button close = new TextButton("Close", mySkin, "small");
        close.setSize(100, 50);
        if (picture != null) {
            close.setPosition(190, 10);
        }
        else {
            close.setPosition(190, 180);
        }
        close.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("closed popup window");
                popupController.handleClose();
                return true;
            }
        });
        stage.addActor(close);
    }

    @Override
    public void setTrueSwitch() {

    }

    @Override
    public void setFalseSwitch() {

    }
}
