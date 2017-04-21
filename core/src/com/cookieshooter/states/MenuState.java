package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cookieshooter.drawing.Menu;

public class MenuState extends State {

    private Stage stage;

    public MenuState() {
        super();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        createActors(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void createActors(float startX, float startY) {
        Menu menu = new Menu(startX, startY);
        stage.addActor(menu.getLogoActor());
        stage.addActor(menu.getPlayButtonActor(getPlayEventListener()));
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
        stage.dispose();
    }

    public EventListener getPlayEventListener() {
        EventListener eventListener = new EventListener() {
            @Override
            public boolean handle(Event event) {
                return navigateToState(new PlayState());
            }
        };
        return eventListener;
    }

    private boolean navigateToState(State state) {
        GameStateManager.getInstance().set(state);
        return true;
    }
}
