package com.cookieshooter.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayState extends State {

    private Texture player;

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        player = new Texture("badlogic.jpg");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(player, 100, 100);
        batch.end();
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}
