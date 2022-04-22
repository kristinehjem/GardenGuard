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
            increaseRounds();
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
        //SET PLAYER TO DONE AND STEPS TO ZERO
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), true);
        super.gsm.getPlayer().setSteps(0);
        super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), super.gsm.getPlayer().getSteps());
    }

    public void checkSwitchTurn(){
        //TRIGGERS SWITCH TURNS IF CONDITIONS ARE MADE FOR FINISHING A ROUND
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
        //RESETS STEPS FOR BOTH ROLES
        if(player instanceof SeekerModel) {
            player.setSteps(10);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
        }
        else if (player instanceof HiderModel) {
            player.setSteps(5);
            super.gsm.getFBIC().UpdateStepsInDB(super.gsm.getGamePin(), player.getPlayerID(), player.getSteps());
        }
    }

    public int getRounds() {
        return this.rounds;
    }

    public void increaseScore() {
        //FINDS PLAYER IN DB AND GIVES THE PLAYER SCORE ACCORDINGLY
        for(PlayerModel hider : super.getPlayers()) {
            if (hider instanceof HiderModel &&
                    getPlayer().getPlayerID().equals(hider.getPlayerID()) &&
                    !hider.getIsFound()) {

                super.getPlayer().setScore(super.getPlayer().getScore() + 12);
                hider.setScore(super.getPlayer().getScore());
                System.out.println("GAIN_POINTS_CHECK");
                //SET ISFOUND TO FALSE AGAIN IF IT WAS TRUE


            }
            else {
                //SET ISFOUND TO FALSE AGAIN IF IT WAS TRUE
                super.gsm.getFBIC().UpdateIsFoundInDB(gsm.getGamePin(), gsm.getPlayer().getPlayerID(), false);
            }
        }
    }

    public void setSeekerTurn() {
        //CHANGE FROM HIDER TURN TO SEEKER TURN AND
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);
        isSeekerTurn = true;
    }

    public void setHiderTurn() {
        //CHANGE FROM SEEKER TURN TO HIDER TURN AND RESOLVE STEPS, POINTS, ROUNDS, AND RESET VARIAVBLE LIKE ISDONE
        super.gsm.getFBIC().UpdateIsDoneInDB(super.gsm.getGamePin(), super.gsm.getPlayer().getPlayerID(), false);
        resetSteps(gsm.getPlayer());
        increaseScore();
        increaseRounds();
        isSeekerTurn = false;
    }

    public void increaseRounds() {
        //INCREASE ROUND NUMBERS BY 1
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
