package com.mygdx.gardenguard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.gardenguard.model.board.Board;

public class GardenGuard extends ApplicationAdapter {
	public static int WIDTH = 480;
	public static int HEIGHT = 800;
	SpriteBatch batch;
	Texture img;
	private Board board;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("grass.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 0);
		batch.begin();
		//batch.draw(img, 0, 0);
		board.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
