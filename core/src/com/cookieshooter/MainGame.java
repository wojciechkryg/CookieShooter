package com.cookieshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.utils.Border;

import java.io.Console;

public class MainGame extends ApplicationAdapter {
    public final static float PPM = 100;
    public static SpriteBatch batch;
    OrthographicCamera cam;
    World world;
    Viewport viewport;
    Box2DDebugRenderer b2dr;
    Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        world = new World(new Vector2(0, -10), true);
        viewport = new FitViewport(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM, cam);
        b2dr = new Box2DDebugRenderer();

        new Border().init(viewport, world);
        player = new Player(viewport, world);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        b2dr.render(world, cam.combined);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        b2dr.dispose();
    }

    private void update(float deltaTime) {
        handleInput();
        world.step(1 / 45f, 6, 2);
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();
    }

    private void handleInput() {
        float accelX = Gdx.input.getAccelerometerX();
        System.out.print(accelX);
        if(accelX>2)
            player.moveRight(-1*accelX);
        else if(accelX<-2)
            player.moveLeft(-1*accelX);

    }
}
