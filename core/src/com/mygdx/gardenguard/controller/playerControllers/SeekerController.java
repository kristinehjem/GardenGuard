package com.mygdx.gardenguard.controller.playerControllers;

import com.mygdx.gardenguard.model.board.Board;
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

    //Bytt til try catch hvis dette ikke fungerer: https://coderanch.com/t/649165/java/prevent-user-bounds-simple-array
    protected void moveRight() {
        if(player.getPosition().x == board.getTiles()[0].length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further right, out of bounds");
            // TODO: lag getTiles
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x+1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)+1, (int) player.getPosition().y);
        }
    }

    protected void moveLeft() {
        if(player.getPosition().x == board.getTiles()[0].length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y][(int) player.getPosition().x-1].isWalkable()) {
            player.setPosition((int) (player.getPosition().x)-1, (int) player.getPosition().y);
        }
    }

    protected void moveUp() {
        if(player.getPosition().y == board.getTiles()[0].length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further up, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y-1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y-1);
        }
    }

    protected void moveDown() {
        if(player.getPosition().y == board.getTiles()[0].length) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            System.out.println("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[(int) player.getPosition().y+1][(int) player.getPosition().x].isWalkable()) {
            player.setPosition((int) player.getPosition().x, (int) player.getPosition().y+1);
        }
    }
}
