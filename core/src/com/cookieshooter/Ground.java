package com.cookieshooter;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ground {
    Viewport viewport;
    World world;

    public Ground(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;

        initGround();
    }

    private void initGround() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(viewport.getWorldWidth() / 2, 0);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(viewport.getWorldWidth() / 2, 200 / MainGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
