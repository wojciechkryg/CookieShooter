package com.cookieshooter.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.Config;

public class Border {

    public void init(Viewport viewport, World world) {
        initLeft(viewport, world);
        initRight(viewport, world);
    }

    private void initLeft(Viewport viewport, World world) {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(0, 0);
        bodyDefinition.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1 / Config.PPM, viewport.getWorldHeight());

        fixtureDefinition.shape = shape;
        b2body.createFixture(fixtureDefinition);

        shape.dispose();
    }

    private void initRight(Viewport viewport, World world) {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth(), 0);
        bodyDefinition.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1 / Config.PPM, viewport.getWorldHeight());

        fixtureDefinition.shape = shape;
        b2body.createFixture(fixtureDefinition);

        shape.dispose();
    }
}
