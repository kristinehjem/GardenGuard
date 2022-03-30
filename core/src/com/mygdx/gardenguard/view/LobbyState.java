package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.LobbyController;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LobbyState extends State{

    LobbyController controller;
    private Stage stage;
    Sprite sprite;
    Texture backround = new Texture("lobbyBackround.png");
    Collection<String> playerNames;
    private BitmapFont playerFont = new BitmapFont();
    private BitmapFont nameFont = new BitmapFont();
    private Viewport viewport;

    public LobbyState(){
        super();
        controller = new LobbyController();
        sprite = new Sprite(backround);
        this.playerNames = new ArrayList<>();
        playerFont.getData().setScale(3f);
        nameFont.getData().setScale(2.5f);
    }

    @Override
    public Controller getController() {
        if (this.controller == null) {
            System.out.println("Controller is null");
            return null;
        } else {
            return this.controller;
        }
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
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        sb.draw(backround, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        create();
        List<PlayerModel> players = controller.getPlayers();
        playerFont.draw(sb, "Players", 180, 600);
        int i = 0;
        for (PlayerModel player: players) {
            int x_value = (int) (100 + (i*30*Math.pow(-1, i)));
            int y_value = 500-(i*80);
            nameFont.draw(sb, player.getPlayerID(),x_value, y_value);
            i += 1;
        }
        stage.act();
        stage.draw();
        sb.end();
    }

    @Override
    protected void dispose() {
        stage.clear();
        System.out.print("Lobby state disposed");
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button startGame = new TextButton("Start game", mySkin, "small");
        startGame.setSize(100, 50);
        startGame.setPosition(170, 300);
        startGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleStart();
                System.out.println("start clicked");
                return true;
            }
        });
        if (super.gsm.getPlayer() instanceof HiderModel) {
            startGame.setVisible(false);
        }
        stage.addActor(startGame);
    }


}
