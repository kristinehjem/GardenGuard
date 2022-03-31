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
    private Sprite upSprite = new Sprite(new Texture("upButton.png"));
    private Sprite downSprite = new Sprite(new Texture("downButton.png"));
    private Sprite leftSprite = new Sprite(new Texture("leftButton.png"));
    private Sprite rightSprite = new Sprite(new Texture("rightButton.png"));
    private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint=new Vector3();

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
        // Hos meg (Ingrid) må man trykke under knappene for å treffe om man jeg er i emulator.
        // I desktop går det fint. Tror det handler om at mobilen jeg bruker har lengre skjerm enn
        // spillet, så skjerm-koordinatene er ikke de samme som spill-koordinatene
        if(Gdx.input.justTouched()) {
            //unprojects the camera: (vet ikke hva det vil si, men klikkingen fungerer ikke uten det)
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(rightSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                System.out.println("XXXXXXX Høyre XXXXXXX");
            } if(upSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                System.out.println("XXXXXXX Opp XXXXXXX");
            } if(downSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                System.out.println("XXXXXXX Ned XXXXXXX");
            } if(leftSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                System.out.println("XXXXXXX Venstre XXXXXXX");
            }
        }
    }

    @Override
    protected void update(float dt) { // TODO: Jeg bruker ikke dt til noe. Skal jeg det?
        handleInput();
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

    @Override
    protected void dispose() {
        // Måtte fjerne disse da jeg heller lagde textursene inni spritene, siden da fantes ikke
        // texturesene som varibaler lengre. Men som taxturwe lages new inni spriten, må vi
        // fortsatt dispose da?
        /*upTexture.dispose();
        downTexture.dispose();
        leftTexture.dispose();
        rightTexture.dispose();*/
    }

    @Override
    protected void create() {
        // Hva er tanken med denne klassen?
    }
}
