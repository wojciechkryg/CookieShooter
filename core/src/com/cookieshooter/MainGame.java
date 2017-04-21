package com.cookieshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cookieshooter.states.GameStateManager;
import com.cookieshooter.states.MenuState;


public class MainGame extends ApplicationAdapter {
    private static SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        GameStateManager.getInstance().push(new MenuState());
        configure();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        GameStateManager.getInstance().resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GameStateManager.getInstance().update(Gdx.graphics.getDeltaTime());
        GameStateManager.getInstance().render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void configure() {
        Gdx.input.setCatchBackKey(true);
    }
}
