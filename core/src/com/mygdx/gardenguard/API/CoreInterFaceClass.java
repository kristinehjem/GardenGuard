package com.mygdx.gardenguard.API;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

import sun.security.util.ManifestEntryVerifier;

public class CoreInterFaceClass implements FireBaseInterface {

    @Override
    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin) {

    }

    @Override
    public String CreateGameInDB() {
        return "hei";
    }

    @Override
    public String CreatePlayerInDB(String gamePin, PlayerModel player) {
        return "hei";
    }

    @Override
    public void CreateGameSwitchInDB(String gamePin) {

    }

    @Override
    public void SetOngameSwitchChangedListener(DataHolderClass dataholder, String gamePin) {

    }

    @Override
    public void UpdateGameSwitchInDB(String gamePin, boolean gameSwitch) {

    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, Vector2 position) {

    }

    @Override
    public void UpdateStepsInDB(String gamePin, String playerID, int value) {

    }

    @Override
    public void UpdateScoreInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateIsFoundInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {

    }

    @Override
    public void UpdateUsername(String gamePin, String playerID, String username) {

    }

    @Override
    public void getScores(String gamePin) {

    }

    @Override
    public void DeleteGame(String gamePin) {

    }
}
