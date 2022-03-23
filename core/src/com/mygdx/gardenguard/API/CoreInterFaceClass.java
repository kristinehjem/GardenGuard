package com.mygdx.gardenguard.API;

public class CoreInterFaceClass implements FireBaseInterface {


    @Override
    public void SomeFuction() {
        System.out.println("gjerkgnl");
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
