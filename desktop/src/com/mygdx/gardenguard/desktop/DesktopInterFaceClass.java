package com.mygdx.gardenguard.desktop;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.API.FireBaseInterface;

public class DesktopInterFaceClass implements FireBaseInterface {
    @Override
    public void SomeFuction() {
    }

    @Override
    public void FirstFireBaseTest() {
    }

    @Override
    public void SetOnValueChangedListener(DataHolderClass dataholder) {
    }

    @Override
    public String CreateGameAndPlayer1InDB(Player player) {
        return "hei";
    }

    @Override
    public void CreatePlayerInDB(String gamePin, Player player) {

    }

    @Override
    public void UpdatePositionInDB(String gamePin, String playerID, String value) {

    }

    @Override
    public void UpdateScoreInDB(String target, String value) {

    }
}
