package com.mygdx.gardenguard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gardenguard.API.FireBaseInterface;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.HiderModel;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;
import com.mygdx.gardenguard.view.GameOverState;
import com.mygdx.gardenguard.view.GameStateManager;
import com.mygdx.gardenguard.view.LaunchState;
import com.mygdx.gardenguard.view.MenuState;
import com.mygdx.gardenguard.view.PlayState;

import java.util.Random;

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
		gsm.push(new LaunchState());
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 0f);
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		try {
			gsm.update(Gdx.graphics.getDeltaTime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
