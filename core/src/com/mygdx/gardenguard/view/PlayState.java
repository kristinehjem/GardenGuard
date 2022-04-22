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
    private Vector3 touchPoint = new Vector3();
    private BitmapFont font;

    private Viewport viewport;
    private Stage stage;
    private boolean switchState;

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
        //FONT TO DRAW
        this.font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(2f);

        create();
        /*OLD CODE: CAN BE USED WHEN MOVING MOVEMENT TO CONTROLLER
        this.player = new SeekerModel(new Vector2(1, 2));
        this.controller = new SeekerController((SeekerModel) this.player, this.board);*/
    }

    @Override
    public Controller getController() {
        return controller;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            this.controller.checkSwitchTurn();
            if (this.controller.isSeekerTurn()) {
                SeekerController seekerController = (SeekerController) this.controller.getPlayerController();
                seekerController.updateView();
                seekerController.checkForPlayers();
            }
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(cam.combined);
        if (gsm.getPlayer() instanceof SeekerModel) {
            shadowingRender(sb);
            showOtherPlayers(sb);
        } else if (gsm.getPlayer() instanceof HiderModel) {
            sb.begin();
            if(controller.isSeekerTurn()) {
                sb.setColor(Color.GRAY);
            }
            else {
                sb.setColor(Color.WHITE);
            }
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
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
        font.draw(sb, "Steps: " + super.gsm.getPlayer().getSteps(), 10, GardenGuard.HEIGHT - 20);
        font.draw(sb, gsm.getPlayer().getScore() + "p", GardenGuard.WIDTH - 130, GardenGuard.HEIGHT - 20);
        font.draw(sb, "Round: " + this.controller.getRounds(), 200, GardenGuard.HEIGHT - 20);
        if(controller.isSeekerTurn()) {
            font.setColor(Color.RED);
            font.draw(sb, "Seekers turn", 180, GardenGuard.HEIGHT - 70);
            font.setColor(Color.YELLOW);
        }
        else {
            font.setColor(Color.RED);
            font.draw(sb, "Hiders turn", 180, GardenGuard.HEIGHT - 70);
            font.setColor(Color.YELLOW);
        }
        create();
        stage.act();
        stage.draw();
        if (switchState) {
            this.controller.pushNewState();
        }
        sb.end();
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
        up.setPosition(GardenGuard.WIDTH / 2f - tileWidth / 2f - 1, tileHeight * 3);
        up.setSize(tileWidth, tileHeight);
        Button down = new ImageButton(downDrawable);
        down.setSize(tileWidth, tileHeight);
        down.setPosition(GardenGuard.WIDTH / 2f - tileWidth / 2f - 1, tileHeight);
        Button left = new ImageButton(leftDrawable);
        left.setPosition(GardenGuard.WIDTH / 2f - tileWidth - tileWidth / 2f - 1, tileHeight * 2 - 1);
        left.setSize(tileWidth, tileHeight);;
        Button right = new ImageButton(rightDrawable);
        right.setPosition(GardenGuard.WIDTH / 2f + tileWidth / 2f - 1, tileHeight * 2);
        right.setSize(tileWidth, tileHeight);
        up.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("up");
                }
                return true;
            }
        });
        down.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("down");
                }
                return true;
            }
        });
        left.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("left");
                }
                return true;
            }
        });
        right.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (isTurn()) {
                    controller.move("right");
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
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            endGame.setVisible(false);
        }
        stage.addActor(endGame);
        stage.addActor(up);
        stage.addActor(down);
        stage.addActor(right);
        stage.addActor(left);
    }

    @Override
    public void setGameSwitch(){
        this.controller.setSeekerTurn();
    }

    @Override
    public void setFalseSwitch() {

        if(this.controller.getRounds() > 5) {
            switchState = true;
        }
        this.controller.setHiderTurn();
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
        //Renders hiders if they are found
        for(PlayerModel hider : super.gsm.getPlayers()) {
            if(hider.getIsFound() && hider instanceof HiderModel) {
                System.out.println("DRAW_HIDER "+ hider.getPosition().x +" : "+ hider.getPosition().y);
                sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                sb.setProjectionMatrix(cam.combined);
                sb.begin();
                sb.draw(new Texture(hider.getTextureFile()), hider.getPosition().x * tileWidth,
                        hider.getPosition().y * tileHeight, tileWidth, tileHeight);
                sb.end();
            }
        }
    }
}
