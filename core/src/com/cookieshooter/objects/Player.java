package com.cookieshooter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cookieshooter.common.AssetsPath;
import com.cookieshooter.common.Config;
import com.cookieshooter.objects.base.Object;

import java.util.ArrayList;
import java.util.List;


public class Player extends Object {

    //region Private variables

    private float width, height;

    private List<Bullet> bullets = new ArrayList<Bullet>();

    //endregion

    //region Constructors

    public Player(Viewport viewport, World world) {
        super(viewport, world);
        init();
    }

    //endregion Contructors

    //region Public methods

    //region Overrides

    @Override
    public void update() {
        moveSprite();
        handleInput();
        updateBullets();
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(sprite, sprite.getX(), sprite.getY(), width * Config.OBJECT_RATIO, height * Config.OBJECT_RATIO);
    }

    @Override
    public void dispose() {
        texture.dispose();
        //todo: dispose bullets
    }

    //endregion Overrides

    public void handleInput() {
        handleAccelerationInput();
        handleTouchInput();
    }

    //endregion Public methods

    //region Private methods

    private void init() {
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
        bodyDefinition.linearDamping = 0;
        bodyDefinition.angularDamping = 0;

        body = world.createBody(bodyDefinition);

        FixtureDef fixtureDefinition = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        fixtureDefinition.shape = shape;
        fixtureDefinition.friction = 0;
        body.createFixture(fixtureDefinition);
        body.setLinearVelocity(0, -10);

        shape.dispose();
    }

    private void initImage() {
        texture = new Texture(AssetsPath.PLAYER);
        sprite = new Sprite(texture);
        update();
    }

    private float getAcceleration(float rotationRate) {
        return -1 * rotationRate;
    }

    private void moveBody(float acceleration) {
        body.setLinearVelocity(new Vector2(acceleration * width * Config.SPEED, body.getLinearVelocity().y));
    }

    private void moveSprite() {
        float spriteX = (body.getPosition().x - height) * Config.PPM;
        float spriteY = (body.getPosition().y - width) * Config.PPM;
        float rotation = (float) Math.toDegrees(body.getAngle());

        sprite.setPosition(spriteX, spriteY);
        sprite.setRotation(rotation);
    }

    private void handleAccelerationInput() {
        moveBody(getAcceleration(Gdx.input.getAccelerometerX()));
    }

    private void handleTouchInput() {
        if (Gdx.input.justTouched()) {
            shot();
        }
    }

    private void shot() {
        bullets.add(getBullet());
    }

    private void updateBullets() {
        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    private Bullet getBullet() {
        return new Bullet(viewport, world, getBulletPosition());
    }

    private Vector2 getBulletPosition() {
        return new Vector2(
                body.getPosition().x,
                body.getPosition().y + (2*height)
        );
    }

    //endregion Private methods
}
