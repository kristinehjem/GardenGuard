package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.LaunchController;

public class LaunchState extends State{

    private Sprite splash;
    private Stage stage;

    private LaunchController controller;

    private float alpha;
    private boolean animationDone;

    private static final float TIME_TO_FADE = 2f;
    private float timer = 0f;
    private boolean fadeIn;

    public LaunchState() {
        super();
        controller = new LaunchController();
        create();
    }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    public void update(float dt) {
        //Function for fading, checks if fading in is true, then if animation is done
        if(animationDone) {
            controller.handleDone();
        }
        if (fadeIn) {
            alpha = timer / TIME_TO_FADE;
            if (timer >= TIME_TO_FADE) {
                fadeIn = false;
                timer = 0;
            }
        }
        else {
            alpha = 1 - timer / TIME_TO_FADE;
            if(timer >= TIME_TO_FADE) {
                animationDone = true;
            }
        }
        timer += dt;
    }

    @Override
    public void render(SpriteBatch sb) {
    //Renders the launcing state
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        splash.setColor(1,1,1, alpha);
        splash.draw(sb);

        stage.act();
        stage.draw();

        sb.end();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }


    @Override
    protected void create() {
        fadeIn = true;
        animationDone = false;
        splash = new Sprite(new Texture("launchsplash.png"));
        Viewport viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        splash.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    @Override
    public void setTrueSwitch() {

    }

    @Override
    public void setFalseSwitch() {

    }
}
