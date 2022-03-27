package com.mygdx.gardenguard.API;

import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.model.player.PlayerModel;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(DataHolderClass dataholder, String gamePin);

    public String CreateGameAndPlayer1InDB(PlayerModel player);

    public void CreatePlayerInDB(String gamePin, PlayerModel player);

    public void UpdatePositionInDB(String gamePin, String playerID, String value);

    public void UpdateScoreInDB(String target, String value);

}
