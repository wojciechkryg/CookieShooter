package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cookieshooter.drawing.Pause;

public class PauseState extends State {

    private Stage stage;

    public PauseState() {
        super();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        createActors(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    private void createActors(float width, float height) {
        Pause pause = new Pause(width, height);
        stage.addActor(pause.getPlayButtonActor(getOnPlayButtonClickedListener()));
        stage.addActor(pause.getMenuButtonActor(getOnMenuButtonClickedListener()));
    }

    private EventListener getOnPlayButtonClickedListener() {
        return new EventListener() {
            @Override
            public boolean handle(Event event) {
                GameStateManager.getInstance().restore();
                return true;
            }
        };
    }

    private EventListener getOnMenuButtonClickedListener() {
        return new EventListener() {
            @Override
            public boolean handle(Event event) {
                GameStateManager.getInstance().set(new MenuState());
                return true;
            }
        };
    }
}
