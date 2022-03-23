package com.mygdx.gardenguard.controller.playerControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.gardenguard.model.player.PlayerModel;

/*
* PlayerController skal ta seg av all logikk som har med hvordan spilleren beveger seg på bordet per nå.
*/

public class PlayerController {

    private String board; //Må endre type til board
    private PlayerModel player;

    public PlayerController(PlayerModel player, String board) {
        this.player = player;
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void updatePosition() throws Exception {
        if(Gdx.input.isButtonJustPressed(1)) {
            System.out.println("Gå til høyre \n");
            moveRight();
        }
        if(Gdx.input.isButtonJustPressed(2)) {
            System.out.println("Gå til venstre \n");
            moveLeft();
        }
        if(Gdx.input.isButtonJustPressed(3)) {
            System.out.println("Gå opp \n");
            moveUp();
        }
        if(Gdx.input.isButtonJustPressed(4)) {
            System.out.println("Gå ned \n");
            moveDown();
        }
    }

    // Bytt til try catch hvis dette ikke fungerer: https://coderanch.com/t/649165/java/prevent-user-bounds-simple-array
    private void moveRight() throws Exception {
        if(player.getSteps() - player.getPath().size == 0) { // sjekker om man har flere steps left TODO: lag path
            System.out.print("No steps left"); // TODO: leggge dette inn i UI
        }
        if(player.getxPos() == board.getTiles().lenght()) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            throw new Exception("Player cannot move further right, out of bounds");
            // TODO: lag getTiles
        }
        else if(board.getTiles()[player.getyPos()][player.getxPos()+1].isWalkable) {
            player.pushPath(board.getTiles()[player.getyPos()][player.getxPos()+1]);
        }
    }

    private void moveLeft() throws Exception {
        if(player.getSteps() - path.size == 0) {
            System.out.print("No steps left");
        }
        if(player.getxPos() == board.getTiles().lenght()) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            throw new Exception("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[player.getyPos()][player.getxPos()-1].isWalkable) {
            path.push(board.getTiles()[player.getyPos()][player.getxPos()-1]);
        }
    }

    private void moveUp() throws Exception {
        if(player.getSteps() - path.size == 0) {
            System.out.print("No steps left");
        }
        if(player.getxPos() == board.getTiles().lenght()) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            throw new Exception("Player cannot move further up, out of bounds");
        }
        else if(board.getTiles()[player.getyPos()-1][player.getxPos()].isWalkable) {
            path.push(board.getTiles()[player.getyPos()-1][player.getxPos()]);
        }
    }

    private void moveDown() throws Exception {
        if(player.getSteps() - path.size == 0) {
            System.out.print("No steps left");
        }
        if(player.getxPos() == board.getTiles().lenght()) { // length osv. her er feil. Tanken er at man må sjekka om man er i enden av brettet, hvis ikke vil neste linje gi feilmelding
            throw new Exception("Player cannot move further left, out of bounds");
        }
        else if(board.getTiles()[player.getyPos()+1][player.getxPos()].isWalkable) {
            path.push(board.getTiles()[player.getyPos()+1][player.getxPos()]);
        }
    }

    private boolean collides() {
        return true; // må endres til if-er med om det collides (om det er solid i retningen man vil gå) eller ikke
    }


}
