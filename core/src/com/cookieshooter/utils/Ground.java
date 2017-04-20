package com.cookieshooter.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.Config;

/**
 * Created by Hanna on 07.04.2017.
 */

public class Ground {
    public void init(Viewport viewport, World world) {
        initBottom(viewport, world);
    }

    private void initBottom(Viewport viewport, World world) {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(0, 0);
        bodyDefinition.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bodyDefinition);
        b2body.setUserData(this);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(viewport.getWorldWidth(), 1 / Config.PPM);

        fixtureDefinition.shape = shape;
        b2body.createFixture(fixtureDefinition);

        shape.dispose();
    }
}
