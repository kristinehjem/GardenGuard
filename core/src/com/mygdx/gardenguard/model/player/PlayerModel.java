package com.mygdx.gardenguard.model.player;

import com.badlogic.gdx.math.Vector3;

public class PlayerModel {

    private String playerID;
    private String role;      // Lage roller seperat fra player modellen?
    private Vector3 position;
    private boolean solid;
    private int score;
    //private firebase for å kunne lage playerID og lagre informasjonen
    //private int face: Sier noe om hvilken retning spriten ser.


    public PlayerModel(String role, String playerID, Vector3 position){

        this.playerID = playerID;
        this.solid = false;
        this.score = 0;
        this.position = position;


    }




}
