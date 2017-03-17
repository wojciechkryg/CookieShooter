package com.cookieshooter.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected GameStateManager gameStateManager;

    protected State(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        cam = new OrthographicCamera();
    }

    protected abstract void handleInput();

    public abstract void update(float deltaTime);

    public abstract void render(SpriteBatch batch);

    public abstract void resize(int width, int height);

    public abstract void dispose();

}
