package com.mygdx.gardenguard.controller.playerControllers;

import com.mygdx.gardenguard.model.player.PlayerModel;

//PlayerController skal ta av seg all logikk som har med hvordan spilleren beveger seg på bordet per nå

public class PlayerController {

    private String board; //Må endres til board
    private PlayerModel player;

    public PlayerController(PlayerModel player, String board) {
        this.player = player;
        this.board = board;
    }


}
