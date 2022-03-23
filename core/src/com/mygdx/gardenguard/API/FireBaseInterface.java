package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin);

    public String CreateGameAndPlayer1InDB(Player player);

    public void CreatePlayerInDB(String gamePin, Player player);

    public void UpdatePositionInDB(String gamePin, String playerID, String value);

    public void UpdateScoreInDB(String target, String value);

}
