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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.playerControllers.HiderController;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.PlayStateController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;


public class PlayState extends State {

    private PlayStateController controller;

    private Board board;
    private PlayerController playerController;
    private int tileWidth = 53; // TODO: Dette burde sikkert implementeres i Tile-klassen. Og: Det hadde vært mye lettere om tilsene var like høy som brede (dvs. at bakgrunnens horisontale streker var like tynne spm de vertikale)
    private int tileHeight = 53; // TODO: Prøv å bruk den nye referansen jeg lagde i board-klassen
    private Sprite upSprite = new Sprite(new Texture("upButton.png"));
    private Sprite downSprite = new Sprite(new Texture("downButton.png"));
    private Sprite leftSprite = new Sprite(new Texture("leftButton.png"));
    private Sprite rightSprite = new Sprite(new Texture("rightButton.png"));
    private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint = new Vector3();
    private BitmapFont showSteps;
    private String numberOfSteps;
    //Dummy test to find other players;
    private PlayerModel hider;
    private Rectangle vision;

    // For rendering the seeker view
    private Texture light;
    private Sprite lightSprite;


    public PlayState() {
        super();
        this.controller = new PlayStateController();
        this.board = new Board();
        setPlayerController();
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1, 2, 2);
        this.board = new Board();
        this.setPlayerController();
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x - 1, gsm.getPlayer().getPosition().y - 1, 2, 2);

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
        // TODO: Kommenter ut/endre når merget med PlayerTurnController
        // TODO: Reduser steps for hvert knappetrykk
        /*
        if(isSeeker == true){
            this.numberOfSteps = "Steps left: " + String(SeekerModel.getSteps());
        }
        else {
            this.numberOfSteps = "Steps left: " + String(HiderModel.getSteps());
        }
        */
        // TODO: Fjern denne linjen når if-else setningen over fungerer
        this.numberOfSteps = "Steps left: 15";

        /*OLD CODE: CAN BE USED WHEN MOVING MOVEMENT TO CONTROLLER
        this.player = new SeekerModel(new Vector2(1, 2));
        this.controller = new SeekerController((SeekerModel) this.player, this.board);*/
    }

    private void setPlayerController() {
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            this.playerController = new SeekerController((SeekerModel) super.gsm.getPlayer(), this.board);
        } else if (super.gsm.getPlayer() instanceof HiderModel) {
            this.playerController = new HiderController((HiderModel) super.gsm.getPlayer(), this.board);
        } else {
            System.err.print("Player is neither instance of SeekerModel nor HiderModel");
        }
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
                    playerController.move("up", controller.isSeekerTurn());
                } else if (downSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    playerController.move("down", controller.isSeekerTurn());
                } else if (leftSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    playerController.move("left", controller.isSeekerTurn());
                } else if (rightSprite.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                    playerController.move("right", controller.isSeekerTurn());
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
        // TODO: kanskje ikke lage en new Texture hver gang? Føler det krever mer (med mindre vi disposer den hele tiden). Kan vel bare bruke den samme? (Jeg gjorde det i helicopter)
        sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), super.gsm.getPlayer().getPosition().x * tileWidth, super.gsm.getPlayer().getPosition().y * tileHeight, 50, 50);
        create();
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
        showSteps.dispose();
    }

    @Override
    protected void create() {
        showSteps = new BitmapFont();
        showSteps.setColor(Color.YELLOW);
        showSteps.getData().setScale(2);
        // Hva er tanken med denne klassen?
        // Herman: den er en abstrakt metode som en kan bruke for å lage ting som rendres, f.eks
        // bruker jeg den i popupstate når jeg lager en Stage før jeg rendrer den
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
