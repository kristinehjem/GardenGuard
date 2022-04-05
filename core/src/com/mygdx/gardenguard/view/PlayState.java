package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;

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
    private BitmapFont showSteps;
    private String numberOfSteps;

    public PlayState() {
        super();
        //cam.setToOrtho(false, GardenGuard.WIDTH, GardenGuard.HEIGHT);
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
        // TODO: kanskje ikke lage en new Texture hver gang? Føler det krever mer (med mindre vi disposer den hele tiden). Kan vel bare bruke den samme? (Jeg gjorde det i helicopter)
        sb.draw(new Texture(super.gsm.getPlayer().getTextureFile()), super.gsm.getPlayer().getPosition().x * tileWidth,super.gsm.getPlayer().getPosition().y * tileHeight, 50, 50);
        /*Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);*/
        create();
        showSteps.draw(sb, numberOfSteps, 10,GardenGuard.HEIGHT - 20);
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
}
