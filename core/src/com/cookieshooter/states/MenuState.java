package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.cookieshooter.common.AssetsPath;

public class MenuState extends State {

    private Texture logo;
    private Texture playBtn;
    private Drawable drawable;
    private ImageButton playButton;
    private Stage stage;

    public MenuState() {
        super();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        float startX = Gdx.graphics.getWidth();
        float startY = Gdx.graphics.getHeight();

        logo = new Texture(AssetsPath.LOGO);
        drawable = new TextureRegionDrawable(new TextureRegion(logo));
        float scale = startX/logo.getWidth();
        drawable.setMinWidth(startX);
        drawable.setMinHeight(logo.getHeight()*scale);
        Image actor = new Image(drawable);
        actor.setPosition(0,startY-(actor.getHeight()+startY/10));
        stage.addActor(actor);

        playBtn = new Texture(AssetsPath.MENU_START);
        drawable = new TextureRegionDrawable(new TextureRegion(playBtn));
        playButton = new ImageButton(drawable);
        playButton.addListener(getPlayEventListener());
        playButton.setPosition(startX/2-playButton.getWidth()/2,startY/3-playButton.getHeight()/2);
        stage.addActor(playButton);
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
        GameStateManager.getInstance().set(new PlayState());
        dispose();
        return false;
    }
}
