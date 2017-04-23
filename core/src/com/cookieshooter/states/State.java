package com.cookieshooter.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected OrthographicCamera cam;

    protected State() {
        cam = new OrthographicCamera();
    }

    protected abstract void handleInput();

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void resize(int width, int height);

    public abstract void dispose();

}
