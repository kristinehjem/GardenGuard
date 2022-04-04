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
    //private PlayerController controller;
    private PlayerModel hider;
    private Rectangle vision;

    // For rendering the seeker view
    private Texture light;
    private Sprite lightSprite;

    public PlayState() {
        super();
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        //DUMMY HIDER FOR TESTING
        this.hider = new HiderModel(new Vector2(8,8));
        //BUTTONS
        this.board = new Board();
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
        // TODO: Logikken inni disse if-statementsene burde nok flyttes til PlayerController. Men jeg vet ikke hvordan jeg kan nå PlayerController fra PlayState.
        // TODO: Dette ble mye super.gsm.getPlayer(). Kunne jeg lagret en lokal variabel med dette, og referert til den heller? Eller blir det feil når man lager flere instanser av klassen elns?
        // Hos meg (Ingrid) må man trykke under knappene for å treffe om man er i emulator.
        // I desktop går det fint. Tror det handler om at mobilen jeg bruker har lengre skjerm enn
        // spillet, så skjerm-koordinatene er ikke de samme som spill-koordinatene

        if(Gdx.input.justTouched()) {
            //unprojects the camera (vet ikke hva det vil si, men klikkingen fungerer ikke uten det):
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

            // Prøver å gå opp:
            if(upSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().y == 14) {
                    System.out.println("Player cannot move further up, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y + 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x), (int) super.gsm.getPlayer().getPosition().y + 1);
                } else {
                    System.out.print("Can't move up because of hedge \n");
                }
            }
            // Prøver å gå ned:
            if(downSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().y == 0) { // TODO: Endre 14 og 8 til board.numTilesHeight og board.numTilesWidth
                    System.out.println("Player cannot move further down, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y - 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x), (int) super.gsm.getPlayer().getPosition().y - 1);
                }  else {
                    System.out.print("Can't move down because of hedge \n");
                }
            }
            // Prøver å gå til venstre:
            if(leftSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().x == 0) {
                    System.out.println("Player cannot move further left, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x - 1].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x - 1), (int) super.gsm.getPlayer().getPosition().y);
                } else {
                    System.out.print("Can't move left because of hedge \n");
                }
            }
            // Prøver å gå til høyre:
            if(rightSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().x == 8) {
                    System.out.println("Player cannot move further right, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x + 1].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x + 1), (int) super.gsm.getPlayer().getPosition().y);
                } else {
                    System.out.print("Can't move right because of hedge \n");
                }
            }
            // Pusher ny posisjon til path:
            // Nå pusher den uansett om det er hider eller seeker. Vet for øyeblikket ikke hvordan
            // jeg skal gjør det for bare hider. Men enten tenker jeg at vi dropper å kunne angre
            // skritt (siden vi ikke rekker det), eller at det kanskje ikke gjør noe om man pusher
            // path til seeker, siden den gjør vel ikke noe med den koden uansett.
            super.gsm.getPlayer().pushPath(new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
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
            //renderStencilImage(sb);
            shadowingRender(sb);

        }
        else {
            sb.begin();
            for (int y = 0; y < GardenGuard.numVertical; y++) {
                for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                    board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
                }
            }

            sb.draw(new Texture("player0.png"), gsm.getPlayer().getPosition().x * tileWidth,
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
        // sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), super.gsm.getPlayer().getPosition().x * tileWidth,super.gsm.getPlayer().getPosition().y * tileHeight, 50, 50);
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        sb.end();

        if(this.vision.contains(hider.getPosition())) {
            sb.begin();
            sb.draw(new Texture("player1.png"), hider.getPosition().x * tileWidth,
                    hider.getPosition().y * tileHeight, tileWidth, tileHeight);
            sb.end();
        }

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

    /*private void renderStencilImage(SpriteBatch sb){
        // Clear the buffer
        Gdx.gl.glClearDepthf(1.0f);
        Gdx.gl.glClear(GL30.GL_DEPTH_BUFFER_BIT);

        // Disable writing to frame buffer and
        // Set up the depth test
        Gdx.gl.glDepthFunc(GL20.GL_LESS);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthMask(true);
        Gdx.gl.glColorMask(false, false, false, true);
        //Here add your mask shape rendering code i.e. rectangle
        //triangle, or other polygonal shape mask

        /*shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(1, 1, 1, 0.5f);
        shapes.circle((float) (gsm.getPlayer().getPosition().x * tileWidth + tileWidth / 2.0),
                (float) (gsm.getPlayer().getPosition().y * tileHeight + tileHeight / 2.0), (float) (tileWidth* 1.5));
        shapes.end();

        // Enable writing to the FrameBuffer
        // and set up the texture to render with the mask
        // applied
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthMask(true);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
        // Here add your texture rendering code
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_ALPHA);
        sb.begin();
        for (int y = 0; y < GardenGuard.numVertical; y++) {
            for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.end();
        // Ensure depth test is disabled so that depth
        sb.begin();
        sb.draw(new Texture("player0.png"), gsm.getPlayer().getPosition().x * tileWidth,
                gsm.getPlayer().getPosition().y * tileHeight, (float) tileWidth, (float) tileHeight);
        sb.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        //Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);


    }*/

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
