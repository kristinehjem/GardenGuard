package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    private Texture upTexture = new Texture("upButton.png");
    private Texture downTexture = new Texture("downButton.png");
    private Texture leftTexture = new Texture("leftButton.png");
    private Texture rightTexture = new Texture("rightButton.png");
    private Texture yellowSquare = new Texture("yellowSquare.png");
    private Sprite upSprite = new Sprite(upTexture);
    private Sprite downSprite = new Sprite(downTexture);
    private Sprite leftSprite = new Sprite(leftTexture);
    private Sprite rightSprite = new Sprite(rightTexture);
    private Sprite squareSprite = new Sprite(yellowSquare);
    private Vector3 temp; // litt usikker på hva temå står for (tatt fra nett), men det brukes lengre nede

    public PlayState(){
        super();
        //cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
        this.board = new Board();
        upSprite.setPosition(GardenGuard.WIDTH/2-25,150);
        downSprite.setPosition(GardenGuard.WIDTH/2-25,50);
        leftSprite.setPosition(GardenGuard.WIDTH/2-75,100);
        rightSprite.setPosition(GardenGuard.WIDTH/2+25,100);
        squareSprite.setPosition(GardenGuard.WIDTH/2-25, 100);
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

        upSprite.draw(sb, 50);
        downSprite.draw(sb, 50);
        leftSprite.draw(sb, 50);
        rightSprite.draw(sb, 50);
        squareSprite.draw(sb, 50);
        sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), GardenGuard.WIDTH/2-25,GardenGuard.HEIGHT/2-25, 50, 50);
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        sb.end();
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    // TODO: Hva er screenX og screenY? Musepososjonen? Hvordan sender jeg inn den?
        temp.set(screenX,screenY,0);
        // camera.unproject(temp); //vet ikke hva denne gjør. Tatt fra nett.

        if(upSprite.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on upSprite");
        if(downSprite.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on downSprite");
        if(leftSprite.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on leftSprite");
        if(rightSprite.getBoundingRectangle().contains(temp.x,temp.y))
            System.out.println("Touch on rightSprite");

        return false;
    }

    @Override
    protected void dispose() {
        upTexture.dispose();
        downTexture.dispose();
        leftTexture.dispose();
        rightTexture.dispose();
    }

    @Override
    protected void create() {
        // Hva er tanken med denne klassen?
    }
}
