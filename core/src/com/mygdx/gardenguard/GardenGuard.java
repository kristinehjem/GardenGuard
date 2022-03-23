package com.mygdx.gardenguard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.API.Player;

public class GardenGuard extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	FireBaseInterface _FBIC;
	DataHolderClass dataholder;
	String gamePin;
	Player player;

	public GardenGuard(FireBaseInterface FBIC) {
		_FBIC = FBIC;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		dataholder = new DataHolderClass();

		_FBIC.SomeFuction();
		_FBIC.FirstFireBaseTest();
		//telling the database we are listening
		_FBIC.SetOnValueChangedListener(dataholder);
		//changing the actual value of message in the database
		//_FBIC.SetValueInDB("new_message", "this is beate speaking");
		_FBIC.SetOnValueChangedListener(dataholder);
		this.player = new Player("Elen", "2,0");
		//_FBIC.SetValueInDB("message", new JSONObject("{\"key\":\"this is beate talking\"}"));
		this.gamePin = _FBIC.CreateGameAndPlayer1InDB(this.player);
		_FBIC.UpdatePositionInDB(gamePin, this.player.getPlayerID(), "3,0");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
