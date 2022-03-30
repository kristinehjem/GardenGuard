package com.mygdx.gardenguard.controller.stateControllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.PlayState;

import java.awt.Button;
import java.util.concurrent.TimeUnit;

public class MenuController extends Controller {

    private boolean pinExist;

    public MenuController() {
        super();
        pinExist = false;
    }

    public void handleJoin(String pin) throws InterruptedException {
        super.gsm.getFBIC().checkIfGameExists(pin, this);
        TimeUnit.SECONDS.sleep(1);
        if (getPinExist()) {
            PlayerModel player = new HiderModel(new Vector2(2, 3));
            super.gsm.setPlayer(player);
            super.gsm.getFBIC().CreatePlayerInDB(pin, player);
            super.gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), pin);
            super.gsm.setPin(pin);
            super.gsm.push(new LobbyState());
        }
        else {
            //real error handling
            System.out.println("pin not exist...End");
        }
    }

    public void handleCreate() {
        PlayerModel player = new SeekerModel(new Vector2(2, 3));
        String gamePin = super.gsm.getFBIC().CreateGameInDB();
        super.gsm.getFBIC().CreatePlayerInDB(gamePin, player);
        super.gsm.setPin(gamePin);
        super.gsm.getFBIC().SetOnValueChangedListener(super.gsm.getDataholder(), gamePin);
        super.gsm.set(new LobbyState());
    }

    public void setPinExist(boolean pinExist) {
        this.pinExist = pinExist;
    }

    public boolean getPinExist() {
        return pinExist;
    }

}
