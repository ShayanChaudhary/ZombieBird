package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Shayan on 10/06/2015.
 * BIRD GAME OBJECT
 */
public class Bird {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // For handling bird rotation
    private int width;
    private int height;

    public Bird(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x,y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 200) {
            velocity.y = 200;
        }
        //update y position based on velocity
        position.add(velocity.cpy().scl(delta));

        //update rotation
        // Rotate counterclockwise
        if (velocity.y < 0) {
            //scaled by delta to keep rotation constant on fast/slow devices.
            rotation -= 600 * delta;

            if (rotation < -20) {
                rotation = -20;
            }
        }
        // Rotate clockwise
        if (isFalling()) {
            //scaled by delta to keep rotation constant on fast/slow devices.
            rotation += 480 * delta;
            if (rotation > 90) {
                rotation = 90;
            }

        }

        //perform boundary checks
        if(position.y > 200) {position.y = 200;}
        if(position.y <0) {position.y = 0;}
    }

    public boolean isFalling() {
        return velocity.y > 110;
    }

    public boolean shouldntFlap() {
        return velocity.y > 70;
    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

}
