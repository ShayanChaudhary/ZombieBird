package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.Helpers.AssetLoader;
import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;

/**
 * Created by Shayan on 10/06/2015.
   TASKED TO PERFORM UPDATES TO GAME WORLD
 */

public class GameWorld {
    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;
    private int midPointY;
    public enum GameState{
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }
    private GameState currentState;

    public GameWorld(int midPoint) {
        this.midPointY=midPoint;
        currentState = GameState.READY;
        // Initialize bird here
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        float groundY=midPointY + 66;
        scroller = new ScrollHandler(this, groundY);
        ground = new Rectangle(0, groundY, 136, 11);
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
            default:
                updateRunning(delta);
                break;
        }
    }

    private void updateReady(float delta) {
        // Do nothing for now
    }

    public void updateRunning(float delta) {
        bird.update(delta);
        scroller.update(delta);
        if (bird.isAlive() && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            bird.die();
        }
        //if bird intersects ground then dead
        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;

            //check for highscore, if new score update
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public Bird getBird() {
        return bird;
    }
    public ScrollHandler getScroller() {
        return scroller;
    }
    public int getScore() {
        return score;
    }
    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }
    public void start() {
        currentState = GameState.RUNNING;
    }
    public void restart() {
        currentState = GameState.READY;

        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
