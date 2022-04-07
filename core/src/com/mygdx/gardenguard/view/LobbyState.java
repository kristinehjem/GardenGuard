package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
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

    private LobbyController controller;
    private Stage stage;
    Sprite sprite;
    Texture backround = new Texture("lobbyBackround.png");
    Collection<String> playerNames;
    private BitmapFont playerFont = new BitmapFont();
    private BitmapFont nameFont = new BitmapFont();
    private BitmapFont pinFont = new BitmapFont();
    private BitmapFont pinInfo = new BitmapFont();
    private Viewport viewport;
    private boolean gameSwitch;

    public LobbyState(){
        super();
        controller = new LobbyController();
        sprite = new Sprite(backround);
        this.playerNames = new ArrayList<>();
        this.gameSwitch = false;
        playerFont.getData().setScale(3f);
        nameFont.getData().setScale(2.5f);
        pinFont.getData().setScale(2f);
        pinInfo.getData().setScale(1.5f);
        create();
    }

    @Override
    public Controller getController() {
        if (this.controller == null) {
            //TODO fix error handling (is this enough?)
            System.err.println("Controller is null");
            return null;
        } else {
            System.out.println("controller is not null");
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
        sb.draw(backround, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        final GlyphLayout layout1 = new GlyphLayout(pinFont, controller.getPin());
        final GlyphLayout layout2 = new GlyphLayout(pinInfo, "Share game pin with your friends");
        pinFont.draw(sb, layout1, (GardenGuard.WIDTH-layout1.width)/2, 740);
        pinInfo.draw(sb, layout2, (GardenGuard.WIDTH-layout2.width)/2, 700);
        List<PlayerModel> players = controller.getPlayers();
        playerFont.draw(sb, "Players", 180, 600);
        int i = 0;
        for (PlayerModel player: players) {
            int x_value = (80);
            int y_value = 500-(i*80);
            nameFont.draw(sb, player.getUsername(),x_value, y_value);
            sb.draw(new Texture(player.getTextureFile()), 10, y_value-40, 50, 50);
            i += 1;
        }
        stage.act();
        stage.draw();
        sb.end();
        if (gameSwitch == true) {
            this.controller.pushNewState();
        }
    }

    @Override
    protected void dispose() {
        stage.dispose();
        backround.dispose();
    }

    @Override
    protected void create() {
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button exit = new TextButton("Exit", mySkin, "small");
        exit.setSize(GardenGuard.WIDTH/8, GardenGuard.HEIGHT/20);
        exit.setPosition(GardenGuard.WIDTH/20, GardenGuard.HEIGHT - 50);
        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleExit();
                return true;
            }
        });
        Button startGame = new TextButton("Start game", mySkin, "small");
        startGame.setSize(GardenGuard.WIDTH/4, GardenGuard.HEIGHT/12);
        startGame.setPosition((GardenGuard.WIDTH-GardenGuard.WIDTH/4)/2, GardenGuard.HEIGHT/8);
        startGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    controller.handleStart();
                    System.out.println("start clicked");
                    return true;
                } catch (Exception e){
                    System.out.println("not enough players");
                    //put in pop up window
                    e.printStackTrace();
                    return true;
                }
            };
        });
        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) { controller.setUsername(text);
            }

            @Override
            public void canceled() {
                controller.setUsername(controller.getPlayer().getPlayerID());
            }
        }, "Enter username", "", "");
        //TODO: ukommenter dette
        /*if (super.gsm.getPlayer() instanceof HiderModel) {
            startGame.setVisible(false);
        }*/
        stage.addActor(startGame);
        stage.addActor(exit);
    }

    @Override
    public void setGameSwitch() {
        this.gameSwitch = true;
    }

    @Override
    public void setFalseSwitch() {

    }
}
