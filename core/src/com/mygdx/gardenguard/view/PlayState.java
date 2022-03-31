package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {

    private Board board;
    private Texture upButton = new Texture("arrowButton.png"); // denne peker foreløpig skrått opp. Må snu den.

    public PlayState(){
        super();
        //cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
    }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        // Tror dette ikke er aktuelt lenger når vi ikke har kontroll på alle playersene lenger
        /*for (PlayerController player: this.players) {
            player.updatePosition();
        }*/
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        for (int y=0;y<GardenGuard.numVertical;y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.draw(upButton, 200, 50, 50, 50); // TODO: plassere denne rett. test om dette fungerer
        sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), GardenGuard.WIDTH/9*4,GardenGuard.WIDTH/15*7, 50, 50);
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        sb.end();
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
