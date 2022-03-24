package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;

public class HiderController extends PlayerController {

    private HiderModel player;

    public HiderController(HiderModel player, Board board) {
        super(board);
        this.player = player;
    }


    @Override
    public HiderModel getPlayer() {
        return player;
    }

    // Bytt til try catch hvis dette ikke fungerer: https://coderanch.com/t/649165/java/prevent-user-bounds-simple-array
    protected void moveRight() {
        if(player.getPosition().x == board.getTiles()[0].length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further right, out of bounds");

        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x+1].isWalkable()) {
            player.pushPath(new Vector2((int) (player.getPosition().x)+1, (int) player.getPosition().y));
            player.setPosition((int) (player.getPosition().x)+1, (int) player.getPosition().y);
        }
    }

    protected void moveLeft() {
        if(player.getPosition().x == 0) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][ (int) player.getPosition().x-1].isWalkable()) {
            player.pushPath(new Vector2(player.getPosition().x-1, player.getPosition().y));
            player.setPosition((int) player.getPosition().x-1, (int) player.getPosition().y);
        }
    }

    protected void moveUp() {
        if(player.getPosition().y == 0) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further up, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y-1][(int) player.getPosition().x].isWalkable()) {
            player.pushPath(new Vector2(player.getPosition().x, player.getPosition().y-1));
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y-1);
        }
    }

    protected void moveDown() {
        if(player.getPosition().y == board.getTiles().length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y+1][ (int) player.getPosition().x].isWalkable()) {
            player.pushPath(new Vector2(player.getPosition().x, player.getPosition().y+1));
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y+1);
        }
    }


}
