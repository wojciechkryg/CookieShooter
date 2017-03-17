package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cookieshooter.common.AssetsPath;

public class MenuState extends State {

    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        background = new Texture(AssetsPath.MENU_BACKGROUND);
        playBtn = new Texture(AssetsPath.MENU_START);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(new PlayState(gameStateManager));
            dispose();
        }
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playBtn, Gdx.graphics.getWidth() / 2 - playBtn.getWidth() / 2, Gdx.graphics.getHeight() / 2 - playBtn.getHeight() / 2);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
