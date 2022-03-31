package com.mygdx.gardenguard.controller.stateControllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
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

    public void handleJoin(String gamePin) throws InterruptedException {
        super.gsm.getFBIC().checkIfGameExists(gamePin, this);
        TimeUnit.SECONDS.sleep(1);
        if (getPinExist()) {
            PlayerModel player = new HiderModel(new Vector2(2, 3));
            super.gsm.setPlayer(player);
            player.setPlayerID(super.gsm.getFBIC().CreatePlayerInDB(gamePin, player));
            super.gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), gamePin);
            super.gsm.setPin(gamePin);
            super.gsm.push(new LobbyState());
        }
        else {
            //real error handling
            System.out.println("pin not exist...End");
        }
    }

    public void handleCreate() {
        PlayerModel player = new SeekerModel(new Vector2(2, 3));
        super.gsm.setPlayer(player);
        String gamePin = super.gsm.getFBIC().CreateGameInDB();
        super.gsm.getFBIC().SetOnValueChangedListener(super.gsm.getDataholder(), gamePin);
        setTexture();
        player.setPlayerID(super.gsm.getFBIC().CreatePlayerInDB(gamePin, player));
        super.gsm.setPin(gamePin);
        super.gsm.set(new LobbyState());
    }

    private void setTexture() {
        int numOfPlayers = super.getPlayers().size();
        super.gsm.getPlayer().setTexture("player" + String.valueOf(numOfPlayers) + ".png");
        /*for(int playerNo = 0; playerNo < numOfPlayers; playerNo++) {
            if(super.gsm.getPlayer().getPlayerID() == super.getPlayers().get(playerNo).getPlayerID()) {
                super.gsm.getPlayer().setTexture(new Texture("player" + String.valueOf(playerNo) + ".png"));
            }
        }*/
    }

    public void setPinExist(boolean pinExist) {
        this.pinExist = pinExist;
    }

    public boolean getPinExist() {
        return pinExist;
    }

}
