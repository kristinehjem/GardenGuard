package com.mygdx.gardenguard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameOverState;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.PlayState;

public class GardenGuard extends ApplicationAdapter {
	private GameStateManager gsm;
	public static final int numVertical = 15;
	public static final int numHorisontal = 9;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	private SpriteBatch batch;

	FireBaseInterface _FBIC;

	public GardenGuard(FireBaseInterface FBIC) {
		_FBIC = FBIC;
	}
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = GameStateManager.getInstance();
		gsm.setFBIC(_FBIC);

		//testing game over state:
		PlayerModel player = new SeekerModel(new Vector2(2, 3));
		gsm.push(new PlayState());
		gsm.setPlayer(player);
		String gamePin = gsm.getFBIC().CreateGameInDB();
		gsm.getFBIC().SetOnValueChangedListener(gsm.getDataholder(), gamePin);
		player.setPlayerID(gsm.getFBIC().CreatePlayerInDB(gamePin, player));
		//super.gsm.getFBIC().CreatePlayerInDB(gamePin, player2);
		gsm.setGamePin(gamePin);
		//gsm.push(new PlayState());

		//gsm.setPin("hei");
		//gsm.push(new GameOverState());
		//gsm.push(new MenuState());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
