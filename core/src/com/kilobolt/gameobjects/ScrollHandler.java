package com.kilobolt.gameobjects;

import com.badlogic.gdx.Game;
import com.kilobolt.Helpers.AssetLoader;
import com.kilobolt.gameworld.GameWorld;

/**
 * Created by Shayan on 10/06/2015.
 */
public class ScrollHandler {

    // ScrollHandler will create all five objects that we need.
    private Grass frontGrass, backGrass;
    private Pipe pipe1, pipe2, pipe3;
    private GameWorld gameWorld;
    // ScrollHandler will use the constants below to determine
    // how fast we need to scroll and also determine
    // the size of the gap between each pair of pipes.

    // Capital letters are used by convention when naming constants.
    public static final int SCROLL_SPEED = -59;
    public static final int PIPE_GAP = 49;

    // Constructor receives a float that tells us where we need to create our
    // Grass and Pipe objects.
    public ScrollHandler(GameWorld gameworld, float yPos) {
        this.gameWorld = gameworld;
        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11,
                SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED, yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED, yPos);
    }

    public void update(float delta) {
        // Update our objects
        frontGrass.update(delta);
        backGrass.update(delta);
        pipe1.update(delta);
        pipe2.update(delta);
        pipe3.update(delta);

        // Check if any of the pipes are scrolled left,
        // and reset accordingly
        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);
        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }

        // Same with grass
        if (frontGrass.isScrolledLeft()) {
            frontGrass.reset(backGrass.getTailX());

        } else if (backGrass.isScrolledLeft()) {
            backGrass.reset(frontGrass.getTailX());

        }
    }

    public void stop() {
        frontGrass.stop();
        backGrass.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    // Return true if ANY pipe hits the bird.
    public boolean collides(Bird bird) {
        if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            pipe1.setScored(true);
            AssetLoader.coin.play();
        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            pipe2.setScored(true);
            AssetLoader.coin.play();

        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < bird.getX()
                + bird.getWidth()) {
            addScore(1);
            pipe3.setScored(true);
            AssetLoader.coin.play();

        }
        return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3.collides(bird));
    }

    // The getters for our five instance variables
    public Grass getFrontGrass() {
        return frontGrass;
    }

    public Grass getBackGrass() {
        return backGrass;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

    private void addScore(int increment) {
        gameWorld.addScore(increment);
    }
}
