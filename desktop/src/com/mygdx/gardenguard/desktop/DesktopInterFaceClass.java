package com.mygdx.gardenguard.desktop;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.controller.stateControllers.MenuController;
import com.mygdx.gardenguard.model.player.PlayerModel;

public class DesktopInterFaceClass implements FireBaseInterface {

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
    public void CreateBoardNumberInDB(String gamePin, String boardNr) {
    }

    @Override
    public void SetOnGameSwitchChangedListener(DataHolderClass dataholder, String gamePin) {

    }

    @Override
    public void GetBoardNumber(DataHolderClass dataholder, String gamePin) {

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
    public void UpdateScoreInDB(String gamePin, String playerID, int value) {

    }

    @Override
    public void UpdateIsFoundInDB(String gamePin, String playerID, boolean value) {

    }


    @Override
    public void checkIfGameExists(String gamePin, MenuController MC) {

    }
    @Override
    public void UpdateUsername(String gamePin, String playerID, String username) {

    }

    @Override
    public void UpdateIsDoneInDB(String gamePin, String playerID, boolean isDone) {

    }

    @Override
    public void DeleteGame(String gamePin) {

    }

    @Override
    public void DeletePlayer(String gamePin, String playerID) {

    }

}
