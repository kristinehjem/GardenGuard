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
    private int rounds;

    public PlayStateController(Board board) {
        super();
        //this.isSeekerTurn = true; //Denne skal slettes til fordel for linja under
        this.isSeekerTurn = false;
        this.board = board;
        this.savedPos = false;
        this.rounds = 1;
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
            if (!player.getIsDone() && !player.getIsSeeker()){
                return false;
            }
        }
        return true;
    }

    public void handleSavePosition() {
        this.savedPos = true;
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
    }

    public void checkSwitchTurn(){
        if (isSeekerTurn() && ((gsm.getPlayer().getSteps() == 0 && gsm.getPlayer() instanceof SeekerModel)
                || (super.gsm.getPlayer().getIsDone() && gsm.getPlayer() instanceof HiderModel))){
            resetSteps(gsm.getPlayer());
            if(gsm.getPlayer().getIsSeeker()) {
                super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), false);
                System.out.println("CHECK3");
                handleRounds();
            }
            super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);

        }
        else if(allSavedPos() && !isSeekerTurn()){
            if(playerController.getPlayer().getIsFound()) {
                super.gsm.getFBIC().UpdateIsFoundInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);
            }
            //forslag til kall til databasen:
            //super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
             // TODO: Men denne lagres vel bare lokalt? At hver player har en egen numOfHidersDone? Sånn at den vil aldri kunne overstige 1?
            System.out.println("CHECK2");
            if(gsm.getPlayer().getIsSeeker()) {
                super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
            }
        }
    }

    public List<String> calculateScores(){
        List<String> scores = new ArrayList<>();

        //TODO: calculate scores
        return scores;
    }

    public boolean getSavedPos() {
        return savedPos;
    }


    private void resetSteps(PlayerModel player) {
        if(player instanceof SeekerModel) {
            player.setSteps(10);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
            //System.out.println(gsm.getPlayer().getSteps());
            //System.out.println(player.getSteps());
        }
        else if (player instanceof  HiderModel) {
            player.setSteps(18);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
            //System.out.println(gsm.getPlayer().getSteps());
            //System.out.println(player.getSteps());
        }
    }

    public int getRounds() {
        return this.rounds;
    }

    public void handleRounds() {
        if (playerController.getPlayer().getIsFound()) { return;}
        playerController.getPlayer().setScore(playerController.getPlayer().getScore() + 20);
    }

    public void increaseRounds() {
        this.rounds++;
        System.out.println("CHECK_FOR_ROUNDS"+ this.rounds);
    }

    @Override
    public void pushNewState() {
        //The same as endGame
        List<String> scores = calculateScores();
        gsm.set(new GameOverState()); //kanskje legge scores som en parameter i gameOverController for å være sikker på at oppdaterte scores vises?
    }

    public PlayerController getPlayerController() {
        return this.playerController;
    }


}
