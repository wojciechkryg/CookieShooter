package com.cookieshooter.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.Config;
import com.cookieshooter.MainGame;

public class Border {

    public void init(Viewport viewport, World world) {
        initBottom(viewport, world);
        initLeft(viewport, world);
        initRight(viewport, world);
    }

    private void initBottom(Viewport viewport, World world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(viewport.getWorldWidth(), 1 * Config.PPM);
        shape.setAsBox(viewport.getWorldWidth(), 1);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        shape.dispose();
    }

    private void initLeft(Viewport viewport, World world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(0, 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(1 * Config.PPM, viewport.getWorldHeight());
        shape.setAsBox(1, viewport.getWorldHeight());

        fdef.shape = shape;
        b2body.createFixture(fdef);

        shape.dispose();
    }

    private void initRight(Viewport viewport, World world) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(viewport.getWorldWidth(), 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        //shape.setAsBox(1 * Config.PPM, viewport.getWorldHeight());
        shape.setAsBox(1, viewport.getWorldHeight());

        fdef.shape = shape;
        b2body.createFixture(fdef);

        shape.dispose();
    }
}
