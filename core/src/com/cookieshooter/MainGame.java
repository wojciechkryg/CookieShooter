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
import com.cookieshooter.controller.Controller;

public class MainGame extends ApplicationAdapter {
    public final static float PPM = 100;
    public static SpriteBatch batch;
    OrthographicCamera cam;
    World world;
    Viewport viewport;
    Box2DDebugRenderer b2dr;
    Controller controller;
    Body player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        cam = new OrthographicCamera();
        world = new World(new Vector2(0, -10), true);
        viewport = new FitViewport(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM, cam);
        b2dr = new Box2DDebugRenderer();
        controller = new Controller();

        createGround();
        createPlayer();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width, height);
        controller.resize(width, height);
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        b2dr.render(world, cam.combined);
        batch.end();

        controller.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        world.dispose();
        b2dr.dispose();
        controller.dispose();
    }

    private void update(float deltaTime) {
        handleInput();
        world.step(1 / 45f, 6, 2);
        cam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        cam.update();
    }

    private void handleInput() {
        if (controller.isLeftPressed()) {
            player.setLinearVelocity(new Vector2(-3, player.getLinearVelocity().y));
        } else if (controller.isRightPressed()) {
            player.setLinearVelocity(new Vector2(3, player.getLinearVelocity().y));
        }
    }

    private void createGround() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(viewport.getWorldWidth() / 2, 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(viewport.getWorldWidth() / 2, 200 / PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    private void createPlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(viewport.getWorldWidth() / 2, 80 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        player = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(64 / PPM, 64 / PPM);

        fdef.shape = shape;
        player.createFixture(fdef);
    }
}
