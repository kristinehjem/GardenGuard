package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.playViews.TileView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayState extends State {

    private Board board;
    private ArrayList<PlayerModel> players;
    private ArrayList<PlayerController> controllers;
    private List<String> pngs = Arrays.asList("bulba.png", "char.png", "squirtle.png", "clafiry.png", "ditto.png");

    public PlayState(){
        super();
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
        this.player = new HiderModel("bulba", new Vector2(1, 2));
        this.player = new HiderModel("bulba", new Vector2(1, 2));
        this.player = new HiderModel("bulba", new Vector2(1, 2));
        this.player = new HiderModel("bulba", new Vector2(1, 2));
        this.player = new SeekerModel("ditto", new Vector2(1, 2));
        this.players = new ArrayList<>();
        players.add(new SeekerController((SeekerModel) this.player, this.board));
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        for (PlayerController playerCont: this.players) {
            playerCont.updatePosition();
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        for (int y=0;y<GardenGuard.numVertical;y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }}
        sb.draw(bulbaText, board.getTiles()[0][0].getWidth()*5+board.getTiles()[0][0].getWidth()/2-bulbaText.getWidth()/2, board.getTiles()[0][0].getHeight()*3+board.getTiles()[0][0].getHeight()/2-bulbaText.getHeight()/2);
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
