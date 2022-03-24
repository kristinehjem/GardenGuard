package com.mygdx.gardenguard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gardenguard.API.DataHolderClass;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.API.Player;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.LobbyState;
import com.mygdx.gardenguard.view.PlayState;

public class GardenGuard extends ApplicationAdapter {
	private GameStateManager gsm;
	public static final int numVertical = 15;
	public static final int numHorisontal = 9;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	private SpriteBatch batch;

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
		gsm = new GameStateManager();
		dataholder = new DataHolderClass(gsm);
		gsm.push(new LobbyState(gsm));
		this.player = new Player("Elen", "2,0");
		this.gamePin = _FBIC.CreateGameAndPlayer1InDB(this.player);
		_FBIC.SetOnValueChangedListener(dataholder, this.gamePin);
		_FBIC.CreatePlayerInDB(this.gamePin, new Player("Beate", "4,0"));
		_FBIC.CreatePlayerInDB(this.gamePin, new Player("Kristine", "5,0"));
		_FBIC.CreatePlayerInDB(this.gamePin, new Player("Herman", "6,0"));
		//_FBIC.UpdatePositionInDB(gamePin, this.player.getPlayerID(), "5,4");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
