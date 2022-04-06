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
import com.mygdx.gardenguard.controller.stateControllers.GameOverController;

import java.util.List;


public class GameOverState extends State {

    private GameOverController controller;

    private final List<String> scores;
    private final Texture background;
    private final BitmapFont scoreText;
    private final BitmapFont titleText;
    private Stage stage;
    private Viewport viewport;


    public GameOverState() {
        super();
        this.background = new Texture("GameOverStateBackground.png");
        this.scoreText = new BitmapFont();
        this.titleText = new BitmapFont();
        this.controller = new GameOverController();
        this.scores = controller.getScores();
    }

    @Override
    public Controller getController() {
        return controller;
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
        sb.draw(background, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        create();
        titleText.draw(sb, "Game over", cam.position.x - ((float)GardenGuard.WIDTH / 4) + 20, 2 * (cam.position.y - titleText.getLineHeight()));
        titleText.getData().setScale(3,3);
        int i = 0;
        for (String score : scores) {
            scoreText.draw(sb, score, (float) (GardenGuard.WIDTH) / 2 - 50, (float)GardenGuard.HEIGHT / 2 + scoreText.getLineHeight() * i++);
        }
        scoreText.getData().setScale(2,2);
        stage.act();
        stage.draw();
        sb.end();
    }

    @Override
    protected void dispose() {
        background.dispose();
        stage.dispose();
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button endGame = new TextButton("End game", mySkin, "small");
        endGame.setSize(GardenGuard.WIDTH/4, GardenGuard.HEIGHT/12);
        endGame.setPosition(GardenGuard.WIDTH/2 - 55, GardenGuard.HEIGHT/8);
        endGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleInput();
                return true;
            }
        });
        stage.addActor(endGame);
    }

    @Override
    public void gameSwitchTrue() {

    }
}
