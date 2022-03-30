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

public class MenuController extends Controller {

    public MenuController() {
        super();
    }

    public void handleJoin(String pin){
        /*try {
            super.gsm.getFBIC().CreatePlayerInDB(pin, new HiderModel(new Vector2(4, 5)));
            super.gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), pin);
            super.gsm.setPin(pin);
            super.gsm.push(new LobbyState());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("pin doesnt exist");
        }*/
        if (super.gsm.getFBIC().checkIfGameExists(pin)) {
            super.gsm.getFBIC().CreatePlayerInDB(pin, new HiderModel(new Vector2(4, 5)));
            super.gsm.getFBIC().SetOnValueChangedListener(GameStateManager.getInstance().getDataholder(), pin);
            super.gsm.setPin(pin);
            super.gsm.push(new LobbyState());
        }
        else {
            System.out.println("pin not exist");
        }
    }

    public void handleCreate() {
        PlayerModel player = new SeekerModel(new Vector2(2, 3));
        String gamePin = super.gsm.getFBIC().CreateGameAndPlayer1InDB(player);
        super.gsm.setPin(gamePin);
        super.gsm.getFBIC().SetOnValueChangedListener(super.gsm.getDataholder(), gamePin);
        super.gsm.push(new LobbyState());
    }

}
