package com.cookieshooter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.Config;


public class Player {

    private float width, height;

    Viewport viewport;
    World world;
    Body player;

    public Player(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;

        initPlayer();
    }

    private void initPlayer() {
        initSize();

        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth() / 2, height);
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        player = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        fixtureDefinition.shape = shape;
        player.createFixture(fixtureDefinition);

        shape.dispose();
    }

    private void initSize() {
        width = viewport.getWorldWidth() / Config.OBJECT_RATIO;
        height = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    public void move(float acceleration) {
        player.setLinearVelocity(new Vector2(acceleration * width * Config.SPEED, player.getLinearVelocity().y));
    }

}
