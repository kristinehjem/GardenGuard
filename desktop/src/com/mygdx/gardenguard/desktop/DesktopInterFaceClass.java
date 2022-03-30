package com.mygdx.gardenguard.desktop;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.model.player.PlayerModel;

public class DesktopInterFaceClass implements FireBaseInterface {

    @Override
    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin) {
    }

    @Override
    public String CreateGameAndPlayer1InDB(PlayerModel player) {
        return "hei";
    }

    @Override
    public void CreatePlayerInDB(String gamePin, PlayerModel player) {

    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateScoreInDB(String target, String value) {

    }

    @Override
    public boolean checkIfGameExists(String gamePin) {
        return false;
    }

    @Override
    public void getScores(String gamePin) {

    }
}
