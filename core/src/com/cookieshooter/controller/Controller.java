package com.cookieshooter.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.MainGame;

public class Controller implements Disposable {

    private enum ButtonKey {
        LEFT, RIGHT;
    }

    private final static int ICON_SIZE = 128;
    private final static int PADDING = 50;

    Viewport viewport;
    Stage stage;
    boolean leftPressed, rightPressed;
    OrthographicCamera cam;

    public Controller() {
        cam = new OrthographicCamera();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        stage = new Stage(viewport, MainGame.batch);
        Gdx.input.setInputProcessor(stage);

        initTable();
    }

    private void initTable() {
        Table table = new Table();
        table.left().bottom();

        Image leftImg = new Image(new Texture("left.png"));
        leftImg.setSize(ICON_SIZE, ICON_SIZE);
        leftImg.addListener(getOnButtonClickListener(ButtonKey.LEFT));

        Image rightImg = new Image(new Texture("right.png"));
        rightImg.setSize(ICON_SIZE, ICON_SIZE);
        rightImg.addListener(getOnButtonClickListener(ButtonKey.RIGHT));

        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table.add().size(PADDING, PADDING);
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
        table.pad(0, PADDING, PADDING, 0);

        stage.addActor(table);
    }

    private InputListener getOnButtonClickListener(final ButtonKey btn) {
        return new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                switch (btn) {
                    case LEFT:
                        leftPressed = true;
                        break;
                    case RIGHT:
                        rightPressed = true;
                        break;
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                switch (btn) {
                    case LEFT:
                        leftPressed = false;
                        break;
                    case RIGHT:
                        rightPressed = false;
                        break;
                }
            }
        };
    }

    public void draw() {
        stage.draw();
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
