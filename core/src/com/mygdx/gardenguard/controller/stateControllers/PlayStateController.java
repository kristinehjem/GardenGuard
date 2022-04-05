package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameOverState;

import java.util.ArrayList;
import java.util.List;

public class PlayStateController extends Controller {

    private boolean isSeekerTurn;
    private int numOfHidersDone;
    private int currentRound;


    public PlayStateController() {
        super();
        this.isSeekerTurn = false;
        this.currentRound = 1;
    }

    public boolean isSeekerTurn() {
        return isSeekerTurn;
    }

    public void setSeekerTurn(boolean seekerTurn) {
        isSeekerTurn = seekerTurn;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void startTurn(){
        if (isSeekerTurn()){
            for (PlayerModel player : super.players){
                if (player instanceof SeekerModel){
                    player.setSteps(15 - (2 * getCurrentRound())); //set steps for seeker to right amount of starting steps
                    break;
                }
            }
        } else {
            for (PlayerModel player : super.players){
                if (player instanceof HiderModel){
                    player.setSteps(15 - (2 * getCurrentRound())); //set steps for seeker to right amount of starting steps
                }
            }
            numOfHidersDone = 0;
        }
    }

    private void endGame() {
        List<String> scores = calculateScores();
        gsm.set(new GameOverState()); //kanskje legge scores som en parameter i gameOverController for å være sikker på at oppdaterte scores vises?
    }

    public void endTurn(){
        if (isSeekerTurn()){
            setSeekerTurn(false);
            setCurrentRound(getCurrentRound() + 1);
            if (getCurrentRound() <= 5){
                List<String> scores = calculateScores();
                //TODO: show the scores in the popup
                startTurn();
            } else {
                endGame();
            }
        } else {
            numOfHidersDone++;
            if (numOfHidersDone >= super.getPlayers().size() - 1) {
                setSeekerTurn(true);
                startTurn();
            }
        }
    }

    public List calculateScores(){
        List<String> scores = new ArrayList<>();

        //TODO: calculate scores
        return scores;
    }
}
