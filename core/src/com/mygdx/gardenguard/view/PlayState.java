package com.mygdx.gardenguard.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.playViews.TileView;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {

    private Board board;
    private PlayerModel player;
    private ArrayList<PlayerController> players;

    public PlayState(){
        super();
        cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
        this.player = new SeekerModel("HEI", new Vector2(1, 2));
        this.players = new ArrayList<>();
        players.add(new SeekerController((SeekerModel) this.player, this.board));
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        for (PlayerController spillere: this.players) {
            spillere.updatePosition();
        }


    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        for (int y=0;y<GardenGuard.numVertical;y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }}

    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
