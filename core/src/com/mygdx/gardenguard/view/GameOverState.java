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
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.GameOverController;
import com.mygdx.gardenguard.model.player.PlayerModel;

import java.security.Guard;
import java.util.List;


public class GameOverState extends State {

    private GameOverController controller;

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
        this.controller = new GameOverController();
        this.scores = controller.getScores();
        titleText.getData().setScale(4,4);
        scoreText.getData().setScale(3,3);
        players = controller.getSortedPlayers();
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
        titleText.draw(sb, "Game over", cam.position.x - ((float)GardenGuard.WIDTH / 4) + 20, 2 * (cam.position.y - titleText.getLineHeight()/2));
        int i = 0;
        for (String score : scores) {
            float x_value = (float) (GardenGuard.WIDTH) / 2 - 100;
            float y_value = (float) GardenGuard.HEIGHT - GardenGuard.HEIGHT/4 - scoreText.getLineHeight() * 2* i++;
            scoreText.draw(sb, score, x_value, y_value);
        }
        int j = 0;
        for (PlayerModel player: this.players) {
            float x_value = (float) (GardenGuard.WIDTH) / 2 - 230;
            //TODO: styling, blir litt rar høyde
            float y_value = (float) GardenGuard.HEIGHT - GardenGuard.HEIGHT/4 - scoreText.getLineHeight() * j++;
            sb.draw(new Texture(player.getTextureFile()), x_value, y_value, 60, 60);
            j += 1;
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
                controller.handleInput();
                return true;
            }
        });
        stage.addActor(endGame);
    }

    @Override
    public void setGameSwitch() {

    }
}
