package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class PlayState extends State {

    // TODO: Når spilleren opprettes må posisjonen dens settes til rett sted (midten for ditto, og rundt midten for alle andre)

    private Board board;
    private Controller controller;
    private int tileWidth = 53; // TODO: Dette burde sikkert implementeres i Tile-klassen. Og: Det hadde vært mye lettere om tilsene var like høy som brede (dvs. at bakgrunnens horisontale streker var like tynne spm de vertikale)
    private int tileHeight = 53; // TODO: Prøv å bruk den nye referansen jeg lagde i board-klassen
    private Sprite upSprite = new Sprite(new Texture("upButton.png"));
    private Sprite downSprite = new Sprite(new Texture("downButton.png"));
    private Sprite leftSprite = new Sprite(new Texture("leftButton.png"));
    private Sprite rightSprite = new Sprite(new Texture("rightButton.png"));
    private Sprite squareSprite = new Sprite(new Texture("yellowSquare.png"));
    private Vector3 touchPoint=new Vector3();
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
        //SHADOW FOR SEEKER
        this.light = new Texture("oaaB1.png");
        this.lightSprite = new Sprite(light);
        this.vision = new Rectangle(gsm.getPlayer().getPosition().x -1, gsm.getPlayer().getPosition().y -1, 2, 2);
        this.board = new Board();
        this.setPlayerController();
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
            this.controller = new SeekerController((SeekerModel) super.gsm.getPlayer(), this.board);
        } else if (super.gsm.getPlayer() instanceof HiderModel) {
            this.controller = new HiderController((HiderModel) super.gsm.getPlayer(), this.board);
        }
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    protected void handleInput() throws InterruptedException {
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
                    int x = (int) super.gsm.getPlayer().getPosition().x;
                    int y = (int) super.gsm.getPlayer().getPosition().y + 1;
                    super.gsm.getPlayer().setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
                } else {
                    System.out.print("Can't move up because of hedge \n");
                }
            }
            // Prøver å gå ned:
            if(downSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().y == 0) { // TODO: Endre 14 og 8 til board.numTilesHeight og board.numTilesWidth
                    System.out.println("Player cannot move further down, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y - 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    int x = (int) super.gsm.getPlayer().getPosition().x;
                    int y = (int) super.gsm.getPlayer().getPosition().y - 1;
                    super.gsm.getPlayer().setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
                }  else {
                    System.out.print("Can't move down because of hedge \n");
                }
            }
            // Prøver å gå til venstre:
            if(leftSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().x == 0) {
                    System.out.println("Player cannot move further left, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x - 1].isWalkable()) {
                    int x = (int) super.gsm.getPlayer().getPosition().x - 1;
                    int y = (int) super.gsm.getPlayer().getPosition().y;
                    super.gsm.getPlayer().setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
                } else {
                    System.out.print("Can't move left because of hedge \n");
                }
            }
            // Prøver å gå til høyre:
            if(rightSprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if(super.gsm.getPlayer().getPosition().x == 8) {
                    System.out.println("Player cannot move further right, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x + 1].isWalkable()) {
                    int x = (int) super.gsm.getPlayer().getPosition().x + 1;
                    int y = (int) super.gsm.getPlayer().getPosition().y;
                    super.gsm.getPlayer().setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
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
    protected void update(float dt) throws InterruptedException { // TODO: Jeg bruker ikke dt til noe. Skal jeg det?
        handleInput();
    }

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
            for (PlayerModel player: getController().getPlayers()) {
                sb.draw(new Texture(player.getTextureFile()), player.getPosition().x * tileWidth,player.getPosition().y * tileHeight, 50, 50);
            }
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
        create();
        showSteps.draw(sb, numberOfSteps, 10,GardenGuard.HEIGHT - 20);
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
        for (PlayerModel player: getController().getPlayers()) {
            sb.draw(new Texture(player.getTextureFile()), player.getPosition().x * tileWidth,player.getPosition().y * tileHeight, 50, 50);
        }
        sb.end();

        sb.setProjectionMatrix(sb.getProjectionMatrix().idt());

        sb.setBlendFunction( GL20.GL_ZERO,GL20.GL_SRC_COLOR);
        sb.begin();

        sb.draw(frameBuffer.getColorBufferTexture(),-1,1,2,-2);
        sb.end();
    }

}
