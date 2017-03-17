package com.cookieshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.states.GameStateManager;
import com.cookieshooter.states.MenuState;
import com.cookieshooter.utils.Border;


public class MainGame extends ApplicationAdapter {
    private static SpriteBatch batch;
    private GameStateManager gameStateManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameStateManager = new GameStateManager();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        gameStateManager.push(new MenuState(gameStateManager));
        configure();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameStateManager.resize(width, height);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render(batch);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    private void configure() {
        Gdx.input.setCatchBackKey(true);
    }
}
