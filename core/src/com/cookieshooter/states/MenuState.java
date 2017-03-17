package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cookieshooter.common.AssetsPath;

public class MenuState extends State {

    private Texture playBtn;
    private Drawable drawable;
    private ImageButton playButton;
    private Stage stage;

    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
        playBtn = new Texture(AssetsPath.MENU_START);
        drawable = new TextureRegionDrawable(new TextureRegion(playBtn));
        playButton = new ImageButton(drawable);
        playButton.addListener(getPlayEventListener());
        stage = new Stage();
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        playBtn.dispose();
    }


    public EventListener getPlayEventListener() {
        EventListener eventListener = new EventListener() {
            @Override
            public boolean handle(Event event) {
                return playclick();
            }
        };
        return eventListener;
    }

    private boolean playclick() {
        gameStateManager.set(new PlayState(gameStateManager));
        dispose();
        return false;
    }
}
