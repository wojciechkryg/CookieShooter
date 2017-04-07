package com.cookieshooter.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;
import com.cookieshooter.objects.base.Object;
import com.cookieshooter.utils.RandomVector;

public class Enemy extends Object {

    private Vector2 velocity;
    private float radius;

    public Enemy(Viewport viewport, World world) {
        super(viewport, world);
        init();
    }

    private void init() {
        initSize();
        initVelocity();
        initBody();
        initImage();
    }

    private void initSize() {
        radius = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initVelocity() {
        int minX = Config.MIN_X;
        int maxX = Config.MAX_X;
        int minY = Config.MIN_Y;
        int maxY = Config.MAX_Y;

        velocity = new RandomVector(minX, maxX, minY, maxY);
    }

    private void initBody() {
        BodyDef bodyDefinition = new BodyDef();
        bodyDefinition.position.set(viewport.getWorldWidth() / 2, radius + viewport.getWorldHeight());
        bodyDefinition.type = BodyDef.BodyType.DynamicBody;
        bodyDefinition.angularDamping = 0;
        bodyDefinition.linearDamping = 0;
        body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDefinition.shape = shape;
        fixtureDefinition.friction = 0;
        fixtureDefinition.restitution = 1;
        body.createFixture(fixtureDefinition);
        body.setLinearVelocity(velocity);

        shape.dispose();
    }

    private void initImage() {
        texture = new Texture(AssetsPath.ENEMY);
        sprite = new Sprite(texture);
        update();
    }

    @Override
    public void update() {
        float spriteX = (body.getPosition().x - radius) * Config.PPM;
        float spriteY = (body.getPosition().y - radius) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), radius * Config.OBJECT_RATIO, radius * Config.OBJECT_RATIO);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
