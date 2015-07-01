package com.kilobolt.gameworld;

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
    private boolean isAlive = true;

    public GameWorld(int midPointY) {
        // Initialize bird here
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        int groundY=midPointY + 66;
        scroller = new ScrollHandler(groundY);
    }
    public void update(float delta) {
        bird.update(delta);
        scroller.update(delta);

        if (isAlive && scroller.collides(bird)) {
            // Clean up on game over
            scroller.stop();
            AssetLoader.dead.play();
            isAlive = false;
        }

    }
    public Bird getBird() {
        return bird;
    }
    public ScrollHandler getScroller() {
        return scroller;
    }

}
