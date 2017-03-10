package com.cookieshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Player {

    Viewport viewport;
    World world;
    Body player;

    public Player(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;

        initPlayer();
    }

    private void initPlayer() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(viewport.getWorldWidth() / 2, 80 / Config.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        player = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(64 / Config.PPM, 64 / Config.PPM);

        fdef.shape = shape;
        player.createFixture(fdef);

        shape.dispose();
    }

    public void moveLeft(float acc) {
            player.setLinearVelocity(new Vector2(acc, player.getLinearVelocity().y));
    }

    public void moveRight(float acc) {
            player.setLinearVelocity(new Vector2(acc, player.getLinearVelocity().y));
    }
}
