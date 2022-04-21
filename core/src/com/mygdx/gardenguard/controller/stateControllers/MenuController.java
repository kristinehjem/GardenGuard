package com.mygdx.gardenguard.controller.stateControllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.PlayState;
import com.mygdx.gardenguard.view.PopupState;

import java.awt.Button;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MenuController extends Controller {

    private boolean pinExist;

    public MenuController() {
        super();
        pinExist = false;
    }

    public void handleJoin(String gamePin) throws InterruptedException {
        super.gsm.getFBIC().checkIfGameExists(gamePin, this);
        TimeUnit.SECONDS.sleep(1);
        if (getPinExist()) {
            PlayerModel player = new HiderModel(new Vector2(0, 0));
            player.setSteps(15);
            super.gsm.setPlayer(player);
            super.gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);
            TimeUnit.MILLISECONDS.sleep(500);
            setTextureAndPosition();
            player.setPlayerID(super.gsm.getFBIC().CreatePlayerInDB(gamePin, player));
            super.gsm.setGamePin(gamePin);
            super.gsm.getFBIC().SetOnGameSwitchChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);
            super.gsm.getFBIC().GetBoardNumber(GameStateManager.getInstance().getDataholder(), gamePin);
            System.out.println(super.gsm.getBoardNr());
            super.gsm.set(new LobbyState());
        }
        else {
            System.err.println("pin not exist...End");
            super.gsm.push(new PopupState("Pin does not exist. \n Please try again"));
        }
    }

    public void handleCreate() throws InterruptedException {
        PlayerModel player = new SeekerModel(new Vector2(0,0));
        player.setSteps(10);
        super.gsm.setPlayer(player);
        String gamePin = super.gsm.getFBIC().CreateGameInDB();
        super.gsm.getFBIC().SetOnValueChangedListener(super.gsm.getDataholder(), gamePin);
        TimeUnit.MILLISECONDS.sleep(1000);
        setTextureAndPosition();
        player.setPlayerID(super.gsm.getFBIC().CreatePlayerInDB(gamePin, player));
        super.gsm.setGamePin(gamePin);
        super.gsm.getFBIC().checkIfGameExists(gamePin, this);
        super.gsm.getFBIC().CreateGameSwitchInDB(gamePin);
        super.gsm.getFBIC().SetOnGameSwitchChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);

        Random random = new Random();
        int boardNumber = random.nextInt(3);
        super.gsm.setBoardNr(boardNumber);
        super.gsm.getFBIC().CreateBoardNumberInDB(gamePin, String.valueOf(boardNumber));
        super.gsm.getFBIC().GetBoardNumber(GameStateManager.getInstance().getDataholder(), gamePin);
        super.gsm.set(new LobbyState());
    }

    public void handleTutorial() {
        super.gsm.push(new PopupState(new Texture("tutorial1.png")));
    }

    private void setTextureAndPosition() {
        int numOfPlayers = super.getPlayers().size();
        super.gsm.getPlayer().setTexture("player" + String.valueOf(numOfPlayers) + ".png");
        switch(numOfPlayers) {
            case 0: super.gsm.getPlayer().setPosition(4,7);
                    break;
            case 1: super.gsm.getPlayer().setPosition(3,8);
                    break;
            case 2: super.gsm.getPlayer().setPosition(5,8);
                    break;
            case 3: super.gsm.getPlayer().setPosition(5,6);
                    break;
            case 4: super.gsm.getPlayer().setPosition(3,6);
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
