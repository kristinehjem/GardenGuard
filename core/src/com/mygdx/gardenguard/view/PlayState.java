package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.stateControllers.PlayStateController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.List;
import java.util.Locale;


public class PlayState extends State {

    private PlayStateController controller;

    private Board board;
    private int tileWidth = 53; // TODO: Dette burde sikkert implementeres i Tile-klassen. Og: Det hadde vært mye lettere om tilsene var like høy som brede (dvs. at bakgrunnens horisontale streker var like tynne spm de vertikale)
    private int tileHeight = 53; // TODO: Prøv å bruk den nye referansen jeg lagde i board-klassen
    private TextureRegion upText = new TextureRegion(new Texture("upButton.png"));
    private TextureRegion downText = new TextureRegion(new Texture("downButton.png"));
    private TextureRegion leftText = new TextureRegion(new Texture("leftButton.png"));
    private TextureRegion rightText = new TextureRegion(new Texture("rightButton.png"));
    private TextureRegionDrawable upDrawable = new TextureRegionDrawable(upText);
    private TextureRegionDrawable downDrawable = new TextureRegionDrawable(downText);
    private TextureRegionDrawable leftDrawable = new TextureRegionDrawable(leftText);
    private TextureRegionDrawable rightDrawable = new TextureRegionDrawable(rightText);
    //private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint = new Vector3();
    private BitmapFont showSteps;

    private Viewport viewport;
    private Stage stage;
    private boolean switchState;
    //Dummy test to find other players;
    private PlayerModel hider;
    private Rectangle vision;

    // For rendering the seeker view
    private Texture light;
    private Sprite lightSprite;

    //CAMERA

    public PlayState() {
        super();
        this.board = new Board(super.gsm.getBoardNr());
        this.controller = new PlayStateController(this.board);
        this.switchState = false;
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1, 2, 2);
        //FONT TO DRAW
        this.showSteps = new BitmapFont();
        showSteps.setColor(Color.YELLOW);
        showSteps.getData().setScale(2f);

        create();
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

    }

    @Override
    protected void update(float dt) {
        this.controller.checkSwitchTurn();
        this.vision.setPosition(gsm.getPlayer().getPosition().x - 1, gsm.getPlayer().getPosition().y - 1);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        if (gsm.getPlayer() instanceof SeekerModel) {
            shadowingRender(sb);
            showOtherPlayers(sb);
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
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.setProjectionMatrix(cam.combined);
        showSteps.draw(sb, "Steps left: " + super.gsm.getPlayer().getSteps(), 10, GardenGuard.HEIGHT - 20);
        showSteps.draw(sb, "Points: " + gsm.getPlayer().getScore(), GardenGuard.WIDTH - 130, GardenGuard.HEIGHT - 20);
        create();
        stage.act();
        stage.draw();
        if (switchState) {
            this.controller.pushNewState();
        }
        sb.end();
        /*if (gameSwitch) {
            this.controller.pushNewState();
        }*/

    }


    @Override
    protected void dispose() {

    }

    private boolean isTurn() {
        return (gsm.getPlayer() instanceof SeekerModel && controller.isSeekerTurn()) || (gsm.getPlayer() instanceof HiderModel && !controller.isSeekerTurn());
    }

    @Override
    protected void create() {
        this.viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        this.stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Button up = new ImageButton(upDrawable);
        up.setPosition(GardenGuard.WIDTH / 2 - tileWidth / 2 - 1, tileHeight * 3);
        up.setSize(tileWidth, tileHeight);
        Button down = new ImageButton(downDrawable);
        down.setSize(tileWidth, tileHeight);
        down.setPosition(GardenGuard.WIDTH / 2 - tileWidth / 2 - 1, tileHeight);
        Button left = new ImageButton(leftDrawable);
        left.setPosition(GardenGuard.WIDTH / 2 - tileWidth - tileWidth / 2 - 1, tileHeight * 2 - 1);
        left.setSize(tileWidth, tileHeight);;
        Button right = new ImageButton(rightDrawable);
        right.setPosition(GardenGuard.WIDTH / 2 + tileWidth / 2 - 1, tileHeight * 2);
        right.setSize(tileWidth, tileHeight);;
        up.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("up", controller.isSeekerTurn());
                }
                return true;
            }
        });
        down.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("down", controller.isSeekerTurn());
                }
                return true;
            }
        });
        left.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("left", controller.isSeekerTurn());
                }
                return true;
            }
        });
        right.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("right", controller.isSeekerTurn());
                }
                return true;
            }
        });
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button endGame = new TextButton("Hide here", mySkin, "small");
        endGame.setPosition(GardenGuard.WIDTH - 80, GardenGuard.HEIGHT-50);
        endGame.setSize(GardenGuard.WIDTH / 6f, GardenGuard.HEIGHT/20f);
        endGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleSavePosition();
                return true;
            }
        });
        stage.addActor(endGame);
        stage.addActor(up);
        stage.addActor(down);
        stage.addActor(right);
        stage.addActor(left);
    }

    /*@Override
    public void setGameSwitch(){
        this.gameSwitch = true;
        viewport = new FitViewport(GardenGuard.WIDTH, GardenGuard.HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Button endGame = new TextButton("Hide here", mySkin, "small");
        endGame.setPosition(GardenGuard.WIDTH - 80, GardenGuard.HEIGHT-50);
        endGame.setSize(GardenGuard.WIDTH / 6f, GardenGuard.HEIGHT/20f);
        endGame.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleSavePosition();
                return true;
            }
        });
        if (super.gsm.getPlayer() instanceof HiderModel) {
            stage.addActor(endGame);
        }
    }*/

    @Override
    public void setGameSwitch(){
        System.out.println("CHECK1SWITCH");
        if(!this.controller.isSeekerTurn()) {
            this.controller.setSeekerTurn(!this.controller.isSeekerTurn());
        }
    }

    @Override
    public void setFalseSwitch() {
        System.out.println("gameswitch is false");
        if(this.controller.isSeekerTurn()) {
            this.controller.setSeekerTurn(!this.controller.isSeekerTurn());
            System.out.println("SWITCHED TO HIDER");
        }
        if(this.controller.getRounds() > 5) {
            switchState = true;
            System.out.println("GAMEFINISHED");
        }
        this.controller.increaseRounds();
    }


    private void shadowingRender(SpriteBatch sb) {
        lightSprite.setPosition((gsm.getPlayer().getPosition().x -2) * tileWidth, (gsm.getPlayer().getPosition().y - 2)* tileHeight);
        FrameBuffer frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, tileWidth, tileHeight,false);
        frameBuffer.begin();
        Gdx.gl.glClearColor(.2f, .2f, .2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(cam.combined);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
        sb.begin();
        if(controller.isSeekerTurn()) {lightSprite.draw(sb);}
        sb.end();
        frameBuffer.end(viewport.getScreenX(), viewport.getScreenY(), (int) viewport.getScreenWidth(), (int) viewport.getScreenHeight());

        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.begin();
        for (int y = 0; y < GardenGuard.numVertical; y++) {
            for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }

        sb.draw(new Texture(getController().getPlayer().getTextureFile()),
                getController().getPlayer().getPosition().x * tileWidth,
                getController().getPlayer().getPosition().y * tileHeight, 50, 50);

        sb.end();

        sb.setProjectionMatrix(sb.getProjectionMatrix().idt());

        sb.setBlendFunction(GL20.GL_ZERO, GL20.GL_SRC_COLOR);
        sb.begin();

        sb.draw(frameBuffer.getColorBufferTexture(), -1, 1, 2, -2);
        sb.end();
    }

    private void showOtherPlayers(SpriteBatch sb){
        List<PlayerModel> list_player = super.gsm.getPlayers();
        System.out.println(controller.getPlayers());
        for(PlayerModel hiders : list_player) {
            if(this.vision.contains(hiders.getPosition()) && hider instanceof SeekerModel) {
                sb.begin();
                sb.draw(new Texture(hiders.getTextureFile()), hiders.getPosition().x * tileWidth,
                        hiders.getPosition().y * tileHeight, tileWidth, tileHeight);
                sb.end();
            }
        }
    }

}
