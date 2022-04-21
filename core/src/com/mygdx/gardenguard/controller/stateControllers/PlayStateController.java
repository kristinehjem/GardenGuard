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
    private PlayerController playerController;
    private Board board;
    private int rounds;

    public PlayStateController(Board board) {
        super();
        this.isSeekerTurn = false;
        this.board = board;
        this.rounds = 0;
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

    public boolean allSavedPos() {
        for (PlayerModel player : super.getPlayers()) {
            //returns false if player is not done and is a hider
            if (!player.getIsDone() && !player.getIsSeeker()){
                return false;
            }
        }
        return true;
    }

    public void handleSavePosition() {
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
        super.gsm.getPlayer().setSteps(0);
        super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), super.gsm.getPlayer().getSteps());
    }

    public void checkSwitchTurn(){
        if(isSeekerTurn() && gsm.getPlayer().getSteps() == 0) {
            super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), false);
        }
        else if(!isSeekerTurn() && allSavedPos()) {
            super.gsm.getFBIC().UpdateGameSwitchInDB(super.gsm.getGamePin(), true);
        }
    }

    public List<String> calculateScores(){
        List<String> scores = new ArrayList<>();

        //TODO: calculate scores
        return scores;
    }


    private void resetSteps(PlayerModel player) {
        if(player instanceof SeekerModel) {
            player.setSteps(10);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
        }
        else if (player instanceof HiderModel) {
            player.setSteps(18);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
            super.gsm.getFBIC().UpdateIsFoundInDB(super.gsm.getGamePin(), player.getPlayerID(), false);
        }
    }

    public int getRounds() {
        return this.rounds;
    }

    public void increaseScore() {
        //player gets 20 points if it is not found
        if (!getPlayer().getIsFound() && getPlayer() instanceof HiderModel) {
            getPlayer().setScore(getPlayer().getScore() + 20);
        }
    }

    public void setSeekerTurn() {
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);
        isSeekerTurn = true;
    }

    public void setHiderTurn() {
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);
        resetSteps(gsm.getPlayer());
        isSeekerTurn = false;
    }

    public void increaseRounds() {
        this.rounds++;
    }

    @Override
    public void pushNewState() {
        gsm.set(new GameOverState());
    }

    public PlayerController getPlayerController() {
        return this.playerController;
    }


}
