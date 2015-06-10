package com.kilobolt.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.gameobjects.Bird;

/**
 * Created by Shayan on 10/06/2015.
   TASKED TO PERFORM UPDATES TO GAME WORLD
 */

public class GameWorld {
    private Bird bird;

    public GameWorld(int midPointY) {
        // Initialize bird here
        bird = new Bird(33, midPointY - 5, 17, 12);
    }
    public void update(float delta) {
        bird.update(delta);
    }
    public Bird getBird() {
        return bird;
    }

}
