package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.LobbyController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LobbyState extends State{

    LobbyController controller;

    Sprite sprite;
    Texture backround = new Texture("lobbyBackround.png");
    Collection<String> playerNames;
    private BitmapFont playerFont = new BitmapFont();
    private BitmapFont nameFont = new BitmapFont();

    public LobbyState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        controller = new LobbyController(super.gsm);
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
        sb.draw(backround, 0, 0, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        List<Player> players = controller.getPlayers();
        playerFont.draw(sb, "Players", 180, 600);
        int i = 0;
        for (Player player: players) {
            int x_value = (int) (120 + (i*30*Math.pow(-1, i)));
            int y_value = 500-(i*80);
            nameFont.draw(sb, player.getName(),x_value, y_value);
            i += 1;
        }
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }


}
