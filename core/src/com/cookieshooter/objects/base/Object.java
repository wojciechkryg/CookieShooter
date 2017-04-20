package com.cookieshooter.objects.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Object {

    //region Protected variables

    protected Viewport viewport;
    protected World world;

    protected Body body;
    protected Texture texture;
    protected Sprite sprite;

    //endregion Protected variables

    //region Constructors

    protected Object(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;
    }

    //endregion Constructors

    //region Abstract methods

    public abstract void update(float deltaTime);

    public abstract void draw(SpriteBatch batch);

    public abstract void dispose();

    public abstract void destroy();

    //endregion Abstract methods
}
