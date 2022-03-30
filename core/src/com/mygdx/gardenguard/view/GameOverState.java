package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.GameOverController;

import java.util.List;


public class GameOverState extends State {

    private GameOverController controller;

    private final List<String> scores;
    private final Texture background;
    private final Texture endGameButton;
    private final BitmapFont scoreText;
    private final BitmapFont buttonText;
    private final BitmapFont titleText;


    public GameOverState() {
        super();
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.background = new Texture("GameOverStateBackground.png");
        this.endGameButton = new Texture("EndGameButton.png");
        this.scoreText = new BitmapFont();
        this.buttonText = new BitmapFont();
        this.titleText = new BitmapFont();
        this.controller = new GameOverController();
        this.scores = controller.getScores();

    }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    protected void handleInput() {
        controller.handleInput();
        /*if (Gdx.input.justTouched()) {
            gsm.set(new MenuState());
        }*/
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        sb.draw(endGameButton, cam.position.x - ((float) GardenGuard.WIDTH / 4), cam.position.y / 2, (float) GardenGuard.WIDTH / 2, (float) GardenGuard.WIDTH / 6);
        buttonText.draw(sb, "End game", cam.position.x - ((float)GardenGuard.WIDTH / 4) + 25, cam.position.y / 2 + 55);
        buttonText.getData().setScale(3,3);
        titleText.draw(sb, "Game over", cam.position.x - ((float)GardenGuard.WIDTH / 4) + 20, 2 * (cam.position.y - titleText.getLineHeight()));
        titleText.getData().setScale(3,3);
        int i = 0;
        for (String score : scores) {
            scoreText.draw(sb, score, (float) (GardenGuard.WIDTH) / 2 - 50, (float)GardenGuard.HEIGHT / 2 + scoreText.getLineHeight() * i++);
        }
        scoreText.getData().setScale(2,2);
    }

    @Override
    protected void dispose() {
        background.dispose();
        endGameButton.dispose();
        System.out.println("Game Over State Disposed");
    }

    @Override
    protected void create() {

    }
}
