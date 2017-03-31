package com.cookieshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Character {

    protected Viewport viewport;
    protected World world;

    protected Body body;
    protected Texture texture;
    protected Sprite sprite;

    protected Character(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;
    }

    public abstract void move(float acceleration);

    public abstract void update();

    public abstract void draw(SpriteBatch batch);

    public abstract void dispose();
}
