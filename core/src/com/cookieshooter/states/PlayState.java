package com.cookieshooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.Player;
import com.cookieshooter.utils.Border;

public class PlayState extends State {

    private Viewport viewport;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Player player;

    protected PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        world = new World(new Vector2(0, -10), true);
        //viewport = new FitViewport(Gdx.graphics.getWidth() * Config.PPM, Gdx.graphics.getHeight() * Config.PPM, cam);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        b2dr = new Box2DDebugRenderer();

        new Border().init(viewport, world);
        player = new Player(viewport, world);
    }

    @Override
    protected void handleInput() {
        float accelX = Gdx.input.getAccelerometerX();
        System.out.print(accelX);
        if (accelX > 2)
            player.moveRight(-1 * accelX);
        else if (accelX < -2)
            player.moveLeft(-1 * accelX);
    }

    @Override
    public void update(float deltaTime) {
        handleInput();
        world.step(1 / 45f, 6, 2);
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        b2dr.render(world, cam.combined);
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
    }
}
