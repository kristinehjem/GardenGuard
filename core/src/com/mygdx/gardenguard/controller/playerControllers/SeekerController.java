package com.mygdx.gardenguard.controller.playerControllers;

import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.board.Tile;
import com.mygdx.gardenguard.model.player.SeekerModel;

public class SeekerController extends PlayerController {

    private final SeekerModel player;

    public SeekerController(SeekerModel player, Board board) {
        super(board);
        this.player = player;
    }

    @Override
    public SeekerModel getPlayer() {
        return player;
    }

    // TODO: Logikken for flytting som nå ligger i PlayState burde flyttes hit. Koden som ligger
    //  inni move-funksjonene nå er redundant. Koden vi bruker (og fungerer) ligger i handleInput()
    //  i PLayState. Så koden som ligger i move-funkdjonene nå skal egt ikke brukes, jeg bare
    //  beholder den i tilfelle det kan være nyttig til når vi flytter logikken fra PlayState
    //  over hit.

    protected void moveUp() {
        /*if(player.getPosition().y == 14) {
            System.out.println("Player cannot move further up, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y + 1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) (player.getPosition().x), (int) player.getPosition().y + 1);
        }*/
    }

    protected void moveDown() {
        /*if(player.getPosition().y == 14) {
            System.out.println("Player cannot move further down, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y - 1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) (player.getPosition().x), (int) player.getPosition().y - 1);
        }*/
    }

    protected void moveRight() {
        /*if(player.getPosition().x == 8) {
            System.out.println("Player cannot move further right, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x + 1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)+1, (int) player.getPosition().y);
        }*/
    }

    protected void moveLeft() {
        /*if(player.getPosition().x == 0) {
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x - 1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)-1, (int) player.getPosition().y);
        }*/
    }
}
