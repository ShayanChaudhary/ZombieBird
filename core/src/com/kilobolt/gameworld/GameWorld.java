package com.kilobolt.gameworld;

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

    public GameWorld(int midPointY) {
        // Initialize bird here
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        float groundY=midPointY + 66;
        scroller = new ScrollHandler(this, groundY);
        ground = new Rectangle(0, groundY, 136, 11);
    }
    public void update(float delta) {
        bird.update(delta);
        scroller.update(delta);

        if (bird.isAlive() && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            bird.die();
        }
        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
        }

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
}
