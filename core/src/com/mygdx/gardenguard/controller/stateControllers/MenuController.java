package com.mygdx.gardenguard.controller.stateControllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.GameStateManager;
import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.PopupState;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MenuController extends Controller {

    private boolean pinExist;

    public MenuController() {
        super();
        pinExist = false;
    }

    public void handleJoin(String gamePin) throws InterruptedException {
        gsm.getFBIC().checkIfGameExists(gamePin, this);
        TimeUnit.SECONDS.sleep(1);
        if (getPinExist()) {
            PlayerModel player = new HiderModel(new Vector2(0, 0));
            player.setSteps(12);
            gsm.setPlayer(player);
            gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);
            TimeUnit.MILLISECONDS.sleep(500);
            setTextureAndPosition();
            player.setPlayerID(gsm.getFBIC().CreatePlayerInDB(gamePin, player));
            gsm.setGamePin(gamePin);
            gsm.getFBIC().SetOnGameSwitchChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);
            gsm.getFBIC().GetBoardNumber(GameStateManager.getInstance().getDataholder(), gamePin);
            gsm.set(new LobbyState());
        }
        else {
            super.gsm.push(new PopupState("Pin does not exist. \n Please try again"));
        }
    }

    public void handleCreate() throws InterruptedException {
        PlayerModel player = new SeekerModel(new Vector2(0,0));
        player.setSteps(10);
        gsm.setPlayer(player);
        String gamePin = gsm.getFBIC().CreateGameInDB();
        gsm.getFBIC().SetOnValueChangedListener(gsm.getDataholder(), gamePin);
        TimeUnit.MILLISECONDS.sleep(1000);
        setTextureAndPosition();
        player.setPlayerID(gsm.getFBIC().CreatePlayerInDB(gamePin, player));
        gsm.setGamePin(gamePin);
        gsm.getFBIC().checkIfGameExists(gamePin, this);
        gsm.getFBIC().CreateGameSwitchInDB(gamePin);
        gsm.getFBIC().SetOnGameSwitchChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);

        Random random = new Random();
        int boardNumber = random.nextInt(3);
        gsm.setBoardNr(boardNumber);
        gsm.getFBIC().CreateBoardNumberInDB(gamePin, String.valueOf(boardNumber));
        gsm.getFBIC().GetBoardNumber(GameStateManager.getInstance().getDataholder(), gamePin);
        gsm.set(new LobbyState());
    }

    public void handleTutorial() {
        gsm.push(new PopupState(new Texture("tutorial.png")));
    }

    private void setTextureAndPosition() {
        int numOfPlayers = getPlayers().size();
        gsm.getPlayer().setTexture("player" + String.valueOf(numOfPlayers) + ".png");
        switch(numOfPlayers) {
            case 0: getPlayer().setPosition(4,7);
                    break;
            case 1: getPlayer().setPosition(3,8);
                    break;
            case 2: getPlayer().setPosition(5,8);
                    break;
            case 3: getPlayer().setPosition(5,6);
                    break;
            case 4: getPlayer().setPosition(3,6);
                    break;
        }
    }

    public void setPinExist(boolean pinExist) {
        this.pinExist = pinExist;
    }

    public boolean getPinExist() {
        return pinExist;
    }


}
