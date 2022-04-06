package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.PlayStateController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;


public class PlayState extends State {

    private PlayStateController controller;

    private Board board;
    private int tileWidth = 53; // TODO: Dette burde sikkert implementeres i Tile-klassen. Og: Det hadde vært mye lettere om tilsene var like høy som brede (dvs. at bakgrunnens horisontale streker var like tynne spm de vertikale)
    private int tileHeight = 53; // TODO: Prøv å bruk den nye referansen jeg lagde i board-klassen
    private Sprite upSprite = new Sprite(new Texture("upButton.png"));
    private Sprite downSprite = new Sprite(new Texture("downButton.png"));
    private Sprite leftSprite = new Sprite(new Texture("leftButton.png"));
    private Sprite rightSprite = new Sprite(new Texture("rightButton.png"));
    private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint = new Vector3();
    private BitmapFont showSteps;
    //Dummy test to find other players;
    private PlayerModel hider;
    private Rectangle vision;

    // For rendering the seeker view
    private Texture light;
    private Sprite lightSprite;


    public PlayState() {
        super();
        this.board = new Board();
        this.controller = new PlayStateController(this.board);
        this.board = new Board();
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1, 2, 2);

        upSprite.setSize(tileWidth, tileHeight);
        downSprite.setSize(tileWidth, tileHeight);
        leftSprite.setSize(tileWidth, tileHeight);
        rightSprite.setSize(tileWidth, tileHeight);
        squareSprite.setSize(tileWidth, tileHeight);
        upSprite.setPosition(GardenGuard.WIDTH / 2 - tileWidth / 2 - 1, tileHeight * 3);
        downSprite.setPosition(GardenGuard.WIDTH / 2 - tileWidth / 2 - 1, tileHeight);
        leftSprite.setPosition(GardenGuard.WIDTH / 2 - tileWidth - tileWidth / 2 - 1, tileHeight * 2 - 1);
        rightSprite.setPosition(GardenGuard.WIDTH / 2 + tileWidth / 2 - 1, tileHeight * 2);
        squareSprite.setPosition(GardenGuard.WIDTH / 2 - tileWidth / 2 - 1, tileHeight * 2);
        this.showSteps = new BitmapFont();
        showSteps.setColor(Color.YELLOW);
        showSteps.getData().setScale(2f);
        /*OLD CODE: CAN BE USED WHEN MOVING MOVEMENT TO CONTROLLER
        this.player = new SeekerModel(new Vector2(1, 2));
        this.controller = new SeekerController((SeekerModel) this.player, this.board);*/
    }

    @Override
    public Controller getController() {
        //return this.playerController;
        return controller;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            //unprojects the camera (vet ikke hva det vil si, men klikkingen fungerer ikke uten det):
            cam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
            if ((gsm.getPlayer() instanceof SeekerModel && controller.isSeekerTurn()) || (gsm.getPlayer() instanceof HiderModel && !controller.isSeekerTurn())){
                if (squareSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    controller.endTurn();
                } // Flytter spilleren ut i fra knappetrykk
                else if (upSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    controller.move("up", controller.isSeekerTurn());
                } else if (downSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    controller.move("down", controller.isSeekerTurn());
                } else if (leftSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    controller.move("left", controller.isSeekerTurn());
                } else if (rightSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    controller.move("right", controller.isSeekerTurn());
                }
            } else {
                System.out.println("It is not your turn");
            }
            this.vision.setPosition(gsm.getPlayer().getPosition().x - 1, gsm.getPlayer().getPosition().y - 1);
        }
    }

    @Override
    protected void update(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        if (gsm.getPlayer() instanceof SeekerModel) {
            shadowingRender(sb);
        } else if (gsm.getPlayer() instanceof HiderModel) {
            sb.begin();
            sb.setProjectionMatrix(cam.combined);
            for (int y = 0; y < GardenGuard.numVertical; y++) {
                for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                    board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
                }
            }
            for (PlayerModel player: getController().getPlayers()) {
                sb.draw(new Texture(player.getTextureFile()), player.getPosition().x * tileWidth,player.getPosition().y * tileHeight, 50, 50);
            }
            sb.end();
        }
        sb.begin();
        sb.setProjectionMatrix(cam.combined);
        upSprite.draw(sb, 50);
        downSprite.draw(sb, 50);
        leftSprite.draw(sb, 50);
        rightSprite.draw(sb, 50);
        squareSprite.draw(sb, 50);
        showSteps.draw(sb, "Steps left: " + super.gsm.getPlayer().getSteps(), 10, GardenGuard.HEIGHT - 20);
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

    }

    @Override
    protected void create() {
    }


    private void shadowingRender(SpriteBatch sb) {
        sb.enableBlending();
        lightSprite.setPosition((gsm.getPlayer().getPosition().x -2) * tileWidth, (gsm.getPlayer().getPosition().y - 2)* tileHeight);
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, tileWidth, tileHeight,false);

        frameBuffer.begin();

        Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
        sb.begin();
        lightSprite.draw(sb);
        sb.end();

        frameBuffer.end();

        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.begin();
        for (int y = 0; y < GardenGuard.numVertical; y++) {
            for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.end();

        sb.begin();
        for (PlayerModel player: getController().getPlayers()) {
            sb.draw(new Texture(player.getTextureFile()), player.getPosition().x * tileWidth,player.getPosition().y * tileHeight, 50, 50);
        }
        sb.end();

        sb.setProjectionMatrix(sb.getProjectionMatrix().idt());

        sb.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);
        sb.begin();

        sb.draw(frameBuffer.getColorBufferTexture(), -1, 1, 2, -2);
        sb.end();
        sb.disableBlending();
    }

}
