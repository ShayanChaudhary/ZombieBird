package com.kilobolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.kilobolt.Helpers.InputHandler;
import com.kilobolt.gameworld.GameRenderer;
import com.kilobolt.gameworld.GameWorld;


/**
 * Created by Shayan on 10/06/2015.
 */
public class GameScreen implements Screen {

    /*Game world and render objects*/
    private GameWorld world;
    private GameRenderer renderer;

    private float runTime = 0;

    public GameScreen(){
        Gdx.app.log("GameScreen", "Attached");

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);
        int midPointY = (int) (gameHeight / 2);

        world = new GameWorld(midPointY); // initialize world
        renderer = new GameRenderer(world, (int) gameHeight, midPointY); // initialize renderer

        //attach input handler
        Gdx.input.setInputProcessor(new InputHandler(world));
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        // Covert Frame rate to String, print it
        //Gdx.app.log("GameScreen FPS", (1 / delta) + "");

        // We are passing in delta to our update method to perform frame-rate independent movement.
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {

    }
}
