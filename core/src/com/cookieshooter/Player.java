package com.cookieshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;


public class Player {

    private float width, height;

    private Viewport viewport;
    private World world;
    private Body player;

    private Texture texture;
    private Sprite sprite;

    public Player(Viewport viewport, World world) {
        this.viewport = viewport;
        this.world = world;

        initPlayer();
    }

    private void initPlayer() {
        initSize();
        initBody();
        initImage();
    }

    private void initSize() {
        width = viewport.getWorldWidth() / Config.OBJECT_RATIO;
        height = viewport.getWorldWidth() / Config.OBJECT_RATIO;
    }

    private void initBody() {
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

    private void initImage() {
        texture = new Texture(AssetsPath.PLAYER);
        sprite = new Sprite(texture);
        update();
    }

    public void move(float acceleration) {
        player.setLinearVelocity(new Vector2(acceleration * width * Config.SPEED, player.getLinearVelocity().y));
    }

    public void update() {
        float spriteX = (player.getPosition().x - height) * Config.PPM;
        float spriteY = (player.getPosition().y - width) * Config.PPM;
        float rotation = (float) Math.toDegrees(player.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), width * Config.OBJECT_RATIO, height * Config.OBJECT_RATIO);
    }

    public void dispose() {
        texture.dispose();
    }

}
