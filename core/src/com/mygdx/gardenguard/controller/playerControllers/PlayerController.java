package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

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

    public void move(String direction, boolean isSeekerTurn) {
        PlayerModel player = super.gsm.getPlayer();

        if (player.getSteps() > 0){
            switch (direction) {
            case "up":
                if(super.gsm.getPlayer().getPosition().y == 14) {
                    System.out.println("Player cannot move further up, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y + 1][(int) super.gsm.getPlayer().getPosition().x].isWalkable()) {
                    int x = (int) player.getPosition().x;
                    int y = (int) player.getPosition().y + 1;
                    player.setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.out.print("Can't move up because of hedge \n");
                }
                break;
            case "down":
                if(super.gsm.getPlayer().getPosition().y == 0) {
                    System.out.println("Player cannot move further down, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y - 1][(int) player.getPosition().x].isWalkable()) {
                    int x = (int) super.gsm.getPlayer().getPosition().x;
                    int y = (int) super.gsm.getPlayer().getPosition().y - 1;
                    player.setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.out.print("Can't move down because of hedge \n");
                }
                break;
            case "left":
                if(super.gsm.getPlayer().getPosition().x == 0) {
                    System.out.println("Player cannot move further left, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x - 1].isWalkable()) {
                    int x = (int) super.gsm.getPlayer().getPosition().x - 1;
                    int y = (int) super.gsm.getPlayer().getPosition().y;
                    player.setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.out.print("Can't move left because of hedge \n");
                }
                break;
            case "right":
                if(super.gsm.getPlayer().getPosition().x == 8) {
                    System.out.println("Player cannot move further right, out of bounds \n");
                } else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x + 1].isWalkable()) {
                    int x = (int) player.getPosition().x + 1;
                    int y = (int) player.getPosition().y;
                    player.setPosition(x, y);
                    super.gsm.getFBIC().UpdatePositionInDB(super.gsm.getGamePin(), player.getPlayerID(), new Vector2(player.getPosition().x, player.getPosition().y));
                    player.setSteps(player.getSteps() - 1);
                    super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
                } else {
                    System.out.print("Can't move right because of hedge \n");
                }
                break;
            }
        } else {
            //TODO: popup med "ikke din tur" elns
            System.out.println("You have no steps left");
        }

        // Pusher ny posisjon til path:
        // Nå pusher den uansett om det er hider eller seeker. Vet for øyeblikket ikke hvordan
        // jeg skal gjør det for bare hider. Men enten tenker jeg at vi dropper å kunne angre
        // skritt (siden vi ikke rekker det), eller at det kanskje ikke gjør noe om man pusher
        // path til seeker, siden den gjør vel ikke noe med den koden uansett.
        player.pushPath(new Vector2(player.getPosition().x, player.getPosition().y));
    }

    protected boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }
}
