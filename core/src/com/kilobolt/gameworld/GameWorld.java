package com.kilobolt.gameworld;

import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;

/**
 * Created by Shayan on 10/06/2015.
   TASKED TO PERFORM UPDATES TO GAME WORLD
 */

public class GameWorld {
    private Bird bird;
    private ScrollHandler scroller;


    public GameWorld(int midPointY) {
        // Initialize bird here
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(midPointY + 66);
    }
    public void update(float delta) {
        bird.update(delta);
        scroller.update(delta);
    }
    public Bird getBird() {
        return bird;
    }
    public ScrollHandler getScroller() {
        return scroller;
    }

}
