package com.mygdx.gardenguard.controller.stateControllers;

import com.mygdx.gardenguard.controller.playerControllers.HiderController;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameOverState;

public class PlayStateController extends Controller {

    private boolean isSeekerTurn;
    private PlayerController playerController;
    private Board board;
    private int rounds;

    public PlayStateController() {
        super();
        this.isSeekerTurn = false;
        this.rounds = 1;
        this.board = new Board(getBoardNr());
        setPlayerController();
    }

    private void setPlayerController() {
        if (gsm.getPlayer() instanceof SeekerModel) {
            playerController = new SeekerController((SeekerModel) getPlayer(), board);
            gsm.getFBIC().UpdateGameSwitchInDB(gsm.getGamePin(), false);
        } else if (gsm.getPlayer() instanceof HiderModel) {
            playerController = new HiderController((HiderModel) getPlayer(), board);
        } else {
            System.err.print("Player is neither instance of SeekerModel nor HiderModel");
        }
    }

    public void move(String direction) {
        playerController.move(direction);
    }

    public boolean isSeekerTurn() {
        return isSeekerTurn;
    }

    private boolean allSavedPos() {
        for (PlayerModel player : getPlayers()) {
            //returns false if player is not done and is a hider
            if (!player.getIsDone() && !player.getIsSeeker()){
                return false;
            }
        }
        return true;
    }

    public void handleSavePosition() {
        //SET PLAYER TO DONE AND STEPS TO ZERO
        getPlayer().setIsDone(true);
        gsm.getFBIC().UpdateIsDoneInDB(gsm.getGamePin(), getPlayer().getPlayerID(), true);
        getPlayer().setSteps(0);
        gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), getPlayer().getPlayerID(), getPlayer().getSteps());
    }

    public void checkSwitchTurn(){
        //TRIGGERS SWITCH TURNS IF CONDITIONS ARE MADE FOR FINISHING A ROUND
        if(isSeekerTurn() && getPlayer().getSteps() == 0) {
            getPlayer().setIsDone(true);
            gsm.getFBIC().UpdateGameSwitchInDB(gsm.getGamePin(), false);
        }
        else if(!isSeekerTurn() && allSavedPos()) {
            gsm.getFBIC().UpdateGameSwitchInDB(gsm.getGamePin(), true);
        }
    }

    private void resetSteps(PlayerModel player) {
        //RESETS STEPS FOR BOTH ROLES
        if(player instanceof SeekerModel) {
            player.setSteps(12);
            gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
        }
        else if (player instanceof HiderModel) {
            player.setSteps(8);
            gsm.getFBIC().UpdateStepsInDB(gsm.getGamePin(), player.getPlayerID(), player.getSteps());
        }
    }

    public int getRounds() {
        return rounds;
    }

    public void increaseScore() {
        //FINDS PLAYER IN DB AND GIVES THE PLAYER SCORE ACCORDINGLY
        for(PlayerModel hider : super.getPlayers()) {
            if (hider instanceof HiderModel &&
                    getPlayer().getPlayerID().equals(hider.getPlayerID()) &&
                    !hider.getIsFound()) {
                    getPlayer().setScore(getPlayer().getScore() + 10);
                    gsm.getFBIC().UpdateScoreInDB(gsm.getGamePin(), getPlayer().getPlayerID(), getPlayer().getScore());
            }
            else {
                //SET ISFOUND TO FALSE AGAIN IF IT WAS TRUE
                gsm.getFBIC().UpdateIsFoundInDB(gsm.getGamePin(), getPlayer().getPlayerID(), false);
            }
        }
    }

    public void setSeekerTurn() {
        //CHANGE FROM HIDER TURN TO SEEKER TURN AND
        gsm.getFBIC().UpdateIsDoneInDB(gsm.getGamePin(), getPlayer().getPlayerID(), false);
        isSeekerTurn = true;
    }

    public void setHiderTurn() {
        //CHANGE FROM SEEKER TURN TO HIDER TURN AND RESOLVE STEPS, POINTS, ROUNDS, AND RESET VARIAVBLE LIKE ISDONE
        if (getPlayer().getIsDone() == true) {
            gsm.getFBIC().UpdateIsDoneInDB(gsm.getGamePin(), getPlayer().getPlayerID(), false);
            resetSteps(gsm.getPlayer());
            increaseScore();
            increaseRounds();
            isSeekerTurn = false;
        }
    }

    public void increaseRounds() {
        //INCREASE ROUND NUMBERS BY 1
        rounds++;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isTurn() {
        return (getPlayer() instanceof SeekerModel && isSeekerTurn()) || (getPlayer() instanceof HiderModel && !isSeekerTurn());
    }

    @Override
    public void pushNewState() {
        gsm.set(new GameOverState());
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

}
