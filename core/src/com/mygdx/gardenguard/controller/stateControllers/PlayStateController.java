package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.controller.playerControllers.HiderController;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameOverState;

import java.util.ArrayList;
import java.util.List;

public class PlayStateController extends Controller {

    private boolean isSeekerTurn;
    private boolean savedPos;
    private PlayerController playerController;
    private Board board;

    public PlayStateController(Board board) {
        super();
        //this.isSeekerTurn = true; //Denne skal slettes til fordel for linja under
        this.isSeekerTurn = false;
        this.board = board;
        this.savedPos = false;
        setPlayerController();
    }

    private void setPlayerController() {
        if (super.gsm.getPlayer() instanceof SeekerModel) {
            this.playerController = new SeekerController((SeekerModel) super.gsm.getPlayer(), this.board);
            super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), false);
        } else if (super.gsm.getPlayer() instanceof HiderModel) {
            this.playerController = new HiderController((HiderModel) super.gsm.getPlayer(), this.board);
        } else {
            System.err.print("Player is neither instance of SeekerModel nor HiderModel");
        }
    }

    public void move(String direction, boolean isSeekerTurn) {
        playerController.move(direction, isSeekerTurn);
    }

    public boolean isSeekerTurn() {
        return isSeekerTurn;
    }

    public void setSeekerTurn(boolean seekerTurn) { isSeekerTurn = seekerTurn; }

    public boolean allSavedPos() {
        for (PlayerModel player : super.getPlayers()) {
            if (!player.getIsDone() && player.getIsSeeker()){
                return false;
            }
        }
        return true;
    }

    public void handleSavePosition() {
        this.savedPos = true;
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
    }

    public void endTurn(){
        if (isSeekerTurn()){
            setSeekerTurn(false);
            endGame();
            }
        else {
            //forslag til kall til databasen:
            //super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
             // TODO: Men denne lagres vel bare lokalt? At hver player har en egen numOfHidersDone? Sånn at den vil aldri kunne overstige 1?
            if (allSavedPos()) {
                setSeekerTurn(true);
            }
        }
        System.out.println("You have ended your turn \n");
    }

    private void endGame() {
        List<String> scores = calculateScores();
        gsm.set(new GameOverState()); //kanskje legge scores som en parameter i gameOverController for å være sikker på at oppdaterte scores vises?
    }

    public List calculateScores(){
        List<String> scores = new ArrayList<>();

        //TODO: calculate scores
        return scores;
    }

    public boolean getSavedPos() {
        return savedPos;
    }
}
