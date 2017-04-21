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

    public void createActors(float width, float height) {
        Menu menu = new Menu(width, height);
        stage.addActor(menu.getLogoActor());
        stage.addActor(menu.getPlayButtonActor(getOnPlayButtonClickedListener()));
        stage.addActor(menu.getExitButtonActor(getOnExitButtonClickedListener()));
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

    private EventListener getOnPlayButtonClickedListener() {
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

    private EventListener getOnExitButtonClickedListener() {
        return new EventListener() {
            @Override
            public boolean handle(Event event) {
                Gdx.app.exit();
                return true;
            }
        };
    }
}
