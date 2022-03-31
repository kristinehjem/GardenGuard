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
    private Texture upButton = new Texture("upButton.png");
    private Texture downButton = new Texture("downButton.png");
    private Texture leftButton = new Texture("leftButton.png");
    private Texture rightButton = new Texture("rightButton.png");
    private Texture yellowSquare = new Texture("yellowSquare.png");

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
        sb.draw(upButton, GardenGuard.WIDTH/2-25, 120, 50, 50); // TODO: få disse til å bli alfa
        sb.draw(downButton, GardenGuard.WIDTH/2-25, 20, 50, 50);
        sb.draw(leftButton, GardenGuard.WIDTH/2-75, 70, 50, 50);
        sb.draw(rightButton, GardenGuard.WIDTH/2+25, 70, 50, 50);
        sb.draw(yellowSquare, GardenGuard.WIDTH/2-25, 70, 50, 50);
        sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), GardenGuard.WIDTH/2-25,GardenGuard.WIDTH/2-25, 50, 50);
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
