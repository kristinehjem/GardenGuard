package com.mygdx.gardenguard.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gardenguard.GardenGuard;
import com.mygdx.gardenguard.controller.stateControllers.Controller;
import com.mygdx.gardenguard.controller.playerControllers.PlayerController;
import com.mygdx.gardenguard.controller.playerControllers.SeekerController;
import com.mygdx.gardenguard.model.board.Board;
import com.mygdx.gardenguard.model.player.PlayerModel;
import com.mygdx.gardenguard.model.player.SeekerModel;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {

    private Board board;
    private PlayerModel player;
    private PlayerController controller;
    private ShapeRenderer shapes;

    public PlayState(){
        super();
        this.shapes = new ShapeRenderer();
        shapes.setProjectionMatrix(cam.combined);
        this.board = new Board();
        this.player = new SeekerModel(new Vector2(1, 2));
        this.controller = new SeekerController((SeekerModel) this.player, this.board);
    }

    @Override
    public Controller getController() {
        return null;
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        controller.updatePosition();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        if (player instanceof SeekerModel) {
            renderStencilImage(sb);

        }
        else {
            sb.begin();
            for (int y = 0; y < GardenGuard.numVertical; y++) {
                for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                    board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
                }
            }
            sb.draw(new Texture("char.png"), this.player.getPosition().x * this.controller.tileWidth,
                    this.player.getPosition().y * this.controller.tileHeight, (float) this.controller.tileWidth, (float) this.controller.tileHeight);
            sb.end();
        }
    }


    @Override
    protected void dispose() {

    }

    @Override
    protected void create() {

    }

    private void renderStencilImage(SpriteBatch sb){
        // Clear the buffer
        Gdx.gl.glClearDepthf(1.0f);
        Gdx.gl.glClear(GL30.GL_DEPTH_BUFFER_BIT);

        // Disable writing to frame buffer and
        // Set up the depth test
        Gdx.gl.glDepthFunc(GL20.GL_LESS);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthMask(true);
        Gdx.gl.glColorMask(true, false, false, true);
        //Here add your mask shape rendering code i.e. rectangle
        //triangle, or other polygonal shape mask
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(1, 1, 1, 0.5f);
        shapes.circle((float) (player.getPosition().x * controller.tileWidth + controller.tileWidth / 2.0),
                (float) (player.getPosition().y * controller.tileHeight + controller.tileHeight / 2.0), (float) (controller.tileWidth* 1.5));
        shapes.end();

        // Enable writing to the FrameBuffer
        // and set up the texture to render with the mask
        // applied
        Gdx.gl.glColorMask(true, true, true, true);
        Gdx.gl.glDepthMask(false);
        Gdx.gl.glDepthFunc(GL20.GL_EQUAL);
        // Here add your texture rendering code
        sb.begin();
        for (int y = 0; y < GardenGuard.numVertical; y++) {
            for (int x = 0; x < GardenGuard.numHorisontal; x++) {
                board.getTiles()[y][x].getTileView().drawTile(sb, x, y);
            }
        }
        sb.draw(new Texture("char.png"), this.player.getPosition().x * this.controller.tileWidth,
                this.player.getPosition().y * this.controller.tileHeight, (float) this.controller.tileWidth, (float) this.controller.tileHeight);
        sb.end();
        // Ensure depth test is disabled so that depth
        // testing is not run on other rendering code.
        Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);


    }



}
