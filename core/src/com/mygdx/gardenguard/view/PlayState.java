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
    private PlayerModel player;
    private List<PlayerController> players; // TODO: Fikse dette med antall models og controllers. Beate sa at vi bare trengte én playerController siden hver person bare skal ha styr på seg selv. Så får vi heller hente posisjonen til de andre spillerne (for rendring) fra databasen

    public PlayState(){
        super();
        //cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
        this.player = new SeekerModel(new Vector2(1, 2));
        this.players = new ArrayList<>();
        players.add(new SeekerController((SeekerModel) this.player, this.board));
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
        for (PlayerController player: this.players) {
            player.updatePosition();
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(upButton, 200, 50); // TODO: plassere denne rett. Koden kjører ikke atm, så får ikke testet om dette fungerer
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        for (int y=0;y<GardenGuard.numVertical;y++) {
            for (int x=0; x<GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.end();
    }

    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }
}
