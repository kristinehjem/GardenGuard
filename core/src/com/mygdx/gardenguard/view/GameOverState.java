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
import com.mygdx.gardenguard.model.player.PlayerModel;

import java.util.List;


public class GameOverState extends State {

    private GameOverController gameOverController;

    private final List<String> scores;
    private final Texture background;
    private final BitmapFont scoreText;
    private final BitmapFont titleText;
    private Stage stage;
    private Viewport viewport;
    private List<PlayerModel> players;


    public GameOverState() {
        super();
        this.background = new Texture("GameOverStateBackground.png");
        this.scoreText = new BitmapFont();
        this.titleText = new BitmapFont();
        this.gameOverController = new GameOverController();
        this.scores = gameOverController.getScores();
        titleText.getData().setScale(4,4);
        scoreText.getData().setScale(3,3);
        players = gameOverController.getSortedPlayers();
        create();
    }

    @Override
    public Controller getController() {
        if (gameOverController == null) {
            System.err.println("Controller is null");
            return null;
        }
        return gameOverController;
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        titleText.draw(sb, "Game over", (GardenGuard.WIDTH - 260)/2, 2 * (cam.position.y - titleText.getLineHeight()/2));
        int i = 0;
        for (String score : scores) {
            float x_value = (float) (GardenGuard.WIDTH) / 2 - 150;
            float y_value = (float) GardenGuard.HEIGHT - GardenGuard.HEIGHT/4 - scoreText.getLineHeight() * i;
            scoreText.draw(sb, score, x_value, y_value);
            i++;
        }
        int j = 0;
        for (PlayerModel player: this.players) {
            float x_value = (float) (GardenGuard.WIDTH) / 2 - 230;
            //TODO: styling, blir litt rar høyde
            float y_value = (float) GardenGuard.HEIGHT - GardenGuard.HEIGHT/4 - scoreText.getLineHeight() * j-50;
            sb.draw(new Texture(player.getTextureFile()), x_value, y_value, 60, 60);
            j++;
        }
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
                gameOverController.handleInput();
                return true;
            }
        });
        stage.addActor(endGame);
    }

    @Override
    public void setTrueSwitch() {

    }

    @Override
    public void setFalseSwitch() {

    }
}
