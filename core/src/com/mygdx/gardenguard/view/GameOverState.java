package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.gardenguard.GardenGuard;

import java.util.HashMap;


public class GameOverState extends State {

    private Array<String> scores;
    private Texture background;
    private Texture endGameButton;
    private BitmapFont scoreText;
    private BitmapFont buttonText;
    private BitmapFont titleText;


    public GameOverState(GameStateManager gsm, Array<String> scores) {
        super(gsm);
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.scores = scores;
        this.background = new Texture("GameOverStateBackground.png");
        this.endGameButton = new Texture("EndGameButton.png");
        this.scoreText = new BitmapFont();
        this.buttonText = new BitmapFont();
        this.titleText = new BitmapFont();

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.draw(background, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        sb.draw(endGameButton, cam.position.x - (GardenGuard.WIDTH / 4), cam.position.y / 2, GardenGuard.WIDTH / 2, GardenGuard.WIDTH / 6);
        buttonText.draw(sb, "End game", cam.position.x - (GardenGuard.WIDTH / 4) + 25, cam.position.y / 2 + 55);
        buttonText.getData().setScale(3,3);
        titleText.draw(sb, "Game over", cam.position.x - (GardenGuard.WIDTH / 4) + 20, 2 * (cam.position.y - titleText.getLineHeight()));
        titleText.getData().setScale(3,3);
        int i = 0;
        for (String score : scores) {
            scoreText.draw(sb, score, (GardenGuard.WIDTH) / 2 - 50, GardenGuard.HEIGHT / 2 + scoreText.getLineHeight() * i++);
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
