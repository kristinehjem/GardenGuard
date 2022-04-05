package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.playerControllers.HiderController;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {

    // TODO: Når spilleren opprettes må posisjonen dens settes til rett sted (midten for ditto, og rundt midten for alle andre)

    private Board board;

    private int tileWidth = 53; // TODO: Dette burde sikkert implementeres i Tile-klassen. Og: Det hadde vært mye lettere om tilsene var like høy som brede (dvs. at bakgrunnens horisontale streker var like tynne spm de vertikale)
    private int tileHeight = 53; // TODO: Prøv å bruk den nye referansen jeg lagde i board-klassen
    private Sprite upSprite = new Sprite(new Texture("upButton.png"));
    private Sprite downSprite = new Sprite(new Texture("downButton.png"));
    private Sprite leftSprite = new Sprite(new Texture("leftButton.png"));
    private Sprite rightSprite = new Sprite(new Texture("rightButton.png"));
    private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint=new Vector3();
    private PlayerController playerController;
    //Dummy test to find other players;
    private PlayerModel hider;
    private Rectangle vision;

    // For rendering the seeker view
    private Texture light;
    private Sprite lightSprite;

    public PlayState() {
        super();
        this.board = new Board();
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            this.playerController = new SeekerController((SeekerModel) super.gsm.getPlayer(), board);
        } else if(super.gsm.getPlayer() instanceof HiderModel) {
            this.playerController = new HiderController((HiderModel) super.gsm.getPlayer(), board);
        } else {
            System.out.print("Player is neither instance of SeekerModel nor HiderModel");
        }
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1, 2, 2);
        //DUMMY HIDER FOR TESTING
        //this.hider = new HiderModel(new Vector2(8,8));
        //BUTTONS
        upSprite.setSize(tileWidth,tileHeight);
        downSprite.setSize(tileWidth,tileHeight);
        leftSprite.setSize(tileWidth,tileHeight);
        rightSprite.setSize(tileWidth,tileHeight);
        squareSprite.setSize(tileWidth,tileHeight);
        upSprite.setPosition(GardenGuard.WIDTH/2-tileWidth/2-1,tileHeight*3);
        downSprite.setPosition(GardenGuard.WIDTH/2-tileWidth/2-1,tileHeight);
        leftSprite.setPosition(GardenGuard.WIDTH/2-tileWidth-tileWidth/2-1,tileHeight*2-1);
        rightSprite.setPosition(GardenGuard.WIDTH/2+tileWidth/2-1,tileHeight*2);
        squareSprite.setPosition(GardenGuard.WIDTH/2-tileWidth/2-1, tileHeight*2);

        /*OLD CODE: CAN BE USED WHEN MOVING MOVEMENT TO CONTROLLER
        this.player = new SeekerModel(new Vector2(1, 2));
        this.controller = new SeekerController((SeekerModel) this.player, this.board);*/
    }

    @Override
    public Controller getController() {
        return null; // TODO: Skal denne egt. returnere en controller?
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            //unprojects the camera (vet ikke hva det vil si, men klikkingen fungerer ikke uten det):
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            // Flytter spilleren ut i fra knappetrykk
            if(upSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                playerController.move("up");
            }
            if(downSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                playerController.move("down");
            }
            if(leftSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                playerController.move("left");
            }
            if(rightSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                playerController.move("right");
            }
            this.vision.setPosition(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1);
        }
    }

    @Override
    protected void update(float dt) { // TODO: Jeg bruker ikke dt til noe. Skal jeg det?
        handleInput();
    }
    /*protected void update(float dt) {
        controller.updatePosition();
    }*/


    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        if (gsm.getPlayer() instanceof SeekerModel) {
            shadowingRender(sb);

        }
        else if (gsm.getPlayer() instanceof HiderModel){

            sb.begin();
            for (int y = 0; y < GardenGuard.numVertical; y++) {
                for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                    board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
                }
            }

            sb.draw(new Texture("player1.png"), gsm.getPlayer().getPosition().x * tileWidth,
                    gsm.getPlayer().getPosition().y * tileHeight, (float) tileWidth, (float) tileHeight);
            sb.end();
        }
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        sb.enableBlending();
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ZERO);
        upSprite.draw(sb, 50);
        downSprite.draw(sb, 50);
        leftSprite.draw(sb, 50);
        rightSprite.draw(sb, 50);
        squareSprite.draw(sb, 50);
        // TODO: kanskje ikke lage en new Texture hver gang? Føler det krever mer (med mindre vi disposer den hele tiden). Kan vel bare bruke den samme? (Jeg gjorde det i helicopter)
        sb.end();

        //BRUKES TIL Å FINNE SPILLERE: MÅ ENDRES
        /*if(this.vision.contains(hider.getPosition())) {
            sb.begin();
            sb.draw(new Texture("player1.png"), hider.getPosition().x * tileWidth,
                    hider.getPosition().y * tileHeight, tileWidth, tileHeight);
            sb.end();
        }*/
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


    private void shadowingRender(SpriteBatch sb) {

        lightSprite.setPosition((gsm.getPlayer().getPosition().x -2) * tileWidth, (gsm.getPlayer().getPosition().y - 2)* tileHeight);
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, tileWidth, tileHeight,false);


        frameBuffer.begin();

        Gdx.gl.glClearColor(.2f,.2f,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.setBlendFunction(GL20.GL_ONE,GL20.GL_ONE);
        sb.begin();
        lightSprite.draw(sb);
        sb.end();

        frameBuffer.end();

        sb.setBlendFunction(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.begin();
        for (int y = 0; y < GardenGuard.numVertical; y++) {
            for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.end();

        sb.begin();
        sb.draw(new Texture("player0.png"), gsm.getPlayer().getPosition().x * tileWidth,
                gsm.getPlayer().getPosition().y * tileHeight, (float) tileWidth, (float) tileHeight);
        sb.end();

        sb.setProjectionMatrix(sb.getProjectionMatrix().idt());

        sb.setBlendFunction( GL20.GL_ZERO,GL20.GL_SRC_COLOR);
        sb.begin();

        sb.draw(frameBuffer.getColorBufferTexture(),-1,1,2,-2);
        sb.end();
    }

}
