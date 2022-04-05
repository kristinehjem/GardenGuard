package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;

/*
* PlayerController skal ta seg av all logikk som har med hvordan spilleren beveger seg på bordet per nå.
*/

public abstract class PlayerController extends Controller {

    protected Board board;
    public final int tileWidth = GardenGuard.WIDTH / GardenGuard.numHorisontal;
    public final int tileHeight = GardenGuard.HEIGHT / GardenGuard.numVertical;


    public PlayerController(Board board) {
        this.board = board;
    }

    public abstract PlayerModel getPlayer();

    protected abstract void checkForPlayers();

    // TODO: Dette ble mye super.gsm.getPlayer(). Kunne jeg lagret en lokal variabel med dette, og referert til den heller? Eller blir det feil når man lager flere instanser av klassen elns?
    public void move(String direction) {
        switch (direction) {
            case "up":
                if(super.gsm.getPlayer().getPosition().y == 14) {
                    System.out.println("Player cannot move further up, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y + 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x), (int) super.gsm.getPlayer().getPosition().y + 1);
                } else {
                    System.out.print("Can't move up because of hedge \n");
                }
                break;
            case "down":
                if(super.gsm.getPlayer().getPosition().y == 0) {
                    System.out.println("Player cannot move further down, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y - 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x), (int) super.gsm.getPlayer().getPosition().y - 1);
                } else {
                    System.out.print("Can't move down because of hedge \n");
                }
                break;
            case "left":
                if(super.gsm.getPlayer().getPosition().x == 0) {
                    System.out.println("Player cannot move further left, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x - 1].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x - 1), (int) super.gsm.getPlayer().getPosition().y);
                } else {
                    System.out.print("Can't move left because of hedge \n");
                }
                break;
            case "right":
                if(super.gsm.getPlayer().getPosition().x == 8) {
                    System.out.println("Player cannot move further right, out of bounds \n");
                } else if(board.getTiles()[(int) super.gsm.getPlayer().getPosition().y][(int) super.gsm.getPlayer().getPosition().x + 1].isWalkable()) {
                    super.gsm.getPlayer().setPosition((int) (super.gsm.getPlayer().getPosition().x + 1), (int) super.gsm.getPlayer().getPosition().y);
                } else {
                    System.out.print("Can't move right because of hedge \n");
                }
                break;
        }
        // Pusher ny posisjon til path:
        // Nå pusher den uansett om det er hider eller seeker. Vet for øyeblikket ikke hvordan
        // jeg skal gjør det for bare hider. Men enten tenker jeg at vi dropper å kunne angre
        // skritt (siden vi ikke rekker det), eller at det kanskje ikke gjør noe om man pusher
        // path til seeker, siden den gjør vel ikke noe med den koden uansett.
        super.gsm.getPlayer().pushPath(new Vector2(super.gsm.getPlayer().getPosition().x, super.gsm.getPlayer().getPosition().y));
    }

    protected boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }
}
