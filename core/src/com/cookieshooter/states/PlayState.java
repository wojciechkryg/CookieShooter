package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.objects.Player;
import com.cookieshooter.common.Config;
import com.cookieshooter.utils.Border;

public class PlayState extends State {

    private Viewport viewport;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Player player;

    protected PlayState() {
        super();

        world = new World(new Vector2(0, -10), true);
        viewport = new FitViewport(Gdx.graphics.getWidth() / Config.PPM, Gdx.graphics.getHeight() / Config.PPM, cam);
        cam.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        b2dr = new Box2DDebugRenderer();

        new Border().init(viewport, world);
        player = new Player(viewport, world);
    }

    @Override
    protected void handleInput() {
        handleKeyInput();
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        world.step(1 / 45f, 6, 2);
        player.update();
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();
        b2dr.render(world, cam.combined); // TEST BOX2D PHYSICS
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        player.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        world.dispose();
        b2dr.dispose();
        player.dispose();
    }

    private void handleKeyInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            // TODO: pause current state
            GameStateManager.getInstance().set(new MenuState());
        }
    }
}
